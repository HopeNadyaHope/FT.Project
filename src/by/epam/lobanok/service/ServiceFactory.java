package by.epam.lobanok.service;

import by.epam.lobanok.service.impl.CourseParticipantServiceImpl;
import by.epam.lobanok.service.impl.CourseServiceImpl;
import by.epam.lobanok.service.impl.EntranceServiceImpl;
import by.epam.lobanok.service.impl.RegistrationServiceImpl;
import by.epam.lobanok.service.impl.ResultServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final EntranceService enterenceService = new EntranceServiceImpl();
	private final RegistrationService registrationService = new RegistrationServiceImpl();
	private final CourseService courseService = new CourseServiceImpl();
	private final ResultService resultService = new ResultServiceImpl();
	private final CourseParticipantService courseParticipantService = new CourseParticipantServiceImpl();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}

	public EntranceService getEntranceService() {
		return enterenceService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}
	
	public CourseService getCourseService() {
		return courseService;
	}
	
	public ResultService getResultService() {
		return resultService;
	}
	
	public CourseParticipantService getCourseParticipantService() {
		return courseParticipantService;
	}
}
