package by.epam.lobanok.service.impl;

import by.epam.lobanok.dao.DAOException;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.ServiceException;

public class EntranceServiceImpl implements EntranceService{

	@Override
	public User entrance(String login, String password) throws ServiceException{
		
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		User user = null; 
		try { 
			System.out.println("SERVICE"); 
			user = entranceDAO.entrance(login, password); 
			} catch (DAOException e) { 
				throw new ServiceException(e);
			} 
		return user;
		 
		/*
		 * System.out.println("SERVICE");
		 * 
		 * User user = new User(); user.setAge(20); user.setName("Nadezhda"); return
		 * user;
		 */
	}
}
