package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrainingRepository;
import com.AUMS.CourseManagement.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class trainingService {
    @Autowired
    private final TrainingRepository trainingRepo;

    public trainingService(TrainingRepository trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public DTOResponse findAll()
    {
        return trainingRepo.findAll();
    }

    public DTOResponse addTraining(Training newTraining)
    {
        return trainingRepo.addTraining(newTraining);
    }

    public DTOResponse deleteTraining(int id)
    {
        return trainingRepo.deleteTraining(id);
    }

    public DTOResponse findById(int id)
    {
        return trainingRepo.findById(id);
    }

    public DTOResponse updateTraining(Training newTraining)
    {
        return trainingRepo.updateTraining(newTraining);
    }

    public DTOResponse assignTraining(int userId, int trainingId)
    {
        return trainingRepo.assignTraining(userId, trainingId);
    }

    public DTOResponse getAssignedTraining(int id)
    {
        return trainingRepo.getAssignedTraining(id);
    }

    public DTOResponse feedback(int id, int rating)
    {
        return trainingRepo.feedback(id,rating);
    }
}
