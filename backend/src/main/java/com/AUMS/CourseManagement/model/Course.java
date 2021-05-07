package com.AUMS.CourseManagement.model;


import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Course implements Serializable {
    private int courseId;
    private int creatorId;
    private String courseName;
    private String courseDesc;
    private String courseLocation ;
    private String courseSkill;
    private String coursePrerequisites;
    private String courseMonth;
    private String courseYear;
    private Timestamp modifiedAt;

}
