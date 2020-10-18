package by.epam.lobanok.dao;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.EditData;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

public interface EntranceDAO {
	
	//User entrance(String login, String password) throws DAOException;
	User entrance(EntranceData entrData) throws DAOException;
	void editProfile(EditData editData) throws DAOException;
}
