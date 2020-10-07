package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

public class EntranceDAOImpl implements EntranceDAO{
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private final String NAME = "name";
	private final String SURNAME = "surname";
	private final String AGE = "age";
	private final String SEX = "sex";
	private final String EMAIL = "email";
	private final String ROLE = "role";	
	
	
	private final String FIND_USER ="SELECT users.name, users.surname, users.age, users.sex, users.email, roles.role "
			+ "FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";

	@Override
	public User entrance(EntranceData entrData) throws DAOException {
		String login = entrData.getLogin();
		String password = entrData.getPassword();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		User user = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_USER); 
			
			ps.setString(1, login);
            ps.setString(2, password);
			result = ps.executeQuery();
			
			if(!result.next()) {
				throw new NoSuchUserDAOException();
			}			
			user = new User();                
            user.setName(result.getString(NAME));
            user.setSurname(result.getString(SURNAME));
            user.setAge(result.getInt(AGE));
            user.setSex(result.getString(SEX));
            user.setRole(result.getString(ROLE));
            user.setEmail(result.getString(EMAIL));
            
		}catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return user;
	}
}
