package com.AUMS.CourseManagement.Repository;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.RowMapper.CourseRowMapper;
import com.AUMS.CourseManagement.SQL_stmt.stmt;
import com.AUMS.CourseManagement.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {
    public static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbctemplate) {
        this.jdbcTemplate = jdbctemplate;
    }

    public DTOResponse findAll()
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_ALL_COURSES, new CourseRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Courses were returned");
        }
        catch(Exception e)
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
            response.setData(jdbcTemplate.queryForObject(stmt.GET_COURSE_BY_ID, new Object[]{id}, new CourseRowMapper()));
            logger.info(response.getResult()+" Course with " + id +" was returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse addCourse(Course newCourse)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.ADD_COURSE,newCourse.getCourseName(),newCourse.getCourseLocation(),newCourse.getCourseDesc(),
                    newCourse.getCreatorId(),newCourse.getCourseSkill(),newCourse.getCoursePrerequisites(),newCourse.getCourseMonth(),
                    newCourse.getCourseYear()));
            response.setResult("Success");
            logger.info(response.getResult()+" Adding course was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse updateCourse(Course newCourse)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.UPDATE_COURSE,newCourse.getCourseName(),newCourse.getCourseDesc()
                    ,newCourse.getCourseSkill(),newCourse.getCoursePrerequisites(),newCourse.getCourseLocation() , newCourse.getCourseMonth(),
                    newCourse.getCourseYear(),newCourse.getCourseId()));
            response.setResult("Success");
            logger.info(response.getResult()+" Updating course was successful");
        }
        catch (Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse deleteCourse(int id)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.DELETE_COURSE,id));
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

}
