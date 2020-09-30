package by.epam.lobanok.service;

import by.epam.lobanok.entity.User;

public interface EntranceService {
	User entrance(String login, String password) throws ServiceException;
}
