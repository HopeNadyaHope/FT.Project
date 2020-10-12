package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService{

	private static final String STUDENT = "студент";
	private static final String TEACHER = "преподаватель";
	
	@Override
	public List<Course> findCourses() throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		
		List<Course> courses;
		try {
			courses = courseDAO.findCourses();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return courses;
	}
	
	public List<RunningCourse> findUserCourses(User user) throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 		
		List<RunningCourse> runningCourses = null;
		
		int userID;
		userID = user.getId();
		
		try {
			switch(user.getRole()) {
			case STUDENT:
				runningCourses = courseDAO.findStudentCourses(userID);
				break;
			case TEACHER:
				runningCourses  = courseDAO.findTeacherCourses(userID);	
				break;
			}
			
			/*if(user.getRole().equals(STUDENT)) {
				runningCourses = courseDAO.findStudentCourses(userID);
			}
			if(user.getRole().equals(TEACHER)) {
			//	runningCourses  = courseDAO.findTeacherCourses(userID);			
			}*/
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return runningCourses;
	}
	
	@Override
	public List<RunningCourse> findRunningCourses(int courseID) throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		
		List<RunningCourse> runningCourses;
		try {
			runningCourses = courseDAO.findRunningCourses(courseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return runningCourses;
	}

}
