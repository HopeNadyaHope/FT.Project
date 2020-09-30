package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lobanok.dao.DAOException;
import by.epam.lobanok.dao.RegistrationDAO;
import by.epam.lobanok.entity.RegistrationData;

public class RegistrationDAOImpl implements RegistrationDAO {
	private final String FIND_ROLE_ID = "SELECT roles.id FROM roles WHERE roles.role=?";
	private final String ADD_USER = "INSERT INTO users(login, password, name, surname, age, sex, email, roles_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String FIND_USER_BY_LOGIN ="SELECT users.name, users.login FROM users WHERE login=?";
	
	@Override
	public boolean registration(RegistrationData regData)  throws DAOException{
		System.out.println("DAO_REG");
		
		boolean registration;
		registration = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); ////////////////////////////////переделать
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/faculty?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC","root","12345"); 
			
			ps = con.prepareStatement(FIND_USER_BY_LOGIN); //////////////////Константы брать откуда-то
			ps.setString(1, regData.getLogin());
			result = ps.executeQuery();
						
			if(result.next()) {
				System.out.println("ЕСТЬ ПОЛЬЗОВАТЕЛЬ С ЛОГИНОМ +" + result.getString("login"));
				return registration;
			}			
			
			ps = con.prepareStatement(FIND_ROLE_ID); 
			ps.setString(1, regData.getRole());
			result = ps.executeQuery();
						
			if(!result.next()) {
				return registration;
			}			
			
			int role_id;
			role_id = result.getInt("id");
			System.out.println(role_id);/////////////////////////////////////////
			
			ps = con.prepareStatement(ADD_USER); 
			ps.setString(1, regData.getLogin());
			ps.setString(2, regData.getPassword());
			ps.setString(3, regData.getName());
			ps.setString(4, regData.getSurname());
			ps.setInt(5, regData.getAge()); 
			ps.setString(6, regData.getSex());
			ps.setString(7, regData.getEmail());
			ps.setInt(8, role_id);
			if(ps.executeUpdate() == 1) {
				registration = true;
			}
			
		}catch (SQLException | ClassNotFoundException e) {
            throw new DAOException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
        }
		return registration;
	}
}
