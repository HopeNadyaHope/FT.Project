package by.epam.lobanok.controller.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.RunningCourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class AddRunningCourse implements Command{
	private static final String COURSE_ID = "courseID";
	private static final String TEACHER_ID = "teacherID";
	private static final String PASSING = "passing";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_RUNNING_COURSES_PAGE = "Controller?command=go_to_running_courses_page&courseID=";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		//Validator
		
		RunningCourse runningCourse = new RunningCourse();
		
		Course course = new Course();	
		int courseID;
		courseID = Integer.parseInt(request.getParameter(COURSE_ID));
		course.setId(courseID);
		runningCourse.setCourse(course);
		
		User teacher = new User();
		teacher.setId(Integer.parseInt(request.getParameter(TEACHER_ID)));
		runningCourse.setTeacher(teacher);
		
		//add start
		//add end
		
		runningCourse.setPassing(request.getParameter(PASSING));
		
		RunningCourseService runningCourseService = ServiceFactory.getInstance().getRunningCourseService();
		runningCourseService.addRunningCourse(runningCourse);
		
		response.sendRedirect(GO_TO_RUNNING_COURSES_PAGE + courseID);
	}
}
