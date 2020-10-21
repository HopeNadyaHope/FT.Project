package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;

public interface CourseDAO {
	
	List<Course> findCourses() throws DAOException;
	Course findCourse(int courseID) throws DAOException;
	void editCourse(Course editedCourse);
	void addCourse(Course course);
	
}
