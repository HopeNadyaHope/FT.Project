package by.epam.lobanok.dao;

import by.epam.lobanok.entity.RegistrationData;

public interface RegistrationDAO {
	boolean registration(RegistrationData regData) throws DAOException;

}