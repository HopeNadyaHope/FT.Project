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
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String LOGIN = "login";
	private final String PASSWORD = "password";	
	private final String USER = "user";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String EXCEPTION_MESSAGE_ATTRIBUTE = "&exceptionMessage=";
	private final String SERVER_EXCEPTION = "Ошибка сервера";
	private final String NO_SUCH_USER = "Нет пользователя с таким логином и паролем";
	private final String UNCORRECT_DATA = "Некорректные данные";
	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	private final String GO_TO_USER_PAGE = "Controller?command=go_to_user_page";
	private final String GO_TO_MAIN_PAGE = "Controller?command=go_to_main_page";
		
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException{	
		String page;
		
		EntranceData entrData = new EntranceData();
		entrData.setLogin(request.getParameter(LOGIN));
		entrData.setPassword(request.getParameter(PASSWORD));
		
		if(!Validator.getInstance().validateEntranceData(entrData)) {
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + UNCORRECT_DATA;
		}
		
		EntranceService entranceService = ServiceFactory.getInstance().getEntranceService();
		User user;
		try {
			user = entranceService.entrance(entrData);
			request.getSession(true).setAttribute(USER, user);
			page = GO_TO_USER_PAGE;		
		}catch(NoSuchUserServiceException e) {	
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + NO_SUCH_USER;
			
		}catch(ServiceException e) {
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + SERVER_EXCEPTION;
		}
		response.sendRedirect(page);
	}	
}
