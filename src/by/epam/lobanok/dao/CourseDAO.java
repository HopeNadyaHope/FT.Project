package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;

public interface CourseDAO {
	
	List<Course> findCourses() throws DAOException;	
	List<RunningCourse> findRunningCourses(int courseID) throws DAOException;
	
	List<RunningCourse> findStudentCourses(int userID) throws DAOException;
	List<RunningCourse> findTeacherCourses(int userID) throws DAOException;
	
	RunningCourse findRunningCourse(int runningCourseID) throws DAOException;
	
}
