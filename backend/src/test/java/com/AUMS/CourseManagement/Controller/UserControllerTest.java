package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.ObjectToJson;
import com.AUMS.CourseManagement.Service.userService;
import com.AUMS.CourseManagement.model.User;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userService UserService;

    DTOResponse response = new DTOResponse();
    User user1 = new User();
    User user2 = new User();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    public void init() {

        user1.setUserId(1);
        user1.setUserDesignation("Student");
        user1.setUserEmail("manjeet.singh@accolitedigital.com");
        user1.setUserLocation("Mumbai");

        user2.setUserId(2);
        user2.setUserDesignation("Instructor");
        user2.setUserEmail("ruchita.patil@accolitedigital.com");
        user2.setUserLocation("Mumbai");



        userList.add(user1);
        userList.add(user2);
    }
    @Test
    void getAllUser() throws Exception{
        response.setData(userList);

        when(UserService.findAll()).thenReturn(response);

        mockMvc.perform(get("/user/all/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()",is(2)));
    }

    @Test
    void getAllInstructor() throws Exception {
        response.setData(user2);

        when(UserService.findAllInstructor()).thenReturn(response);

        mockMvc.perform(get("/user/allInstructor/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId",is(2)));
    }

    @Test
    void getUserById() throws Exception{
        response.setData(user1);

        when(UserService.findById(1)).thenReturn(response);

        mockMvc.perform(get("/user/find/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userEmail", is(user1.getUserEmail())));
    }

    @Test
    void getUserByEmail() throws Exception{
        response.setData(user2);

        when(UserService.findByEmail("ruchita.patil@accolitedigital.com")).thenReturn(response);

        mockMvc.perform(get("/user/findByEmail/ruchita.patil@accolitedigital.com")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId", is(user2.getUserId())));

    }

    @Test
    void addUser() throws Exception{
        response.setData(user1);

        when(UserService.addUser(user1)).thenReturn(response);

        mockMvc.perform(post("/user/add")
                .content(ObjectToJson.asJsonString(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateUser() throws Exception{
        response.setData(user2);

        when(UserService.updateUser(user2)).thenReturn(response);

        mockMvc.perform(put("/user/update")
                .content(ObjectToJson.asJsonString(user2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteUser() throws Exception{
        response.setData(user1);

        when(UserService.deleteUser(1)).thenReturn(response);

        mockMvc.perform(delete("/user/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
}