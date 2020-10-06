package by.epam.lobanok.service;

import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.exception.ServiceException;

public interface RegistrationService {
	boolean registration(RegistrationData loginationData) throws ServiceException;
}
