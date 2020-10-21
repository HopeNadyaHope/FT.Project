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

public class CourseDAOImpl implements CourseDAO{	
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(CourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String FIND_COURSES = "SELECT * FROM courses";	
	private static final String FIND_COURSE = "SELECT * FROM courses WHERE id=?";
	private static final String EDIT_COURSE = "UPDATE courses SET courseName=?,description=? WHERE id=?";
	private static final String ADD_COURSE = "INSERT INTO courses(courseName, description) VALUES(?,?)";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	
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
	
	public Course findCourse(int courseID) throws DAOException{
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE);
			ps.setInt(1, courseID);
			
			resultSet = ps.executeQuery();			
			resultSet.next();
			
            course = new Course();
            course.setId(Integer.parseInt(resultSet.getString(ID)));
            course.setCourseName(resultSet.getString(COURSE_NAME));
            course.setDescription(resultSet.getString(DESCRIPTION));
			
		}catch (SQLException e) {
			logger.info("DAOException: in SQL (findCourse)");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return course;		
	}
	


	@Override
	public void editCourse(Course editedCourse) {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(EDIT_COURSE); 
			
			ps.setString(1, editedCourse.getCourseName());
			ps.setString(2, editedCourse.getDescription());
			ps.setInt(3, editedCourse.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.info("DAOException in SQL (edit course)");
        } finally {
        	pool.closeConnection(con, ps);
        }
	}

	@Override
	public void addCourse(Course course) {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_COURSE); 
			
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDescription());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.info("DAOException in SQL (add course)");
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
}
