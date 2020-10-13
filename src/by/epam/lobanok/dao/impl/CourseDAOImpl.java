package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

public class CourseDAOImpl implements CourseDAO{	
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(CourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String FIND_COURSES = "SELECT * FROM courses";	
	
	private static final String FIND_RUNNING_COURSES = "SELECT running_courses.id, running_courses.start, running_courses.end, running_courses.passing, " + 
			"courses.courseName, courses.description, users.name, users.surname " + 
			"FROM running_courses " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE courses_id = ?";	
	
	private static final String FIND_RUNNING_COURSE = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing, "+
			"courses.courseName, courses.description, users.name, users.surname "+
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id  "+
			"WHERE running_courses.id = ?";
	
	private static final String FIND_STUDENT_COURSES = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing,"+
			"courses.courseName, courses.description, users.name, users.surname "+ 
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id "+
			"WHERE running_courses.id IN (SELECT running_courses.id "+ 
										"FROM running_courses "+ 
										"JOIN course_participants ON running_courses.id=course_participants.running_courses_id "+
										"JOIN users ON users.id=course_participants.users_id "+
										"WHERE course_participants.users_id=?)";
	
	private static final String FIND_TEACHER_COURSES ="SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing, "+
			"courses.courseName, courses.description "+ 
			"FROM courses "+
			"JOIN running_courses ON courses.id = running_courses.courses_id "+
			"JOIN users ON users.id = running_courses.users_id "+
			"WHERE running_courses.users_id = ?";


	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	private static final String PASSING = "passing";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Course> findCourses() throws DAOException {
		List<Course> courses = new ArrayList<Course>();
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSES);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                course = new Course();
                course.setId(Integer.parseInt(resultSet.getString(ID)));
                course.setCourseName(resultSet.getString(COURSE_NAME));
                course.setDescription(resultSet.getString(DESCRIPTION));
                courses.add(course);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return courses;
	}
	
	
	@Override
	public List<RunningCourse> findRunningCourses(int courseID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSES);
			ps.setInt(1, courseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                runningCourse = new RunningCourse();
                runningCourse.setId(Integer.parseInt(resultSet.getString(ID)));
                //set start
                //set end
                runningCourse.setPassing(resultSet.getString(PASSING));
                
                User teacher = new User();
                teacher.setName(resultSet.getString(NAME));
                teacher.setSurname(resultSet.getString(SURNAME));
                runningCourse.setTeacher(teacher);
                
                Course course = new Course();
                course.setCourseName(resultSet.getString(COURSE_NAME));
                course.setDescription(resultSet.getString(DESCRIPTION));
                runningCourse.setCourse(course);
                
                runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourses;
	}
	

	@Override
	public RunningCourse findRunningCourse(int runningCourseID) throws DAOException {
		RunningCourse runningCourse = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSE);
			ps.setInt(1, runningCourseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                runningCourse = new RunningCourse();
                runningCourse.setId(Integer.parseInt(resultSet.getString(ID)));
                //set start
                //set end
                runningCourse.setPassing(resultSet.getString(PASSING));
                
                User teacher = new User();
                teacher.setName(resultSet.getString(NAME));
                teacher.setSurname(resultSet.getString(SURNAME));
                runningCourse.setTeacher(teacher);
                
                Course course = new Course();
                course.setCourseName(resultSet.getString(COURSE_NAME));
                course.setDescription(resultSet.getString(DESCRIPTION));
                runningCourse.setCourse(course);
			}			
		}catch (SQLException e) {
			logger.info("DAOException");//////////////////////magic value
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourse;
	}
	
	
	@Override
	public List<RunningCourse> findStudentCourses(int userID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENT_COURSES);
			ps.setInt(1,userID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {//мб отдельно создавать обьект runningCourse?
				runningCourse = new RunningCourse();
				
				runningCourse.setId(Integer.parseInt(resultSet.getString(ID)));
				//start
				//end
				runningCourse.setPassing(resultSet.getString(PASSING));
				
				Course course = new Course();
				course.setCourseName(resultSet.getString(COURSE_NAME));
				course.setDescription(resultSet.getString(DESCRIPTION));				
				runningCourse.setCourse(course);
				
				User teacher = new User();
				teacher.setName(resultSet.getString(NAME));
				teacher.setSurname(resultSet.getString(SURNAME));
				runningCourse.setTeacher(teacher);
				
				runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return runningCourses;
	}
	
	
	@Override
	public List<RunningCourse> findTeacherCourses(int userID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHER_COURSES);
			ps.setInt(1, userID);
            //ps.setString(2, surname);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				runningCourse = new RunningCourse();
				
				runningCourse.setId(Integer.parseInt(resultSet.getString(ID)));
				//start
				//end
				runningCourse.setPassing(resultSet.getString(PASSING));
				
				Course course = new Course();
				course.setCourseName(resultSet.getString(COURSE_NAME));
				course.setDescription(resultSet.getString(DESCRIPTION));				
				runningCourse.setCourse(course);

				runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourses;
	}


}
