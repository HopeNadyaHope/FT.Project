package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface CourseService {
	
	List<Course> findCourses() throws ServiceException;
	List<Course> findUserCourses(User user) throws ServiceException;
}
