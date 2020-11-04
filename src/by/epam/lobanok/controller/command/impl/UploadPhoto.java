package by.epam.lobanok.controller.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class UploadPhoto implements Command {

	private static final String FILE = "file";
	private static final String USER = "user";
	private static final String USER_PHOTO_PATH = "D:\\Тренинг\\Лабы\\ПРОЕКТ\\img\\userPhoto\\";
	private static final String JPG = ".jpg";
	
	private static final String GO_TO_USER_PAGE = "Controller?command=go_to_user_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
	
		Part file;
		InputStream inputStream = null;
		OutputStream outStream = null;

		try {
			file = request.getPart(FILE);
			inputStream = file.getInputStream();

			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();

			User user = (User)request.getSession().getAttribute(USER);
			int userID;
			userID = user.getId();
			
			String photoURL;
			photoURL = USER_PHOTO_PATH + userID + JPG;
			
			File photoFile = new File(photoURL);

			if (!photoFile.exists()) {
				photoFile.createNewFile();
			}

			outStream = new FileOutputStream(photoFile);
			outStream.write(buffer);			
			outStream.close();
			
			if(user.getPhotoURL() == null) {
				UserService userService = ServiceFactory.getInstance().getUserService();
				userService.updatePhotoURL(userID,photoURL);
				
				user.setPhotoURL(photoURL);
				request.getSession().setAttribute(USER, user);
			}
		} catch (IOException e1) {
			e1.printStackTrace();/////////////////////////////////////////////////////////////////////////
		} catch (ServletException e1) {
			e1.printStackTrace();/////////////////////////////////////////////////////////////////////////
		}
			response.sendRedirect(GO_TO_USER_PAGE);
	}

}
