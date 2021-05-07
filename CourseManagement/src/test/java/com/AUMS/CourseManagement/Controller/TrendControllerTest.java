package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.trendService;
import com.AUMS.CourseManagement.model.Trend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
@AutoConfigureMockMvc
class TrendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private trendService TrendService;

    DTOResponse response =new DTOResponse();
    Trend trend1 = new Trend();
    Trend trend2 = new Trend();
    Trend trend3 = new Trend();
    Trend trend4 = new Trend();
    Trend trend5 = new Trend();

    @BeforeEach
    public void init() {
        trend1.setCategory("Mumbai");
        trend1.setCount(2);

        trend2.setCategory("Creator");
        trend2.setCount(4);

        trend3.setCategory("2021");
        trend3.setCount(6);

        trend4.setCategory("Mumbai");
        trend4.setCount(1);

        trend5.setCategory("PDF");
        trend5.setCount(3);

    }

    @Test
    void getTypeOfUser() throws Exception{

        response.setData(trend2);

        when(TrendService.getTypeOfUser()).thenReturn(response);

        mockMvc.perform(get("/trend/typeofuser")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count",is(trend2.getCount())));
    }

    @Test
    void getCourseByLocation() throws Exception{
        response.setData(trend1);

        when(TrendService.getCourseByLocation()).thenReturn(response);

        mockMvc.perform(get("/trend/courseByLocation")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count",is(trend1.getCount())));
    }

    @Test
    void getCourseByYear() throws Exception{
        response.setData(trend3);

        when(TrendService.getCourseByYear()).thenReturn(response);

        mockMvc.perform(get("/trend/courseByYear")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count",is(trend3.getCount())));
    }

    @Test
    void getStudentByLocation() throws Exception{
        response.setData(trend4);

        when(TrendService.getStudentByLocation()).thenReturn(response);

        mockMvc.perform(get("/trend/studentByLocation")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count",is(trend4.getCount())));
    }

    @Test
    void getFileByType() throws Exception{
        response.setData(trend5);

        when(TrendService.getFileByType()).thenReturn(response);

        mockMvc.perform(get("/trend/fileByType")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count",is(trend5.getCount())));
    }
}