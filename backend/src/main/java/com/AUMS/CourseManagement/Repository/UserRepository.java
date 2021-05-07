package com.AUMS.CourseManagement.Repository;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.RowMapper.UserRowMapper;
import com.AUMS.CourseManagement.SQL_stmt.stmt;
import com.AUMS.CourseManagement.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DTOResponse findAll()
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_ALL_USERS,new UserRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Users were returned");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse findAllInstructor()
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_ALL_USERS,new UserRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Instructors were returned");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse findById(int id)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setResult("Success");
            response.setData(jdbcTemplate.queryForObject(stmt.GET_USER_BY_ID, new Object[]{id}, new UserRowMapper()));
            logger.info(response.getResult()+" User with "+ id+" was returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse addUser(User newUser)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.ADD_USER,newUser.getUserName(),newUser.getUserEmail(),newUser.getUserDesignation(),
            newUser.getUserLocation()));
            response.setResult("Success");
            logger.info(response.getResult()+" Adding user was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse updateUser(User newUser)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.UPDATE_USER,newUser.getUserName(), newUser.getUserEmail(),
                    newUser.getUserLocation(),newUser.getUserDesignation(), newUser.getUserId()));
            response.setResult("Success");
            logger.info(response.getResult()+" Updating user was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse deleteUser(int id)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.DELETE_USER,id));
            response.setResult("Success");
            logger.info(response.getResult()+" Deleting user was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse getInstructorId()
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_INSTRUCTOR_ID, new UserRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" Instructor's Id were returned");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse findByEmail(String email)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setResult("Success");
            response.setData(jdbcTemplate.queryForObject(stmt.GET_USER_BY_EMAIL, new String[]{email}, new UserRowMapper()));
            logger.info(response.getResult()+" User with "+email+" was returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }
}
