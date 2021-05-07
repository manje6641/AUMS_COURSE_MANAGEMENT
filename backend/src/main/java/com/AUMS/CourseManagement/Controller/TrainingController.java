package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.EmailService;
import com.AUMS.CourseManagement.Service.trainingService;
import com.AUMS.CourseManagement.model.Email;
import com.AUMS.CourseManagement.model.Session;
import com.AUMS.CourseManagement.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private final trainingService TrainingService;
    private final EmailService emailService;

    public TrainingController(trainingService trainingService, EmailService emailService) {
        TrainingService = trainingService;
        this.emailService = emailService;
    }

    @GetMapping("/all")
    public DTOResponse getAllTraining()
    {
        return TrainingService.findAll();
    }

    @GetMapping("/find/{id}")
    public DTOResponse getTrainingById(@PathVariable("id") int id)
    {
        return TrainingService.findById(id);
    }

    @PostMapping("/add")
    public DTOResponse addTraining(@RequestBody Training training)
    {
        return TrainingService.addTraining(training);
    }

    @PostMapping("/sendmail")
    public DTOResponse sendEmail(@RequestBody Email email) throws MessagingException {
        return emailService.sendEmail(email);
    }

    @DeleteMapping("/delete/{id}")
    public DTOResponse deleteTraining(@PathVariable("id") int id)
    {
        return TrainingService.deleteTraining(id);
    }

    @PutMapping("/update")
    public DTOResponse updateTraining(@RequestBody Training training)
    {
        return TrainingService.updateTraining(training);
    }

    @PostMapping("/assign")
    public DTOResponse assignTraining(@RequestBody Session session )
    {
        return TrainingService.assignTraining(session.getUserId(), session.getTrainingId());
    }

    @GetMapping("/getAssignedTraining/{id}")
    public DTOResponse getAssignedTraining(@PathVariable("id") int id)
    {
        return TrainingService.getAssignedTraining(id);
    }

    @GetMapping("/feedback/{id}/{rating}")
    public DTOResponse feedback(@PathVariable("id") int id,@PathVariable("rating") int rating)
    {
        return TrainingService.feedback(id,rating);
    }
}