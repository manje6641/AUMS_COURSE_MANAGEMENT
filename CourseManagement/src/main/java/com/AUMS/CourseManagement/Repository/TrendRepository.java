package com.AUMS.CourseManagement.Repository;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.SQL_stmt.stmt;
import com.AUMS.CourseManagement.model.Trend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrendRepository {
    public static final Logger logger = LoggerFactory.getLogger(TrendRepository.class);
@Autowired
    private final JdbcTemplate jdbcTemplate;

    public TrendRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DTOResponse getTypeOfUser()
    {
        DTOResponse response = new DTOResponse();
        try{
            response.setData(jdbcTemplate.query(stmt.GET_USER_TYPE, (rs, rowNum) ->
                    new Trend(rs.getInt("total"),
                            rs.getString("category"))));
            response.setResult("Success");
            logger.info(response.getResult()+" No. of user grouped by designation were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse getCourseByLocation()
    {
        DTOResponse response = new DTOResponse();
        try{
            response.setData(jdbcTemplate.query(stmt.GET_COURSE_BY_LOCATION, (rs, rowNum) ->
                    new Trend(rs.getInt("total"),
                            rs.getString("category"))));
            response.setResult("Success");
            logger.info(response.getResult()+" All Courses grouped by location were returned ");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse getCourseByYear()
    {
        DTOResponse response = new DTOResponse();
        try{
            response.setData(jdbcTemplate.query(stmt.GET_COURSE_BY_YEAR, (rs, rowNum) ->
                    new Trend(rs.getInt("total"),
                            rs.getString("category"))));
            response.setResult("Success");
            logger.info(response.getResult()+" All Courses grouped by year were returned ");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }
    public DTOResponse getStudentByLocation()
    {
        DTOResponse response = new DTOResponse();
        try{
            response.setData(jdbcTemplate.query(stmt.GET_STUDENT_BY_LOCATION, (rs, rowNum) ->
                    new Trend(rs.getInt("total"),
                            rs.getString("category"))));
            response.setResult("Success");
            logger.info(response.getResult()+" All Student grouped by location were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse getFileByType()
    {
        DTOResponse response = new DTOResponse();
        try{
            response.setData(jdbcTemplate.query(stmt.GET_FILE_BY_TYPE, (rs, rowNum) ->
                    new Trend(rs.getInt("total"),
                            rs.getString("category"))));
            response.setResult("Success");
            logger.info(response.getResult()+" All File grouped by File type were returned");
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
