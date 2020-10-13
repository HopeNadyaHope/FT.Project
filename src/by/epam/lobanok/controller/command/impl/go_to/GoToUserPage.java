package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToUserPage implements Command{
	
	private final String USER_PAGE = "/WEB-INF/jsp/userPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		request.getRequestDispatcher(USER_PAGE).forward(request, response);		
	}
}
