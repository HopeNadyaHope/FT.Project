package by.epam.lobanok.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.command.CommandProvider;
import by.epam.lobanok.service.ServiceException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String COMMAND = "command";
	private final CommandProvider commands = new CommandProvider();

    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
		response.setContentType("text/html");
		String currentCommand; 
		Command command;
		  
		currentCommand = request.getParameter(COMMAND);		  
		command = commands.getCommand(currentCommand); 
		
		//System.out.println(currentCommand);
		command.execute(request, response);
		/*////////////////////////////////////////////////////////////////////////////////////THROWS
		 * RequestDispatcher requestDispatcher =
		 * request.getRequestDispatcher("/WEB-INF/jsp/entrance.jsp");
		 * requestDispatcher.forward(request, response);
		 */
	}

}
