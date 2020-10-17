package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.service.exception.ServiceException;

public interface CourseParticipantService {
	List<CourseParticipant> findCoursePartucepant(int runningCourseID) throws ServiceException;	
	boolean addCourseParticipant(int studentID, int runningCourseID) throws ServiceException;
}
