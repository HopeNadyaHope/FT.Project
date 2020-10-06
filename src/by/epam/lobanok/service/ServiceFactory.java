package by.epam.lobanok.service;

import by.epam.lobanok.service.impl.CourseServiceImpl;
import by.epam.lobanok.service.impl.EntranceServiceImpl;
import by.epam.lobanok.service.impl.RegistrationServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final EntranceService enterenceService = new EntranceServiceImpl();
	private final RegistrationService registrationService = new RegistrationServiceImpl();
	private final CourseService courseService = new CourseServiceImpl();
	
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
}
