package com.AUMS.CourseManagement.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Email {
    private String subject;
    private String message;
    private String recipient;
    private int trainingId;
    private int courseId;
    private int instructorId;
    private int feedback;
}
