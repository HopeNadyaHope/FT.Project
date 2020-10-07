package by.epam.lobanok.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.lobanok.controller.command.impl.Entrance;
import by.epam.lobanok.controller.command.impl.go_to.GoToAboutPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToCoursesPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToMainPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToRegistrationPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToUserCoursesPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToUserPage;
import by.epam.lobanok.controller.command.impl.Localization;
import by.epam.lobanok.controller.command.impl.Registration;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandProvider(){
		commands.put(CommandName.ENTRANCE, new Entrance());
		commands.put(CommandName.REGISTRATION, new Registration());
		
		commands.put(CommandName.LOCALIZATION, new Localization());
		
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());		
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_ABOUT_PAGE, new GoToAboutPage());
		commands.put(CommandName.GO_TO_COURSES_PAGE, new GoToCoursesPage());
		commands.put(CommandName.GO_TO_USER_PAGE, new GoToUserPage());
		commands.put(CommandName.GO_TO_USER_COURSES_PAGE, new GoToUserCoursesPage());
		
	}
	
	public Command getCommand(String commandValue) {
		Command command;
		CommandName commandName;		
		commandName = CommandName.valueOf(commandValue.toUpperCase());
		command = commands.get(commandName);
		
		return command;	
	}	
}
