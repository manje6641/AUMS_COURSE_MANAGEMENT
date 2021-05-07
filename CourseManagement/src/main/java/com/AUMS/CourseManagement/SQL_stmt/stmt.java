package com.AUMS.CourseManagement.SQL_stmt;

public class stmt {
    public stmt() {
    }

    public static final String GET_ALL_COURSES = "SELECT * FROM COURSE WHERE DELETED_AT IS NULL";
    public static final String GET_COURSE_BY_ID = "SELECT * FROM COURSE WHERE COURSE_ID = ? AND DELETED_AT IS NULL";
    public static final String ADD_COURSE = "INSERT INTO course" +
            "( course_name, course_location, course_desc,creator_id, course_skills, course_prerequisites,  course_month, course_year) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_COURSE = "UPDATE course SET course_name=?, course_desc=?, course_skills=?, " +
            "course_prerequisites=?, course_location=?, course_month=?,course_year=?, modified_at=now() WHERE course_id =?";
    public static final String DELETE_COURSE = "UPDATE course SET deleted_at=now() WHERE course_id=?";
    public static final String GET_ALL_MATERIAL = "SELECT * FROM TRAINING_MATERIAL WHERE DELETED_AT IS NULL";
    public static final String GET_MATERIAL_BY_ID = "SELECT * FROM TRAINING_MATERIAL WHERE TRAINING_ID=? AND DELETED_AT IS NULL ORDER BY TRAINING_FILE_ID DESC";
    public static final String GET_ID_FROM_MATERIAL = "SELECT DISTINCT training_id FROM training JOIN course " +
            "ON training.course_id = course.course_id " +
            "where training.course_id =? " +
            "and training.instructor_id =? and " +
            "training.deleted_at IS NULL";
    public static final String CREATE_MATERIAL = "INSERT INTO training_material(training_id, training_file, " +
            "training_file_name, training_file_type, training_file_size, uploaded_at) VALUES(?,?,?,?,?,now())";
    public static final String UPDATE_MATERIAL  = "UPDATE TRAINING_MATERIAL SET TRAINING_FILE=?,training_file_name=?,training_file_type=?,training_file_size=?,uploaded_at=now() WHERE TRAINING_FILE_ID=? AND DELETED_AT IS NULL";
    public static final String DELETE_MATERIAL = "UPDATE TRAINING_MATERIAL SET DELETED_AT = now() WHERE TRAINING_FILE_ID=?";
    public static final String GET_ALL_USERS = "SELECT * from user WHERE DELETED_AT IS NULL";
    public static final String GET_ALL_INSTRUCTOR = "SELECT * from user WHERE USER_DESIGNATION='Instructor' AND DELETED_AT IS NULL";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE USER_ID = ? AND DELETED_AT IS NULL";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE USER_EMAIL = ? AND DELETED_AT IS NULL";
    public static final String ADD_USER = "INSERT INTO user(user_name, user_email, user_designation, user_location) VALUES ( ?, ?, ?, ? )";
    public static final String UPDATE_USER = "UPDATE USER SET USER_NAME=?, USER_EMAIL=?, USER_LOCATION=?,USER_DESIGNATION=? WHERE USER_ID=?";
    public static final String DELETE_USER = "UPDATE USER SET DELETED_AT=now() WHERE USER_ID=?";
    public static final String GET_TRAINING_DETAILS = "Select * FROM TRAINING WHERE DELETED_AT IS NULL";
    public static final String ADD_TRAINING = "INSERT INTO training(course_id, instructor_id) VALUES ( ?, ? )";
    public static final String GET_COURSE_ID = "SELECT course_id FROM COURSE WHERE DELETED_AT IS NULL";
    public static final String GET_INSTRUCTOR_ID = "SELECT USER_ID FROM USER WHERE USER_DESIGNATION='Instructor'";
    public static final String DELETE_TRAINING = "DELETE FROM TRAINING WHERE TRAINING_ID=?";
    public static final String GET_TRAINING_BY_ID = "SELECT * FROM TRAINING WHERE TRAINING_ID=? AND DELETED_AT IS NULL";
    public static final String UPDATE_TRAINING = "UPDATE TRAINING SET COURSE_ID=?, INSTRUCTOR_ID=? WHERE TRAINING_ID=?";
    public static final String GET_USER_TYPE = "select count(*) total,user_designation category from user where deleted_at is null group by user_designation";
    public static final String GET_COURSE_BY_LOCATION = "select count(*) total,course_location category from course where deleted_at is null group by course_location order by course_location";
    public static final String GET_COURSE_BY_YEAR = "select count(*) total,course_year category from course where deleted_at is null group by course_year order by course_year";
    public static final String GET_STUDENT_BY_LOCATION = "select count(*) total,user_location category from user where user_designation='Student' and deleted_at is null group by user_location order by user_location";
    public static final String GET_FILE_BY_TYPE = "select count(*) total, training_file_type category from training_material where deleted_at is null group by training_file_type";
    public static final String ASSIGN_TRAINING = "INSERT INTO SESSION( user_id, training_id) VALUE(?, ?)";
    public static final String GET_ASSIGNED_TRAINING = "SELECT * FROM SESSION WHERE USER_ID=?";
    public static final String TRAINING_FEEDBACK = "UPDATE TRAINING SET TRAINING_FEEDBACK=? WHERE TRAINING_ID=?";
}
