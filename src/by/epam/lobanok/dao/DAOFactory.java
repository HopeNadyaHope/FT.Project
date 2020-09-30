package by.epam.lobanok.dao;

import by.epam.lobanok.dao.impl.EntranceDAOImpl;
import by.epam.lobanok.dao.impl.RegistrationDAOImpl;

public final class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	private final EntranceDAO entranceDAO  = new EntranceDAOImpl();
	private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();
	
	private DAOFactory() {}

	public EntranceDAO  getEntranceDAO() {
		return entranceDAO ;
	}
	
	public RegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
