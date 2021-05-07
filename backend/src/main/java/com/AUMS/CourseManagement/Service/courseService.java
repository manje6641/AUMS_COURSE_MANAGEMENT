package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.CourseRepository;
import com.AUMS.CourseManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class courseService {
    @Autowired
    private final CourseRepository courseRepository;

    public courseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public DTOResponse findAll()
    {
        return courseRepository.findAll();
    }

    public DTOResponse findById(int id)
    {
        return courseRepository.findById(id);
    }

    public DTOResponse addCourse(Course newCourse)
    {
        return courseRepository.addCourse(newCourse);
    }

    public DTOResponse updateCourse(Course newCourse)
    {
        return courseRepository.updateCourse(newCourse);
    }

    public DTOResponse deleteCourse(int id)
    {
        return courseRepository.deleteCourse(id);
    }

}
