package by.epam.lobanok.dao;

import by.epam.lobanok.entity.User;

public interface EntranceDAO {
	
	User entrance(String login, String password) throws DAOException;
}
