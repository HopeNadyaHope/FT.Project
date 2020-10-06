package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService{

	@Override
	public List<Course> findCourses() throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		
		List<Course> courses;
		try {
			courses = courseDAO.findCourses();
		} catch (DAOException e) {
			//log
			throw new ServiceException(e);
		}
		return courses;
	}
	
	public List<Course> findUserCourses(User user) throws ServiceException {//мб заменить на running-courses
		String name;
		String surname;
		name = user.getName();
		surname = user.getSurname();
		
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 		
		List<Course> courses = null;
		
		try {

			if(user.getRole().equals("студент")) {
				courses = courseDAO.findStudentCourses(name, surname);//мб комманд
			}
			if(user.getRole().equals("преподаватель")) {
				courses = courseDAO.findTeacherCourses(name, surname);			
			}
		} catch (DAOException e) {
			//log
			throw new ServiceException(e);
		}
		return courses;
	}

}
