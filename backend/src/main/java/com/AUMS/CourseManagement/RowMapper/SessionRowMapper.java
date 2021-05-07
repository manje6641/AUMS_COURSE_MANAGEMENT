package com.AUMS.CourseManagement.RowMapper;

import com.AUMS.CourseManagement.CONSTANT.DB_COLUMN_NAMES;
import com.AUMS.CourseManagement.model.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionRowMapper implements RowMapper<Session> {

    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        Session session = new Session();
        session.setUserId(rs.getInt(DB_COLUMN_NAMES.USER_ID));
        session.setTrainingId(rs.getInt(DB_COLUMN_NAMES.TRAINING_ID));
        return session;
    }
}
