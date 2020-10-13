package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.service.CourseParticipantService;
import by.epam.lobanok.service.exception.ServiceException;

public class CourseParticipantServiceImpl implements CourseParticipantService {

	@Override
	public List<CourseParticipant> findCoursePartucepant(int runningCourseID) throws ServiceException {
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO(); 
		
		List<CourseParticipant> courseParticipants;
		try {
			courseParticipants = courseParticipantDAO.findCourseParticipants(runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return courseParticipants;
	}
}
