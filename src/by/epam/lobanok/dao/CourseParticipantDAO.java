package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.CourseParticipant;

public interface CourseParticipantDAO {
	
	List<CourseParticipant> findCourseParticipants(int runningCourseID) throws DAOException;	
	boolean addCourseParticipant(int studentID, int runningCourseID) throws DAOException;
	List<CourseParticipant> findCoursesParticipantResults(int studentID) throws DAOException;
}
