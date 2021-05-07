package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.trainingMaterialService;
import com.AUMS.CourseManagement.model.TrainingMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

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
class TrainingMaterialControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private trainingMaterialService TMService;

    DTOResponse response =new DTOResponse();
    TrainingMaterial Material1 = new TrainingMaterial();
    TrainingMaterial Material2 = new TrainingMaterial();
    List<TrainingMaterial> MaterialList = new ArrayList<>();

    @BeforeEach
    public void init() {
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
    void getAlltrainingMaterials() throws Exception{
        response.setData(Material1);

        when(TMService.findById(1)).thenReturn(response);

        mockMvc.perform(get("/trainingMaterial/find/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.fileName",is(Material1.getFileName())));
    }

    @Test
    void addTrainingMaterial() throws Exception{
        response.setData(Material1);
        MultipartFile file = null;

        when(TMService.addMaterial(file, Material1.getTrainingId())).thenReturn(response);

        mockMvc.perform(post("/trainingMaterial/add/1")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(String.valueOf(file))
                .accept(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteTrainingMaterial() throws Exception {
        response.setData(Material2);

        when(TMService.deleteMaterial(11)).thenReturn(response);
        mockMvc.perform(delete("/trainingMaterial/delete/2")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateTrainingMaterial() throws Exception{
        response.setData(Material2);
        MultipartFile file = null;

        when(TMService.updateMaterial(file,Material2.getTrainingId())).thenReturn(response);

        mockMvc.perform(put("/trainingMaterial/update/2")
                .content((byte[]) null)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}