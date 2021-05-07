package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.trendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trend")
public class TrendController {
    @Autowired
    private final trendService TrendService;

    public TrendController(trendService trendService) {
        TrendService = trendService;
    }

    @GetMapping("/typeofuser")
    public DTOResponse getTypeOfUser()
    {
        return TrendService.getTypeOfUser();
    }

    @GetMapping("/courseByLocation")
    public DTOResponse getCourseByLocation()
    {
        return TrendService.getCourseByLocation();
    }

    @GetMapping("/courseByYear")
    public DTOResponse getCourseByYear()
    {
        return TrendService.getCourseByYear();
    }

    @GetMapping("/studentByLocation")
    public DTOResponse getStudentByLocation()
    {
        return TrendService.getStudentByLocation();
    }

    @GetMapping("/fileByType")
    public DTOResponse getFileByType()
    {
        return TrendService.getFileByType();
    }
}
