package by.epam.lobanok.dao;

import by.epam.lobanok.dao.impl.CourseDAOImpl;
import by.epam.lobanok.dao.impl.EntranceDAOImpl;
import by.epam.lobanok.dao.impl.RegistrationDAOImpl;
import by.epam.lobanok.dao.impl.ResultDAOImpl;

public final class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private final EntranceDAO entranceDAO  = new EntranceDAOImpl();
	private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();
	private final CourseDAO courseDAO = new CourseDAOImpl();
	private final ResultDAO resultDAO = new ResultDAOImpl();
	
	private DAOFactory() {}	
	
	public static DAOFactory getInstance() {
		return instance;
	}

	public EntranceDAO  getEntranceDAO() {
		return entranceDAO ;
	}
	
	public RegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	
	public ResultDAO getResultDAO() {
		return resultDAO;
	}
}
