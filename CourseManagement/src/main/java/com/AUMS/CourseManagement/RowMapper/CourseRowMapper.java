package com.AUMS.CourseManagement.RowMapper;

import com.AUMS.CourseManagement.CONSTANT.DB_COLUMN_NAMES;
import com.AUMS.CourseManagement.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course>
{
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Course course = new Course();
        course.setCourseId(rs.getInt(DB_COLUMN_NAMES.COURSE_ID));
        course.setCourseName(rs.getString(DB_COLUMN_NAMES.COURSE_NAME));
        course.setCourseDesc(rs.getString(DB_COLUMN_NAMES.COURSE_DESCRIPTION));
        course.setCourseLocation(rs.getString(DB_COLUMN_NAMES.COURSE_LOCATION));
        course.setCourseSkill(rs.getString(DB_COLUMN_NAMES.COURSE_SKILLS));
        course.setCoursePrerequisites(rs.getString(DB_COLUMN_NAMES.COURSE_PREREQUISITES));
        course.setCourseMonth(rs.getString(DB_COLUMN_NAMES.COURSE_MONTH));
        course.setCourseYear(rs.getString(DB_COLUMN_NAMES.COURSE_YEAR));
        course.setCreatorId(rs.getInt(DB_COLUMN_NAMES.CREATOR_ID));
        course.setModifiedAt(rs.getTimestamp(DB_COLUMN_NAMES.MODIFIED_AT));
        return course;
    }
}
