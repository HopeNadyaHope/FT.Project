package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface EntranceService {
	User entrance(EntranceData entrData) throws ServiceException;
	void editProfile(User editedUser) throws ServiceException;
	List<User> findAllTeachers() throws ServiceException;
}
