package by.epam.lobanok.service.impl;

import by.epam.lobanok.dao.DAOException;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.RegistrationDAO;
import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.RegistrationService;
import by.epam.lobanok.service.ServiceException;

public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public boolean registration(RegistrationData regData) throws ServiceException {
		System.out.println(regData);////////////////////////////////////////////
		RegistrationDAO registrationDAO = DAOFactory.getInstance().getRegistrationDAO(); 
		boolean registration;
		try { 
			System.out.println("SERVICE_REG"); ///////////////////////////////////////////
			registration = registrationDAO.registration(regData); 
			} catch (DAOException e) { 
				throw new ServiceException(e);
			} 
		return registration;
	}
}
