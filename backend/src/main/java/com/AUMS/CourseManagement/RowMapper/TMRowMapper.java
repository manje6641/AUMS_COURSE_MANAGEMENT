package com.AUMS.CourseManagement.RowMapper;

import com.AUMS.CourseManagement.CONSTANT.DB_COLUMN_NAMES;
import com.AUMS.CourseManagement.model.TrainingMaterial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TMRowMapper implements RowMapper<TrainingMaterial>
{
    @Override
    public TrainingMaterial mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setFileId(rs.getInt(DB_COLUMN_NAMES.FILE_ID));
        trainingMaterial.setTrainingId(rs.getInt(DB_COLUMN_NAMES.TRAINING_ID));
        trainingMaterial.setFileName(rs.getString(DB_COLUMN_NAMES.FILE_NAME));
        trainingMaterial.setFileType(rs.getString(DB_COLUMN_NAMES.FILE_TYPE));
        trainingMaterial.setFileSize((float) rs.getInt(DB_COLUMN_NAMES.FILE_SIZE)/1048576);
        trainingMaterial.setFile(rs.getBlob(DB_COLUMN_NAMES.FILE).getBytes(1, (int)rs.getBlob(DB_COLUMN_NAMES.FILE).length()));
        trainingMaterial.setUploadedAt(rs.getTimestamp(DB_COLUMN_NAMES.UPLOADED_AT));
        trainingMaterial.setDeletedAt(rs.getTimestamp(DB_COLUMN_NAMES.DELETED_AT));
        return trainingMaterial;
    }
}
