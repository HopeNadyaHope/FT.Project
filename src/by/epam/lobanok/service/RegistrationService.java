package by.epam.lobanok.service;

import by.epam.lobanok.entity.RegistrationData;

public interface RegistrationService {
	boolean registration(RegistrationData loginationData) throws ServiceException;
}
