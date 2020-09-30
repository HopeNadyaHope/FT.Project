package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.ServiceException;

public class Localization implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		
		String adress;
		adress = (String) session.getAttribute("adress");
		request.getRequestDispatcher(adress).forward(request, response);
	}

}
