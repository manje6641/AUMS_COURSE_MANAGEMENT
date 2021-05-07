package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrainingMaterialRepository;
import com.AUMS.CourseManagement.model.TrainingMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
class trainingMaterialServiceTest {

    @Mock
    private TrainingMaterialRepository TMRepo;

    @InjectMocks
    private trainingMaterialService TMService;

    DTOResponse response =new DTOResponse();
    TrainingMaterial Material1 = new TrainingMaterial();
    TrainingMaterial Material2 = new TrainingMaterial();
    List<TrainingMaterial> MaterialList = new ArrayList<>();
    @BeforeEach
    public void init()
    {
        Material1.setTrainingId(1);
        Material1.setFileId(2);
        Material1.setFileName("file");
        Material1.setFileType("pdf");

        Material1.setTrainingId(2);
        Material2.setFileId(4);
        Material2.setFileName("file");
        Material2.setFileType("pdf");

        MaterialList.add(Material1);
        MaterialList.add(Material2);
    }
    @Test
    void findAll() {
        response.setData(MaterialList);

        when(TMRepo.findAll()).thenReturn(response);

        DTOResponse response1 = TMService.findAll();
        List<TrainingMaterial> trainingmaterial = (List<TrainingMaterial>) response1.getData();
        assertThat(trainingmaterial.size())
                .isEqualTo(2);
    }

    @Test
    void findById() {
        response.setData(Material1);

        when(TMRepo.findById(1)).thenReturn(response);

        DTOResponse response1 = TMService.findById(1);
        TrainingMaterial trainingMaterial = (TrainingMaterial) response1.getData();
        assertThat(trainingMaterial.getFileId())
                .isEqualTo(2);
    }

    @Test
    void addMaterial() throws SQLException, IOException {
        response.setData(MaterialList);

        MultipartFile file = null;

        response.setAdditionalData(1);
        when(TMRepo.addMaterial(file, 1 )).thenReturn(response);

        DTOResponse response1 = TMService.addMaterial(file, 1);
        assertThat(response1.getAdditionalData())
                .isEqualTo(1);
    }

    @Test
    void deleteMaterial() {
        final int trainingId = 1;
        TMService.deleteMaterial(trainingId);
        TMService.deleteMaterial(trainingId);

        verify(TMRepo, times(2)).deleteMaterial(trainingId);
    }

    @Test
    void updateMaterial() {
        response.setData(MaterialList);

        MultipartFile file = null;

        response.setAdditionalData(1);
        when(TMRepo.updateMaterial(file, 1 )).thenReturn(response);

        DTOResponse response1 = TMService.updateMaterial(file, 1);
        assertThat(response1.getAdditionalData())
                .isEqualTo(1);
    }
}