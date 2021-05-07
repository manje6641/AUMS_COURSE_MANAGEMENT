package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.courseService;
import com.AUMS.CourseManagement.model.Course;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController
{
    private final courseService CourseService;

    public CourseController(courseService courseService) {
        CourseService = courseService;
    }

    @GetMapping("/all")
    public DTOResponse getAllCourse()
    {
        return CourseService.findAll();
    }

    @GetMapping("/find/{id}")
    public DTOResponse getCourseById(@PathVariable("id") int id)
    {
        return CourseService.findById(id);
    }


    @PostMapping("/add")
    public DTOResponse addCourse(@RequestBody Course course)
    {
        return CourseService.addCourse(course);
    }

    @PutMapping("/update")
    public DTOResponse updateCourse(@RequestBody Course course)
    {
        return CourseService.updateCourse(course);
    }

    @DeleteMapping("/delete/{id}")
    public DTOResponse deleteCourse(@PathVariable("id") int id)
    {
        return CourseService.deleteCourse(id);
    }


}
