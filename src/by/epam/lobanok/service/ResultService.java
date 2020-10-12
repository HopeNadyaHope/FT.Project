package by.epam.lobanok.service;

import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface ResultService {
	
	Result getCourseResult(User student, int runningCourseID) throws ServiceException;//или User user
}
