package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToRunningCoursesPage implements Command{

	private static final String COURSE_ID = "courseID";
	private static final String RUNNING_COURSES = "runningCourses";
	private static final String RUNNING_COURSES_PAGE = "/runningCourses.jsp";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		CourseService courseService = ServiceFactory.getInstance().getCourseService();
		List<RunningCourse> runningCourses;
		
		int courseID;
		courseID = Integer.parseInt(request.getParameter(COURSE_ID));
		runningCourses = courseService.findRunningCourses(courseID);		
		request.setAttribute(RUNNING_COURSES, runningCourses);
		
		request.getRequestDispatcher(RUNNING_COURSES_PAGE).forward(request, response);				
	}
}
