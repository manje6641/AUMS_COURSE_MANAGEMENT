package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrainingMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class trainingMaterialService
{
    @Autowired
    private final TrainingMaterialRepository TMRepo;

    public trainingMaterialService(TrainingMaterialRepository tmRepo) {
        TMRepo = tmRepo;
    }

    public DTOResponse findAll()
    {
        return TMRepo.findAll();
    }

    public DTOResponse findById(int id)
    {
        return TMRepo.findById(id);
    }

    public DTOResponse addMaterial(MultipartFile file, int trainingId) throws IOException, SQLException
    {
        return TMRepo.addMaterial(file, trainingId);
    }

    public DTOResponse deleteMaterial(int id)
    {
        return TMRepo.deleteMaterial(id);
    }

    public DTOResponse updateMaterial(MultipartFile file, int fileId)
    {
        return TMRepo.updateMaterial(file, fileId);
    }
}
