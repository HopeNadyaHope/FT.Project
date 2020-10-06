package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.exception.ServiceException;

public class Localization implements Command{

	private final String LOCAL = "local";
	private final String LAST_COMMAND = "lastCommand";
	private final String CONTROLLER_COMMAND = "Controller?command=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL, request.getParameter(LOCAL));
		
		String lastCommand; 
		lastCommand = (String) session.getAttribute(LAST_COMMAND);
		response.sendRedirect(CONTROLLER_COMMAND + lastCommand);
	}

}
