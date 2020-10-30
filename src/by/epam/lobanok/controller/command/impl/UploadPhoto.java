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
import by.epam.lobanok.service.exception.ServiceException;

public class UploadPhoto implements Command {

	private static final String FILE = "file";
	private static final String USER = "user";
	private static final String USER_PHOTO_PATH = "D:\\Тренинг\\Лабы\\ПРОЕКТ\\WELCOME\\WebProj\\WebContent\\images\\userPhoto\\";
	private static final String JPG = ".jpg";
	
	private static final String GO_TO_USER_PAGE = "Controller?command=go_to_user_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		System.out.println(request.getContextPath());
		
		Part file;
		InputStream inputStream = null;
		OutputStream outStream = null;

		try {
			file = request.getPart(FILE);
			inputStream = file.getInputStream();

			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);

			User user = (User)request.getSession().getAttribute(USER);
			int userID;
			userID = user.getId();
			
			File targetFile = new File(USER_PHOTO_PATH + userID + JPG);

			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}

			outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);

		} catch (IOException e1) {
			e1.printStackTrace();/////////////////////////////////////////////////////////////////////////
		} catch (ServletException e1) {
			e1.printStackTrace();/////////////////////////////////////////////////////////////////////////
		}

		try {
			response.sendRedirect(GO_TO_USER_PAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
