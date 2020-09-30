package by.epam.lobanok.service;

import by.epam.lobanok.service.impl.EntranceServiceImpl;
import by.epam.lobanok.service.impl.RegistrationServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final EntranceService enterenceService = new EntranceServiceImpl();
	private final RegistrationService registrationService = new RegistrationServiceImpl();
	
	private ServiceFactory() {}

	public EntranceService getEntranceService() {
		return enterenceService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
}
