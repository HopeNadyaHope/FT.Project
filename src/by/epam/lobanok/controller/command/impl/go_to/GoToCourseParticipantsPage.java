package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.service.CourseParticipantService;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToCourseParticipantsPage implements Command{
	private static final String RUNNING_COURSE = "runningCourse";
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String COURSE_PARTICIPANTS = "courseParticipants";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String USER_COURSES_PARTICIPANTS_PAGE = "WEB-INF/jsp/userCourseParticipantsPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		int runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		CourseParticipantService courseParticepantService = ServiceFactory.getInstance().getCourseParticipantService();
		List<CourseParticipant> courseParticipants;
		courseParticipants = courseParticepantService.findCoursePartucepant(runningCourseID);
		request.setAttribute(COURSE_PARTICIPANTS, courseParticipants);
		
		
		CourseService courseService = ServiceFactory.getInstance().getCourseService();
		RunningCourse runningCourse;
		runningCourse = courseService.findRunningCourse(runningCourseID);
		request.setAttribute(RUNNING_COURSE, runningCourse);
		
		request.getRequestDispatcher(USER_COURSES_PARTICIPANTS_PAGE).forward(request, response);			
	}

}
