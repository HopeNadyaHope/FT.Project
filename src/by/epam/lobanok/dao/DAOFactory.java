package by.epam.lobanok.dao;

import by.epam.lobanok.dao.impl.CourseDAOImpl;
import by.epam.lobanok.dao.impl.CourseParticipantDAOImpl;
import by.epam.lobanok.dao.impl.EntranceDAOImpl;
import by.epam.lobanok.dao.impl.RegistrationDAOImpl;
import by.epam.lobanok.dao.impl.ResultDAOImpl;
import by.epam.lobanok.dao.impl.RunningCourseDAOImpl;

public final class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final EntranceDAO entranceDAO  = new EntranceDAOImpl();
	private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();
	private final CourseDAO courseDAO = new CourseDAOImpl();
	private final RunningCourseDAO runningCourseDAO = new RunningCourseDAOImpl();
	private final ResultDAO resultDAO = new ResultDAOImpl();
	private final CourseParticipantDAO courseParticipantDAO = new CourseParticipantDAOImpl();

	/////////////////////////////////////////////////////////////////////////////////////////////
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
	
	public RunningCourseDAO getRunningCourseDAO() {
		return runningCourseDAO;
	}
	
	public ResultDAO getResultDAO() {
		return resultDAO;
	}
	
	public CourseParticipantDAO getCourseParticipantDAO() {
		return courseParticipantDAO;
		
	}
}
