package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.ObjectToJson;
import com.AUMS.CourseManagement.Service.courseService;
import com.AUMS.CourseManagement.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

@MockBean
    private courseService CourseService;

    DTOResponse response =new DTOResponse();
    Course course0 = new Course();
    Course course1 = new Course();
    List<Course> courseList = new ArrayList<>();

    @BeforeEach
    public void init() {
        course0.setCourseId(1);
        course0.setCourseName("C++");
        course0.setCourseDesc("Basic of C++");

        course1.setCourseId(2);
        course1.setCourseName("Java");
        course1.setCourseDesc("Basic of Java");

        courseList.add(course0);
        courseList.add(course1);
    }

    @Test
    void getAllCourse() throws Exception {

        response.setData(courseList);

        when(CourseService.findAll()).thenReturn(response);

        mockMvc.perform(get("/course/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()",is(2)));
    }

    @Test
    void getCourseById() throws Exception {

        response.setData(course0);

        when(CourseService.findById(1)).thenReturn(response);

        mockMvc.perform(get("/course/find/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.courseName", is(course0.getCourseName())));
    }

    @Test
    void addCourse() throws Exception{
        response.setData(course1);

         when(CourseService.addCourse(course1)).thenReturn(response);

         mockMvc.perform(post("/course/add")
         .content(ObjectToJson.asJsonString(course1))
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andReturn();
    }

    @Test
    void updateCourse() throws Exception {
        response.setData(course0);

        when(CourseService.updateCourse(course0)).thenReturn(response);

        mockMvc.perform(put("/course/update")
                .content(ObjectToJson.asJsonString(course0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteCourse() throws Exception{

        response.setData(course0);

        when(CourseService.deleteCourse(1)).thenReturn(response);

        mockMvc.perform(delete("/course/delete/1")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
}