package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class trendService {
    @Autowired
    private final TrendRepository TrendRepo;

    public trendService(TrendRepository trendRepo) {
        TrendRepo = trendRepo;
    }
    public DTOResponse getTypeOfUser()
    {
        return TrendRepo.getTypeOfUser();
    }

    public DTOResponse getCourseByLocation()
    {
        return TrendRepo.getCourseByLocation();
    }

    public DTOResponse getCourseByYear()
    {
        return TrendRepo.getCourseByYear();
    }

    public DTOResponse getStudentByLocation()
    {
        return TrendRepo.getStudentByLocation();
    }

    public DTOResponse getFileByType()
    {
        return TrendRepo.getFileByType();
    }
}
