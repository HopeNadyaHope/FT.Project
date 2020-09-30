package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.ServiceException;
import by.epam.lobanok.service.ServiceFactory;


public class Entrance implements Command {
	
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException{
		System.out.println("ENTRANCE");
				
		String login; 
		String password;
		login = request.getParameter(LOGIN);
		password = request.getParameter(PASSWORD);
		
		EntranceService entranceService = ServiceFactory.getInstance().getEntranceService();
		User user;
		user = entranceService.entrance(login, password);
	
		
		if(user == null) {
			request.getSession(true).setAttribute("adress", "/main.jsp");			
			response.getWriter().println("Неверные входные данные");
			request.getRequestDispatcher("/main.jsp").include(request, response);
			/*
			 * RequestDispatcher requestDispatcher =
			 * request.getRequestDispatcher("/main.jsp"); requestDispatcher.forward(request,
			 * response);
			 */
		}
		else {
			//request.setAttribute("user", user);
			HttpSession session = request.getSession(true);
			session.setAttribute("adress", "/WEB-INF/jsp/userPage.jsp");
			session.setAttribute("user", user);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
			requestDispatcher.forward(request, response);
			 
			/*
			 * response.getWriter().println("Неверные входные данные");
			 * request.getRequestDispatcher("/WEB-INF/jsp/entrance.jsp").include(request,
			 * response);
			 */
		}
	}
}
