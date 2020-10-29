package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

public interface EntranceDAO {

	User entrance(EntranceData entrData) throws DAOException;
	void editProfile(User editedUser) throws DAOException;
	List<User> findTeachers() throws DAOException;
	List<User> findStudents() throws DAOException;
}
