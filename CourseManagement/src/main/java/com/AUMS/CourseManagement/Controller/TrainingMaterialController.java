package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.trainingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/trainingMaterial")
public class TrainingMaterialController
{
    @Autowired
    private final trainingMaterialService TMService;

    public TrainingMaterialController(trainingMaterialService tmService) {
        TMService = tmService;
    }

    @GetMapping("/find/{id}")
    public DTOResponse getAlltrainingMaterials(@PathVariable("id") int id)
    {
        return TMService.findById(id);
    }


    @PostMapping("/add/{id}")
    public DTOResponse addTrainingMaterial(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) throws IOException, SQLException
    {
        return TMService.addMaterial(file, id);
    }

    @DeleteMapping("/delete/{id}")
    public DTOResponse deleteTrainingMaterial(@PathVariable("id") int id)
    {
        return TMService.deleteMaterial(id);
    }

    @PutMapping("/update/{id}")
    public DTOResponse updateTrainingMaterial(@RequestParam("file") MultipartFile file, @PathVariable("id") int id)
    {
        return TMService.updateMaterial(file, id);
    }

}
