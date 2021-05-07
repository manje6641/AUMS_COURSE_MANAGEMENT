package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.ObjectToJson;
import com.AUMS.CourseManagement.Service.trainingService;
import com.AUMS.CourseManagement.model.Session;
import com.AUMS.CourseManagement.model.Training;
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
class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private trainingService TrainingService;
    DTOResponse response = new DTOResponse();
    Training training1 = new Training();
    Training training2 = new Training();
    Session session = new Session();
    List<Training> trainingList = new ArrayList<>();

    @BeforeEach
    public void init() {

        training1.setCourseId(1);
        training1.setInstructorId(2);
        training1.setTrainingId(3);
        training1.setFeedback(9);

        training2.setCourseId(1);
        training2.setInstructorId(2);
        training2.setTrainingId(3);
        training2.setFeedback(8);

        session.setTrainingId(2);
        session.setUserId(1);

        trainingList.add(training1);
        trainingList.add(training2);
    }

    @Test
    void getAllTraining() throws Exception{

        response.setData(trainingList);

        when(TrainingService.findAll()).thenReturn(response);

        mockMvc.perform(get("/training/all/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()",is(2)));
    }

    @Test
    void getTrainingById() throws Exception{

        response.setData(training1);

        when(TrainingService.findById(3)).thenReturn(response);

        mockMvc.perform(get("/training/find/3")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.courseId", is(training1.getCourseId())));
    }

    @Test
    void addTraining() throws Exception{

        response.setData(training2);

        when(TrainingService.addTraining(training2)).thenReturn(response);

        mockMvc.perform(post("/training/add")
                .content(ObjectToJson.asJsonString(training2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    void deleteTraining() throws Exception{
        response.setData(training2);

        when(TrainingService.deleteTraining(3)).thenReturn(response);
        mockMvc.perform(delete("/training/delete/3")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateTraining() throws Exception{
        response.setData(training1);

        when(TrainingService.updateTraining(training1)).thenReturn(response);

        mockMvc.perform(put("/training/update")
                .content(ObjectToJson.asJsonString(training1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void assignTraining() throws Exception{
        response.setData(session);

        when(TrainingService.assignTraining(session.getUserId(), session.getTrainingId())).thenReturn(response);

        mockMvc.perform(post("/training/assign")
                .content(ObjectToJson.asJsonString(session))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAssignedTraining() throws Exception{

        response.setData(session);

        when(TrainingService.getAssignedTraining(1)).thenReturn(response);

        mockMvc.perform(get("/training/getAssignedTraining/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId", is(session.getUserId())));
    }
}