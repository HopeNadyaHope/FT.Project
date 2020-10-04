package by.epam.lobanok.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.entity.User;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		String login =request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName("Nadezhda");
		user.setSurname("Lobanok");
		
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		requestDispatcher.forward(request, response);
		
		
		/*
		 * response.setCharacterEncoding("cp1251"); PrintWriter out =
		 * response.getWriter(); out.println("Логин: " + login); out.println("<br />");
		 * out.println("Пароль: " + password);
		 *//////////////получить и вывести пароль логин
		
		
		 
		 /////перейти на новую страницу
		System.out.println("NADEZHDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

}
