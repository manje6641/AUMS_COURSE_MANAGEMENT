package com.AUMS.CourseManagement.RowMapper;

import com.AUMS.CourseManagement.CONSTANT.DB_COLUMN_NAMES;
import com.AUMS.CourseManagement.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>
{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        User user = new User();
        user.setUserId(rs.getInt(DB_COLUMN_NAMES.USER_ID));
        user.setUserEmail(rs.getString(DB_COLUMN_NAMES.USER_EMAIL));
        user.setUserName(rs.getString(DB_COLUMN_NAMES.USER_NAME));
        user.setUserDesignation(rs.getString(DB_COLUMN_NAMES.USER_DESIGNATION));
        user.setUserLocation(rs.getString(DB_COLUMN_NAMES.USER_LOCATION));
      //  user.setUserImage(rs.getBlob(DB_COLUMN_NAMES.USER_IMAGE));
        return user;
    }
}
