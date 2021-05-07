package com.AUMS.CourseManagement.model;

import lombok.*;

import java.sql.Timestamp;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class TrainingMaterial {
    private int trainingId;
    private int fileId;
    private Timestamp uploadedAt;
    private Timestamp deletedAt;
    private byte[] file;
    private float fileSize;
    private String fileName;
    private String fileType;
}
