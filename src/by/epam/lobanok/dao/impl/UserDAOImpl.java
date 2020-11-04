package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.UserDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.User;

/**
 * Implementation of UserDAO 
 *
 * @author hope_nadya_hope
 */
public class UserDAOImpl implements UserDAO {
	/**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a UserDAO.class
     */
	private static final Logger logger = LogManager.getLogger(User.class);

	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to edit user's profile
     */
	private static final String EDIT_PROFILE = "UPDATE users SET name=?,surname=?,age=?,sex=?,email=? WHERE id=?";
	
	/**
     * SQL statement to find all teachers
     */
	private static final String FIND_TEACHERS = "SELECT id,name,surname,age,sex,email FROM users WHERE users.roles_id=" +
												"(SELECT id FROM roles WHERE role='преподаватель') ORDER BY surname,name";
	
	/**
     * SQL statement to find all students
     */
	private static final String FIND_STUDENTS = "SELECT id,name,surname,age,sex,email FROM users WHERE users.roles_id=" +
			"(SELECT id FROM roles WHERE role='студент') ORDER BY surname,name";
	
	/**
     * SQL statement to update photoURL for user
     */
	private static final String UPDATE_PHOTO_URL = "UPDATE users SET photo_url=? WHERE id=?";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";


	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Edits user SQL
     *
     * @param a user to edit
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void editProfile(User editedUser) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(EDIT_PROFILE); 
			
			ps.setString(1, editedUser.getName());
			ps.setString(2, editedUser.getSurname());
			ps.setInt(3, editedUser.getAge());
			ps.setString(4, editedUser.getSex());
			ps.setString(5, editedUser.getEmail());
            ps.setInt(6, editedUser.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.info("DAOException in SQL (edit profile)");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
	

	/**
     * Finds all teachers in a system SQL
     *
     * @return List of teachers
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<User> findTeachers() throws DAOException {		
		List<User> teachers = new ArrayList<User>();
		User teacher;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHERS);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                teacher = new User();
                teacher.setId(Integer.parseInt(resultSet.getString(ID)));
				teacher.setName(resultSet.getString(NAME));
				teacher.setSurname(resultSet.getString(SURNAME));
				teacher.setAge(resultSet.getInt(AGE));
				teacher.setSex(resultSet.getString(SEX));
				teacher.setEmail(resultSet.getString(EMAIL));
				
                teachers.add(teacher);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return teachers;
	}

	
	/**
     * Finds all students in a system SQL
     *
     * @return List of students
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<User> findStudents() throws DAOException {
		List<User> students = new ArrayList<User>();
		User student;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENTS);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				student = new User();
				student.setId(Integer.parseInt(resultSet.getString(ID)));
				student.setName(resultSet.getString(NAME));
				student.setSurname(resultSet.getString(SURNAME));
				student.setAge(resultSet.getInt(AGE));
				student.setSex(resultSet.getString(SEX));
				student.setEmail(resultSet.getString(EMAIL));
				
				students.add(student);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return students;
	}

	
	/**
     * Updates photoURL for a given user
     *
     * @param ID of a given user
     * @param new photoURL
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void updatePhotoURL(int userID, String photoURL) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(UPDATE_PHOTO_URL); 
			
			ps.setString(1, photoURL);
			ps.setInt(2, userID);
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.info("DAOException in SQL (update photo url)");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
}
