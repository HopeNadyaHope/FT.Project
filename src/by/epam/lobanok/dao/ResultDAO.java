package by.epam.lobanok.dao;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Result;

public interface ResultDAO {

	Result getCourseResult(int studentID, int runningCourseID) throws DAOException;
	void addResult(int coursePartisipantID, int rating, String review) throws DAOException;
}
