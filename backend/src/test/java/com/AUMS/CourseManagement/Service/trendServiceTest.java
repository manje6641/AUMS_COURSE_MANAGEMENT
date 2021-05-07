package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.TrendRepository;
import com.AUMS.CourseManagement.model.Trend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.AUMS.CourseManagement.CourseManagementApplication.class)
class trendServiceTest {

    @Mock
    private TrendRepository trendRepo;

    @InjectMocks
    private trendService TrendService;
    DTOResponse response = new DTOResponse();
    Trend trend1 = new Trend();
    Trend trend2 = new Trend();
    Trend trend3 = new Trend();
    Trend trend4 = new Trend();
    Trend trend5 = new Trend();

    @BeforeEach
    public void init()
    {
        trend1.setCategory("Mumbai");
        trend1.setCount(2);

        trend2.setCategory("Creator");
        trend2.setCount(4);

        trend3.setCategory("2021");
        trend3.setCount(6);

        trend4.setCategory("Mumbai");
        trend4.setCount(1);

        trend5.setCategory("PDF");
        trend5.setCount(3);
    }

    @Test
    void getTypeOfUser() {
        response.setData(trend2);

        when(trendRepo.getTypeOfUser()).thenReturn(response);

        DTOResponse response1 = TrendService.getTypeOfUser();
        assertThat(response1).isNotNull();
        verify(trendRepo).getTypeOfUser();
    }

    @Test
    void getCourseByLocation() {
        response.setData(trend1);

        when(trendRepo.getCourseByLocation()).thenReturn(response);

        DTOResponse response1 = TrendService.getCourseByLocation();
        assertThat(response1).isNotNull();
        verify(trendRepo).getCourseByLocation();
    }

    @Test
    void getCourseByYear() {
        response.setData(trend3);

        when(trendRepo.getCourseByYear()).thenReturn(response);

        DTOResponse response1 = TrendService.getCourseByYear();
        assertThat(response1).isNotNull();
        verify(trendRepo).getCourseByYear();
    }

    @Test
    void getStudentByLocation() {
        response.setData(trend4);

        when(trendRepo.getStudentByLocation()).thenReturn(response);

        DTOResponse response1 = TrendService.getStudentByLocation();
        assertThat(response1).isNotNull();
        verify(trendRepo).getStudentByLocation();
    }

    @Test
    void getFileByType() {
        response.setData(trend5);

        when(trendRepo.getFileByType()).thenReturn(response);

        DTOResponse response1 = TrendService.getFileByType();
        assertThat(response1).isNotNull();
        verify(trendRepo).getFileByType();
    }
}