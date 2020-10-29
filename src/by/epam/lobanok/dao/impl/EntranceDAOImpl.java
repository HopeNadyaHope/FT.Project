package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

public class EntranceDAOImpl implements EntranceDAO{
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(EntranceDAO.class);

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String FIND_USER ="SELECT users.id, users.name, users.surname, users.age, users.sex, users.email, roles.role "+
											"FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";
	
	private static final String EDIT_PROFILE = "UPDATE users SET name=?,surname=?,age=?,sex=?,email=? WHERE id=?";
	
	private static final String FIND_TEACHERS = "SELECT id,name,surname,age,sex,email FROM users WHERE users.roles_id=" +
												"(SELECT id FROM roles WHERE role='преподаватель') ORDER BY surname,name";
	
	private static final String FIND_STUDENTS = "SELECT id,name,surname,age,sex,email FROM users WHERE users.roles_id=" +
			"(SELECT id FROM roles WHERE role='студент') ORDER BY surname,name";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public User entrance(EntranceData entrData) throws DAOException {
		String login = entrData.getLogin();
		String password = entrData.getPassword();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_USER); 
			
			ps.setString(1, login);
            ps.setString(2, password);
			resultSet = ps.executeQuery();
			
			if(!resultSet.next()) {
				logger.info("NoSuchUserDAOException");
				throw new NoSuchUserDAOException();
			}			
			user = new User();  
			
			user.setId(resultSet.getInt(ID));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setAge(resultSet.getInt(AGE));
            user.setSex(resultSet.getString(SEX));
            user.setRole(resultSet.getString(ROLE));
            user.setEmail(resultSet.getString(EMAIL));
            
		}catch (SQLException e) {
			logger.info("DAOException in SQL (entrance)");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return user;
	}

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
}
