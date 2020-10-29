package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

public class CourseParticipantDAOImpl implements CourseParticipantDAO{

	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(CourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////
	private static final String FIND_COURSE_PARTICIPANTS = "SELECT course_participants.id, users.id as userID, users.name, users.surname, "+
			"results.rating, results.review "+
			"FROM course_participants "+
			"JOIN users ON course_participants.users_id = users.id "+
			"LEFT OUTER JOIN results on course_participants.results_id = results.id "+
			"WHERE course_participants.running_courses_id = ? " +
			"ORDER BY users.name";
	
	private static final String IS_COURSE_PARTICIPANT = "SELECT * FROM course_participants " + 
			"WHERE course_participants.users_id=? AND course_participants.running_courses_id=?";
	
	private static final String FIND_COURSES_PARTICIPANT_RESULTS ="SELECT running_courses.id, running_courses.start, running_courses.end, running_courses.passing, " + 
			"courses.id AS courseID, courses.courseName, courses.description, " + 
			"users.id AS teacherID, users.name, users.surname, " + 
			"results.rating, results.review " + 
			"FROM course_participants " + 
			"LEFT OUTER JOIN results ON course_participants.results_id = results.id " + 
			"JOIN running_courses ON course_participants.running_courses_id = running_courses.id " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE course_participants.id IN" + 
			"(SELECT course_participants.id FROM course_participants WHERE course_participants.users_id=?)";
	
	private static final String ADD_COURSE_PARTICIPANT = "INSERT INTO course_participants(users_id, running_courses_id) VALUES(?,?)";

	private static final String DELETE_COURSE_PARTICIPANT = "DELETE course_participants, results " + 
			"FROM course_participants " + 
			"JOIN results ON course_participants.results_id = results.id " + 
			"WHERE course_participants.users_id = ? AND course_participants.running_courses_id = ?";
	
	/////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String USER_ID = "userID";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String COURSE_ID = "courseID";
	private static final String TEACHER_ID = "teacherID";
	
	private static final String RATING = "rating";
	private static final String REVIEW = "review";	
	
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	private static final String PASSING = "passing";
	private static final String START = "start";
	private static final String END = "end";	

	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<CourseParticipant> findCourseParticipants(int runningCourseID) throws DAOException {
		List<CourseParticipant> courseParticipants = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipant;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE_PARTICIPANTS);
			ps.setInt(1, runningCourseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                courseParticipant = new CourseParticipant();
                courseParticipant.setId(Integer.parseInt(resultSet.getString(ID)));
                
				
				  User student = new User();
				  student.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				  student.setName(resultSet.getString(NAME));
				  student.setSurname(resultSet.getString(SURNAME));
				  courseParticipant.setStudent(student);
				 
                Result result = new Result();
                if(resultSet.getString(RATING) != null) {
                	result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                result.setReview(resultSet.getString(REVIEW));
                courseParticipant.setResult(result);

                courseParticipants.add(courseParticipant);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return courseParticipants;
	}

	@Override
	public List<CourseParticipant> findCoursesParticipantResults(int studentID) throws DAOException {
		List<CourseParticipant> coursesParticipantResults = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipantResult;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSES_PARTICIPANT_RESULTS);
			ps.setInt(1, studentID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                courseParticipantResult = new CourseParticipant(); 
                User student = new User();
                student.setId(studentID);
                courseParticipantResult.setStudent(student);
                
				RunningCourse runningCourse = new RunningCourse();
				runningCourse.setId(Integer.parseInt(resultSet.getString(ID)));
                runningCourse.setStart(resultSet.getDate(START).toLocalDate());
                runningCourse.setEnd(resultSet.getDate(END).toLocalDate());
                runningCourse.setPassing(resultSet.getString(PASSING));
                
                Course course = new Course();
                course.setId(Integer.parseInt(resultSet.getString(COURSE_ID)));
                course.setCourseName(resultSet.getString(COURSE_NAME));
                course.setDescription(resultSet.getString(DESCRIPTION));
                runningCourse.setCourse(course);      
                
                User teacher = new User();
                teacher.setId(Integer.parseInt(resultSet.getString(TEACHER_ID)));
                teacher.setName(resultSet.getString(NAME));
                teacher.setSurname(resultSet.getString(SURNAME));
                runningCourse.setTeacher(teacher);
                
                courseParticipantResult.setRunningCourse(runningCourse);
                
                Result result = new Result();
                if(resultSet.getString(RATING) != null) {
                	result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                result.setReview(resultSet.getString(REVIEW));
                courseParticipantResult.setResult(result);

                coursesParticipantResults.add(courseParticipantResult);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return coursesParticipantResults;
	}
	
	@Override
	public boolean addCourseParticipant(int studentID, int runningCourseID) throws DAOException {
		if(isCourseParticipant(studentID,runningCourseID)) {
			return false;
		}
		
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_COURSE_PARTICIPANT, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return true;		
	}

	private boolean isCourseParticipant(int studentID, int runningCourseID) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(IS_COURSE_PARTICIPANT);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return false;
	}

	
	public void deleteCourseParticipant(int studentID, int runningCourseID) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(DELETE_COURSE_PARTICIPANT);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
	
}
