package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.Course;
import by.epam.lobanok.service.exception.ServiceException;

public interface CourseService {	
	List<Course> findCourses() throws ServiceException;
	Course findCourse(int courseID) throws ServiceException;
	void editCourse(Course editedCourse);
	void addCourse(Course course);
}
