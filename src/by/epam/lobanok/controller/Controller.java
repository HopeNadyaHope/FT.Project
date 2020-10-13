package by.epam.lobanok.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.command.CommandProvider;
import by.epam.lobanok.service.exception.ServiceException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final CommandProvider commands = new CommandProvider();
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private final String COMMAND = "command";
	private final String LOCALIZATION = "localization";
	private final String LAST_COMMAND = "lastCommand";

	/////////////////////////////////////////////////////////////////////////////////////////////
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			//log
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			//log
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
		response.setContentType("text/html");
		
		String currentCommand; 
		Command command;		  
		currentCommand = request.getParameter(COMMAND);
		
		if(!currentCommand.equals(LOCALIZATION)) {
			request.getSession(true).setAttribute(LAST_COMMAND, currentCommand);	
		}
		
		command = commands.getCommand(currentCommand); 
		command.execute(request, response);
	}
}
