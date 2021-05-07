package com.AUMS.CourseManagement.Repository;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.RowMapper.SessionRowMapper;
import com.AUMS.CourseManagement.RowMapper.TrainingRowMapper;
import com.AUMS.CourseManagement.SQL_stmt.stmt;
import com.AUMS.CourseManagement.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepository {
    public static final Logger logger = LoggerFactory.getLogger(TrainingRepository.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public TrainingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DTOResponse findAll()
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_TRAINING_DETAILS, new TrainingRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Training were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }
    public DTOResponse addTraining(Training newTraining)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.ADD_TRAINING,newTraining.getCourseId(),newTraining.getInstructorId()));
            response.setResult("Success");
            logger.info(response.getResult()+" Adding Training was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse deleteTraining(int id)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.DELETE_TRAINING,id));
            response.setResult("Success");
            logger.info(response.getResult()+" Deleting course was successful");
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
            response.setData(jdbcTemplate.queryForObject(stmt.GET_TRAINING_BY_ID, new Object[]{id}, new TrainingRowMapper()));
            logger.info(response.getResult()+" All Training were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse updateTraining(Training newTraining)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.UPDATE_TRAINING,newTraining.getCourseId(),newTraining.getInstructorId(),
                    newTraining.getTrainingId()));
            response.setResult("Success");
            logger.info(response.getResult()+" Updating Training was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse assignTraining(int userId, int trainingId)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.ASSIGN_TRAINING,userId,trainingId));
            response.setResult("Success");
            logger.info(response.getResult()+" Assigning course to user_id "+ userId + " was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse getAssignedTraining(int id)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setResult("Success");
            response.setData(jdbcTemplate.query(stmt.GET_ASSIGNED_TRAINING, new Object[]{id}, new SessionRowMapper()));
            logger.info(response.getResult()+" All Assigned Training were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse feedback(int id,int rating)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.TRAINING_FEEDBACK,rating,id));
            response.setResult("Success");
            logger.info(response.getResult()+" Training feedback was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

}
