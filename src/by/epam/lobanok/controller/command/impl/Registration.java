package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.validator.Validator;
import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.RegistrationService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.DublicateUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;

public class Registration implements Command {
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String NAME = "name";
	private final String SURNAME = "surname";
	private final String AGE = "age";
	private final String SEX = "sex";
	private final String EMAIL = "email";
	private final String ROLE = "role";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String EXCEPTION_MESSAGE = "exceptionMessage";
	private final String SERVER_EXCEPTION = "Ошибка сервера";
	private final String DUBLICATE_LOGIN = "Пользователь с таким логином существует";
	private final String UNCORRECT_DATA = "Некорректные данные";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String MAIN_PAGE = "/main.jsp";
	private final String REGISTRATION_PAGE = "/registration.jsp";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {	
		RegistrationData regData = new RegistrationData();		
		regData.setLogin(request.getParameter(LOGIN));
		regData.setPassword(request.getParameter(PASSWORD));
		regData.setName(request.getParameter(NAME));
		regData.setSurname(request.getParameter(SURNAME));
		regData.setAge(Integer.parseInt(request.getParameter(AGE)));
		regData.setSex(request.getParameter(SEX));
		regData.setEmail(request.getParameter(EMAIL));
		regData.setRole(request.getParameter(ROLE));
		
		RegistrationService registrationService = ServiceFactory.getInstance().getRegistrationService();
		if(!Validator.getInstance().validateRegistrationData(regData)) {			
			request.setAttribute(SERVER_EXCEPTION, UNCORRECT_DATA);			
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);		
		}
		
		boolean registration;
		try {				
			registration = registrationService.registration(regData);
			request.setAttribute(EXCEPTION_MESSAGE, null);
			request.getRequestDispatcher(MAIN_PAGE).forward(request, response); 
			
		}catch(DublicateUserServiceException e) {
			request.setAttribute(EXCEPTION_MESSAGE, DUBLICATE_LOGIN);
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
		
		}catch(ServiceException e) {	
			request.setAttribute(SERVER_EXCEPTION, SERVER_EXCEPTION);
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
		}
	}	
}