package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.validator.Validator;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.NoSuchUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;


public class Entrance implements Command {
	
	private final String LAST_COMMAND = "lastCommand";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String LOGIN = "login";
	private final String PASSWORD = "password";	
	private final String USER = "user";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String EXCEPTION_MESSAGE = "exceptionMessage";
	private final String SERVER_EXCEPTION = "Ошибка сервера";
	private final String NO_SUCH_USER = "Нет пользователя с таким логином и паролем";
	private final String UNCORRECT_DATA = "Некорректные данные";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String MAIN_PAGE = "/main.jsp";
	private final String USER_PAGE = "/WEB-INF/jsp/userPage.jsp";	
	private final String GO_TO_USER_PAGE = "go_to_user_page";
		
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException{	
		EntranceData entrData = new EntranceData();
		entrData.setLogin(request.getParameter(LOGIN));
		entrData.setPassword(request.getParameter(PASSWORD));
		
		if(!Validator.getInstance().validateEntranceData(entrData)) {
			request.setAttribute(EXCEPTION_MESSAGE, UNCORRECT_DATA);			
			request.getRequestDispatcher(MAIN_PAGE).forward(request, response);			
		}
		
		EntranceService entranceService = ServiceFactory.getInstance().getEntranceService();
		User user;
		try {
			user = entranceService.entrance(entrData);
			
			request.setAttribute(EXCEPTION_MESSAGE, null);
			request.getSession(true).setAttribute(USER, user);
			
			request.getSession(true).setAttribute(LAST_COMMAND, GO_TO_USER_PAGE);				
			request.getRequestDispatcher(USER_PAGE).forward(request, response);
			
		}catch(NoSuchUserServiceException e) {
			request.setAttribute(EXCEPTION_MESSAGE, NO_SUCH_USER);
			request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
			
		}catch(ServiceException e) {
			request.setAttribute(EXCEPTION_MESSAGE, SERVER_EXCEPTION);
			request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
		}
	}	
}
