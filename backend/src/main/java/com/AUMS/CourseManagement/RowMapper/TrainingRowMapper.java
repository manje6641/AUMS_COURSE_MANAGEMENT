package com.AUMS.CourseManagement.RowMapper;

import com.AUMS.CourseManagement.CONSTANT.DB_COLUMN_NAMES;
import com.AUMS.CourseManagement.model.Training;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingRowMapper implements RowMapper<Training> {

    @Override
    public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
        Training t = new Training();
        t.setCourseId(rs.getInt(DB_COLUMN_NAMES.COURSE_ID));
        t.setTrainingId(rs.getInt(DB_COLUMN_NAMES.TRAINING_ID));
        t.setFeedback(rs.getInt(DB_COLUMN_NAMES.TRAINING_FEEDBACK));
        t.setInstructorId(rs.getInt(DB_COLUMN_NAMES.INSTRUCTOR_ID));
        return t;
    }
}
