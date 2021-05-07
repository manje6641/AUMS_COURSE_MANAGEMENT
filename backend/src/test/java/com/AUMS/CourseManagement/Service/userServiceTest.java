package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.UserRepository;
import com.AUMS.CourseManagement.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
class userServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
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
    void findAll() {
        response.setData(userList);

        when(userRepo.findAll()).thenReturn(response);

        DTOResponse response1 = UserService.findAll();
        List<User> user = ((List<User>) response1.getData());
        assertThat(user.size())
                .isEqualTo(2);
    }

    @Test
    void findById() {
        response.setData(user1);

        when(userRepo.findById(1)).thenReturn(response);
        int id = 1;

        DTOResponse response1 = UserService.findById(id);
        User user = (User) response1.getData();
        assertThat(user.getUserId())
                .isEqualTo(id);
    }

    @Test
    void addUser() {
        response.setData(user2);

        when(userRepo.addUser(user2)).thenReturn(response);

        DTOResponse response1 = UserService.addUser(user2);
        User user = (User) response1.getData();
        assertThat(response1).isNotNull();
        verify(userRepo).addUser(user);
    }

    @Test
    void updateUser() {
        response.setData(user1);

        when(userRepo.updateUser(user1)).thenReturn(response);

        DTOResponse response1 = UserService.updateUser(user1);
        User user = (User) response1.getData();
        assertThat(response1).isNotNull();
        verify(userRepo).updateUser(user);
    }

    @Test
    void findByEmail() {
        response.setData(user2);

        when(userRepo.findByEmail("ruchita.patil@accolitedigital.com")).thenReturn(response);

        DTOResponse response1 = UserService.findByEmail("ruchita.patil@accolitedigital.com");
        User user = (User) response1.getData();
        assertThat(response1).isNotNull();
        verify(userRepo).findByEmail(user.getUserEmail());
    }

    @Test
    void findAllInstructor() {
        response.setData(user2);

        when(userRepo.findAllInstructor()).thenReturn(response);

        DTOResponse response1 = UserService.findAllInstructor();
        User user = (User) response1.getData();
        assertThat(response1).isNotNull();
        verify(userRepo).findAllInstructor();
    }

    @Test
    void deleteUser() {
        final int userId = 1;
        UserService.deleteUser(userId);
        UserService.deleteUser(userId);

        verify(userRepo, times(2)).deleteUser(userId);

    }
}