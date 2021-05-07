package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrainingRepository;
import com.AUMS.CourseManagement.model.Session;
import com.AUMS.CourseManagement.model.Training;
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
class trainingServiceTest {

    @Mock
    private TrainingRepository trainingRepo;

    @InjectMocks
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
        training2.setTrainingId(4);
        training2.setFeedback(8);

        session.setTrainingId(2);
        session.setUserId(1);

        trainingList.add(training1);
        trainingList.add(training2);
    }


    @Test
    void findAll() {
        response.setData(trainingList);

        when(trainingRepo.findAll()).thenReturn(response);

        DTOResponse response1 = TrainingService.findAll();
        List<Training> training = (List<Training>) response1.getData();
        assertThat(training.size())
                .isEqualTo(2);
    }

    @Test
    void addTraining() {
        response.setData(training1);

        when(trainingRepo.addTraining(training1)).thenReturn(response);

        DTOResponse response1 = TrainingService.addTraining(training1);
        Training training = (Training) response1.getData();
        assertThat(response1).isNotNull();
        verify(trainingRepo).addTraining(training);
    }

    @Test
    void deleteTraining() {
        final int trainingId = 3;
        TrainingService.deleteTraining(trainingId);
        TrainingService.deleteTraining(trainingId);

        verify(trainingRepo, times(2)).deleteTraining(trainingId);
    }

    @Test
    void findById() {
        response.setData(training1);

        when(trainingRepo.findById(3)).thenReturn(response);
        int id = 3;

        DTOResponse response1 = TrainingService.findById(id);
        Training training = (Training) response1.getData();
        assertThat(training.getTrainingId())
                .isEqualTo(id);
    }

    @Test
    void updateTraining() {
        response.setData(training2);

        when(trainingRepo.updateTraining(training2)).thenReturn(response);

        DTOResponse response1 = TrainingService.updateTraining(training2);
        Training training = (Training) response1.getData();
        assertThat(response1).isNotNull();
        verify(trainingRepo).updateTraining(training);
    }

    @Test
    void assignTraining() {
        response.setData(session);

        when(trainingRepo.assignTraining(session.getUserId(),session.getTrainingId())).thenReturn(response);

        DTOResponse response1 = TrainingService.assignTraining(session.getUserId(),session.getTrainingId());
        Session session1 = (Session) response1.getData();
        assertThat(response1).isNotNull();
        verify(trainingRepo).assignTraining(session1.getUserId(),session1.getTrainingId());
    }

    @Test
    void getAssignedTraining() {
        response.setData(session);

        when(trainingRepo.getAssignedTraining(1)).thenReturn(response);

        DTOResponse response1 = TrainingService.getAssignedTraining(session.getUserId());
        Session session1 = (Session) response1.getData();
        assertThat(response1).isNotNull();
        verify(trainingRepo).getAssignedTraining(session1.getUserId());
    }
}