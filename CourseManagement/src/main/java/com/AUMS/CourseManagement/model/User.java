package com.AUMS.CourseManagement.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userDesignation;
    private String userLocation;
}
