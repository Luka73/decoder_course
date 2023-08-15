package com.ead.course.controllers;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    private static final UUID MyUUID = UUID.randomUUID();
    @Mock
    CourseService courseService;
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    CourseController controller;

    private static CourseModel getCourseModel(UUID id) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(id);
        courseModel.setCourseLevel(CourseLevel.BEGINNER);
        courseModel.setCourseStatus(CourseStatus.INPROGRESS);
        courseModel.setName("Spring Boot");
        courseModel.setDescription("Course de Spring Boot");
        courseModel.setImageUrl("https://image.url");
        courseModel.setCreationDate(LocalDateTime.of(2022, Month.AUGUST, 7, 10, 10, 10));
        courseModel.setLastUpdateDate(LocalDateTime.of(2022, Month.SEPTEMBER, 7, 10, 10, 10));
        return courseModel;
    }

    @Test
    public void test() throws Exception {
        CourseModel courseModel = getCourseModel(null);
        CourseModel exceptedCourseModel = getCourseModel(MyUUID);

        when(courseService.save(courseModel)).thenReturn(exceptedCourseModel);
        this.mockMvc.perform(post("/courses")).andDo(print()).andExpect(status().isOk());

    }

}
