package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	private final String ID = "id";
	private final String NAME = "name";
	private final String SURNAME = "surname";
	private final String AGE = "age";
	private final String SEX = "sex";
	private final String EMAIL = "email";
	private final String ROLE = "role";	
	
	
	private final String FIND_USER ="SELECT users.id, users.name, users.surname, users.age, users.sex, users.email, roles.role "
			+ "FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";

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
			logger.info("DAOException in SQL");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return user;
	}
}
