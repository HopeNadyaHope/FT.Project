package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

public class CourseDAOImpl implements CourseDAO{	
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private final String FIND_COURSES = "SELECT * FROM courses";	
	
	private final String FIND_STUDENT_COURSES = "SELECT courses.id, courses.courseName, courses.description " +
			"FROM courses " + 
			"JOIN running_courses ON courses.id=running_courses.courses_id " + 
			"JOIN course_participants ON running_courses.id=course_participants.running_courses_id " + 
			"JOIN users ON users.id=course_participants.users_id " + 
			"WHERE course_participants.users_id=(SELECT users.id FROM users WHERE users.name=? AND users.surname=?) ";
	
	private final String FIND_TEACHER_COURSES ="SELECT courses.id, courses.courseName, courses.description " + 
			"FROM courses " + 
			"JOIN running_courses ON courses.id = running_courses.courses_id " + 
			"JOIN users ON users.id = running_courses.users_id " + 
			"WHERE running_courses.users_id = (SELECT users.id FROM users WHERE users.name=? and users.surname=?)";
	
	private final String FIND_RUNNING_COURSES = "SELECT running_courses.id, running_courses.start, running_courses.end, running_courses.passing, " + 
			"courses.courseName, courses.description, users.name, users.surname " + 
			"FROM running_courses " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE courses_id = ?";
	
	
	private final String ID = "id";
	private final String COURSE_NAME = "courseName";
	private final String DESCRIPTION = "description";
	private final String PASSING = "passing";
	private final String NAME = "name";
	private final String SURNAME = "surname";
	
	@Override
	public List<Course> findCourses() throws DAOException {
		List<Course> courses = new ArrayList<Course>();
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSES);
			result = ps.executeQuery();
			
			while(result.next()) {
                course = new Course();
                course.setId(Integer.parseInt(result.getString(ID)));
                course.setCourseName(result.getString(COURSE_NAME));
                course.setDescription(result.getString(DESCRIPTION));
                courses.add(course);
			}
			
		}catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return courses;
	}
	
	@Override
	public List<Course> findStudentCourses(String name, String surname) throws DAOException {
		List<Course> courses = new ArrayList<Course>();
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENT_COURSES);
			ps.setString(1, name);
            ps.setString(2, surname);
			result = ps.executeQuery();
			
			while(result.next()) {
                course = new Course();
                course.setId(Integer.parseInt(result.getString(ID)));
                course.setCourseName(result.getString(COURSE_NAME));
                course.setDescription(result.getString(DESCRIPTION));
                courses.add(course);
			}
			
		}catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return courses;
	}
	
	@Override
	public List<Course> findTeacherCourses(String name, String surname) throws DAOException {
		List<Course> courses = new ArrayList<Course>();
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHER_COURSES);
			ps.setString(1, name);
            ps.setString(2, surname);
			result = ps.executeQuery();
			
			while(result.next()) {
                course = new Course();
                course.setId(Integer.parseInt(result.getString(ID)));
                course.setCourseName(result.getString(COURSE_NAME));
                course.setDescription(result.getString(DESCRIPTION));
                courses.add(course);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
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
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSES);
			ps.setInt(1, courseID);
			result = ps.executeQuery();
			
			while(result.next()) {
                runningCourse = new RunningCourse();
                runningCourse.setId(Integer.parseInt(result.getString(ID)));
                //set start
                //set end
                runningCourse.setPassing(result.getString(PASSING));
                
                User teacher = new User();
                teacher.setName(result.getString(NAME));
                teacher.setSurname(result.getString(SURNAME));
                runningCourse.setTeacher(teacher);
                
                Course course = new Course();
                course.setCourseName(result.getString(COURSE_NAME));
                course.setDescription(result.getString(DESCRIPTION));
                runningCourse.setCourse(course);
                
                System.out.println(runningCourse);
                
                runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourses;
	}

}
