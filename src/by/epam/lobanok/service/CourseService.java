package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface CourseService {
	
	List<Course> findCourses() throws ServiceException;
	
	List<RunningCourse> findUserCourses(User user) throws ServiceException;
	List<RunningCourse> findRunningCourses(int courseID) throws ServiceException;
	
	RunningCourse findRunningCourse(int runningCourseID) throws ServiceException;
}
