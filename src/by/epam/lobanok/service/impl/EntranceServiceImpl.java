package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.exception.NoSuchUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;

public class EntranceServiceImpl implements EntranceService{

	@Override
	public User entrance(EntranceData entrData) throws ServiceException {
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		User user = null; 
		try {
			user = entranceDAO.entrance(entrData); 
			} catch(NoSuchUserDAOException e) {
				throw new NoSuchUserServiceException();
			}catch (DAOException e) { 
				throw new ServiceException(e);
			} 
		return user;
	}

	@Override
	public void editProfile(User editedUser) throws ServiceException {
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		try { 
			entranceDAO.editProfile(editedUser); 
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
	}

	@Override
	public List<User> findAllTeachers() throws ServiceException {
		List<User> teachers = null;
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		try {
			teachers = entranceDAO.findTeachers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return teachers;
	}

	@Override
	public List<User> findAllStudents() throws ServiceException {
		List<User> students = null;
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		try {
			students = entranceDAO.findStudents();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return students;
	}
}
