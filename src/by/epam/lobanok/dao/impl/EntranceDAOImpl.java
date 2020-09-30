package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lobanok.dao.DAOException;
import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.entity.User;

public class EntranceDAOImpl implements EntranceDAO{

	//static final String FIND_USER = "SELECT * FROM users WHERE login=? AND password=?";

	
	private final String FIND_USER ="SELECT users.name, users.surname, users.age, users.sex, users.email, roles.role "
			  						+ "FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";
	 
	@Override
	public User entrance(String login, String password) throws DAOException{
		System.out.println("DAO");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		User user = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); ////////////////////////////////переделать
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/faculty?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC","root","12345"); 
			
			ps = con.prepareStatement(FIND_USER); 
			ps.setString(1, login);
            ps.setString(2, password);
			result = ps.executeQuery();
			
			if(result.next()) {
                user = new User();
                
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setAge(result.getInt("age"));
                user.setSex(result.getString("sex"));
                user.setRole(result.getString("role"));
                user.setEmail(result.getString("email"));
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

        return user;
	}
}
