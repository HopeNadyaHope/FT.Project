package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.User;

public class CourseParticipantDAOImpl implements CourseParticipantDAO{

	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(CourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////
	private static final String FIND_COURSE_PARTICIPANTS = "SELECT course_participants.id, users.id as userID, users.name, users.surname, "+
			"results.rating, results.review "+
			"FROM course_participants "+
			"JOIN users ON course_participants.users_id = users.id "+
			"LEFT OUTER JOIN results on course_participants.results_id = results.id "+
			"WHERE course_participants.running_courses_id = ?";

	/////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String USER_ID = "userID";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String RATING = "rating";
	private static final String REVIEW = "review";	
	
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<CourseParticipant> findCourseParticipants(int runningCourseID) throws DAOException {
		List<CourseParticipant> courseParticipants = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipant;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE_PARTICIPANTS);
			ps.setInt(1, runningCourseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                courseParticipant = new CourseParticipant();
                courseParticipant.setId(Integer.parseInt(resultSet.getString(ID)));
                
				
				  User student = new User();
				  student.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				  student.setName(resultSet.getString(NAME));
				  student.setSurname(resultSet.getString(SURNAME));
				  courseParticipant.setStudent(student);
				 
                Result result = new Result();
                //result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                result.setReview(resultSet.getString(REVIEW));
                courseParticipant.setResult(result);

                courseParticipants.add(courseParticipant);
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return courseParticipants;
	}

}
