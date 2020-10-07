package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToUserCoursesPage implements Command{

	private final String USER = "user";
	private final String COURSES = "courses";
	
	private final String USER_COURSES_PAGE = "/WEB-INF/jsp/userCoursesPage.jsp";////
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		CourseService courseService = ServiceFactory.getInstance().getCourseService();
		List<Course> courses;
		courses = courseService.findUserCourses((User)request.getSession().getAttribute(USER));		
		
		request.setAttribute(COURSES, courses);
		request.getRequestDispatcher(USER_COURSES_PAGE).forward(request, response);			
	}
}