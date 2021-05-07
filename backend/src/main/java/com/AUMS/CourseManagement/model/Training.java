package com.AUMS.CourseManagement.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Training {
    private int trainingId;
    private int courseId;
    private int instructorId;
    private int feedback;
}
