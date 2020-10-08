package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;

public interface CourseDAO {
	
	List<Course> findCourses() throws DAOException;
	List<Course> findStudentCourses(String name, String surname) throws DAOException;
	List<Course> findTeacherCourses(String name, String surname) throws DAOException;
	List<RunningCourse> findRunningCourses(int courseID) throws DAOException;
}
