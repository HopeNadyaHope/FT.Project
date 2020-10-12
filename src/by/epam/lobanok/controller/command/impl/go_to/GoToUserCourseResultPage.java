package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ResultService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToUserCourseResultPage implements Command{

	private static final String USER_COURSES_RESULT_PAGE = "WEB-INF/jsp/userCourseResultPage.jsp";
	
	//private static final String RUNNING_COURSE = "runningCourse";
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String USER = "user";
	private static final String COURSE_RESULT = "courseResult";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		ResultService resultService = ServiceFactory.getInstance().getResultService();
		//сохранить сведения о курсах
		User student;
		int runningCourseID;
		student = (User)request.getSession().getAttribute(USER);
		runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		Result result;
		result = resultService.getCourseResult(student, runningCourseID);		
		request.setAttribute(COURSE_RESULT, result);
		//мб класть в сессиию?
		
		request.getRequestDispatcher(USER_COURSES_RESULT_PAGE).forward(request, response);			
	}
}
