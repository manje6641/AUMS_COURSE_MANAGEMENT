package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.CourseRepository;
import com.AUMS.CourseManagement.Repository.TrainingRepository;
import com.AUMS.CourseManagement.Repository.UserRepository;
import com.AUMS.CourseManagement.model.Course;
import com.AUMS.CourseManagement.model.Email;
import com.AUMS.CourseManagement.model.Training;
import com.AUMS.CourseManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;
    private final CourseRepository courseRepo;
    private final TrainingRepository trainingRepo;
    private final UserRepository userRepo;

    public EmailService(JavaMailSender javaMailSender, CourseRepository courseRepo, TrainingRepository trainingRepo, UserRepository userRepo) {
        this.javaMailSender = javaMailSender;
        this.courseRepo = courseRepo;
        this.trainingRepo = trainingRepo;
        this.userRepo = userRepo;
    }

    public DTOResponse sendEmail(Email email) throws MessagingException {

        DTOResponse response = new DTOResponse();
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            DTOResponse trainingResponse = trainingRepo.findById(email.getTrainingId());
            int courseId = ((Training) trainingResponse.getData()).getCourseId();
            DTOResponse courseResponse = courseRepo.findById(courseId);
            int instructorId = ((Training) trainingResponse.getData()).getInstructorId();
            DTOResponse userResponse = userRepo.findById(instructorId);
            String courseName = ((Course) courseResponse.getData()).getCourseName();
            String instructorName = ((User) userResponse.getData()).getUserName();
            String instructorEmail = ((User) userResponse.getData()).getUserEmail();
            helper.setTo(email.getRecipient());
            helper.setSubject(email.getSubject());
            helper.setText("Please check the portal for training material.\n " +
                    "Course ID -> " + courseId + "\n" + "Course Name -> " + courseName + "\n" + "Instructor ID -> " + instructorId + "\n" +
                    "Instructor Name -> " + instructorName + "\n" + "Instructor Email -> " + instructorEmail);
            javaMailSender.send(msg);
            response.setAdditionalData(1);
            response.setResult("Success");
        } catch (Exception e) {
            response.setResult("Failure");
            response.setError_message(e.toString());
        }
        return response;

    }
}
