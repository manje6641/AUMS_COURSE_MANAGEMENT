package com.AUMS.CourseManagement.Repository;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.RowMapper.TMRowMapper;
import com.AUMS.CourseManagement.SQL_stmt.stmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

@Repository
public class TrainingMaterialRepository {
    public static final Logger logger = LoggerFactory.getLogger(TrainingMaterialRepository.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public TrainingMaterialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DTOResponse findAll()
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_ALL_MATERIAL, new TMRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Training Material were returned");
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
        DTOResponse response =new DTOResponse();
        try
        {
            response.setData(jdbcTemplate.query(stmt.GET_MATERIAL_BY_ID,new Object[]{id},new TMRowMapper()));
            response.setResult("Success");
            logger.info(response.getResult()+" All Training Material were returned");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse addMaterial(MultipartFile file, int trainingId)
    {
        DTOResponse response = new DTOResponse();
        try
        {
                byte[] data = file.getBytes();
                Blob data1 = new SerialBlob(data);
                response.setAdditionalData(jdbcTemplate.update(stmt.CREATE_MATERIAL, trainingId, data1,
                        file.getOriginalFilename(),file.getContentType(), file.getSize()));
            response.setResult("Success");
            logger.info(response.getResult()+" Adding Material was successful");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }

    public DTOResponse deleteMaterial(int id)
    {
        DTOResponse response=new DTOResponse();
        try
        {
            response.setAdditionalData(jdbcTemplate.update(stmt.DELETE_MATERIAL,id));
            response.setResult("Success");
            logger.info(response.getResult()+" Deleting material was successful");
        }
        catch(Exception e)
        {
            response.setResult("Failure");
            response.setError_message(e.toString());
            logger.error(e.toString());
        }
        return response;
    }


    public DTOResponse updateMaterial(MultipartFile file, int fileId)
    {
        DTOResponse response = new DTOResponse();
        try
        {
            byte[] data = file.getBytes();
            Blob data1 = new SerialBlob(data);
            response.setAdditionalData(jdbcTemplate.update(stmt.UPDATE_MATERIAL, data1,
                    file.getOriginalFilename(),file.getContentType(), file.getSize(), fileId));
            response.setResult("Success");
            logger.info(response.getResult()+" Updating Material was successful");
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
