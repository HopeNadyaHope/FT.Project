package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

public class EntranceDAOImplTest {
	
	private static final String FIND_USER ="SELECT users.id, users.name, users.surname, users.age, users.sex, users.email, roles.role, users.photo_url "+
			"FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";	
	private static final String PHOTO_URL = "photo_url";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void entranceTest() {
		EntranceData entrData = new EntranceData();
		entrData.setLogin("vasya");
		entrData.setPassword("22222");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		User expectedUser = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/testdb?useSSL=false&serverTimezone=UTC","root","12345");
			ps = con.prepareStatement(FIND_USER); 
			
			ps.setString(1,entrData.getLogin());
            ps.setString(2, entrData.getPassword());
			resultSet = ps.executeQuery();
			
			if(resultSet.next()) {			
				expectedUser = new User();			
				expectedUser.setId(resultSet.getInt(ID));
				expectedUser.setName(resultSet.getString(NAME));
				expectedUser.setSurname(resultSet.getString(SURNAME));
				expectedUser.setAge(resultSet.getInt(AGE));
				expectedUser.setSex(resultSet.getString(SEX));
				expectedUser.setEmail(resultSet.getString(EMAIL));
				expectedUser.setRole(resultSet.getString(ROLE));
				expectedUser.setPhotoURL(resultSet.getString(PHOTO_URL)); 
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		EntranceDAO entranceDAO = new EntranceDAOImpl();
		User actualUser = null;
		try {
			actualUser = entranceDAO.entrance(entrData); 
			}catch(NoSuchUserDAOException e) {
				System.out.println("No such user");
			}catch (DAOException e) { 
				e.printStackTrace();
			} 
		org.junit.Assert.assertEquals(expectedUser, actualUser);
	}
}
