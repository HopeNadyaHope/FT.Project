package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.ServiceException;

public class GoToCoursesPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		request.getSession(true).setAttribute("adress", "/courses.jsp");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/courses.jsp");
		requestDispatcher.forward(request, response);		
	}
}
