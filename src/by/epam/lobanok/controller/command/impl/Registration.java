package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.RegistrationService;
import by.epam.lobanok.service.ServiceException;
import by.epam.lobanok.service.ServiceFactory;

public class Registration implements Command {
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String NAME = "name";
	private final String SURNAME = "surname";
	private final String AGE = "age";
	private final String SEX = "sex";
	private final String EMAIL = "email";
	private final String ROLE = "role";	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		System.out.println("REGISTRATION");	////////////////////////////////	
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
		boolean registration;
		registration = registrationService.registration(regData);
		
		if(registration) {
			request.getSession(true).setAttribute("adress", "/main.jsp");
			
			response.getWriter().println("Вы успешно зарегистрировались!");
			request.getRequestDispatcher("/main.jsp").include(request, response);
		}
		else {
			request.getSession(true).setAttribute("adress", "/registration.jsp");
			
			response.getWriter().println("При регистрации что-то пошло не так, попробуйте снова!");
			request.getRequestDispatcher("/registration.jsp").include(request, response);
			/*
			 * RequestDispatcher requestDispatcher =
			 * request.getRequestDispatcher("/registration.jsp");
			 * requestDispatcher.forward(request, response);///вывод что не зарегистрирован
			 */		//boolean переход к entrance
		}
	}
}
