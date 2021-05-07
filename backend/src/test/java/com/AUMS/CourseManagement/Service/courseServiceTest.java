package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.CourseRepository;
import com.AUMS.CourseManagement.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
class courseServiceTest {

    @Mock
    private CourseRepository courseRepo;

    @InjectMocks
    private courseService CourseService;

    DTOResponse response = new DTOResponse();
    Course course1 = new Course();
    Course course2 = new Course();
    List<Course> courseList = new ArrayList<>();

    @BeforeEach
    public void init() {
        course1.setCourseId(1);
        course1.setCourseName("c++");
        course1.setCourseDesc("Basic of c++");
        course1.setCreatorId(1);

        course2.setCourseId(2);
        course2.setCourseName("java");
        course2.setCourseDesc("Basic of java");
        course2.setCreatorId(2);
        courseList.add(course1);
        courseList.add(course2);
    }

    @Test
    void findAll() {
        response.setData(courseList);

        when(courseRepo.findAll()).thenReturn(response);

        DTOResponse response1 = CourseService.findAll();
        List<Course> course = (List<Course>) response1.getData();
        assertThat(course.size())
                .isEqualTo(2);
    }

    @Test
    void findById() {
        response.setData(course1);

        when(courseRepo.findById(1)).thenReturn(response);
        int id = 1;

        DTOResponse response1 = CourseService.findById(id);
        Course course = (Course) response1.getData();
        assertThat(course.getCourseId())
                .isEqualTo(id);
    }

    @Test
    void addCourse() {
        response.setData(course2);

        when(courseRepo.addCourse(course2)).thenReturn(response);

        DTOResponse response1 = CourseService.addCourse(course2);
        Course course = (Course) response1.getData();
        assertThat(response1).isNotNull();
        verify(courseRepo).addCourse(course);
    }

    @Test
    void updateCourse() {
        response.setData(course2);

        when(courseRepo.updateCourse(course2)).thenReturn(response);

        DTOResponse response1 = CourseService.updateCourse(course2);
        Course course = (Course) response1.getData();
        assertThat(response1).isNotNull();
        verify(courseRepo).updateCourse(course);
    }

    @Test
    void deleteCourse() {
        final int courseId = 1;
        CourseService.deleteCourse(courseId);
        CourseService.deleteCourse(courseId);

        verify(courseRepo, times(2)).deleteCourse(courseId);
    }
}