package com.ead.course.controllers;

import com.ead.course.AbstractTest;
import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CourseControllerTest extends AbstractTest {

    private static final LocalDateTime DATE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    @Mock
    CourseService courseService;
    @InjectMocks
    CourseController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static CourseModel getCourseModel(UUID id) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(id);
        courseModel.setCourseLevel(CourseLevel.BEGINNER);
        courseModel.setCourseStatus(CourseStatus.INPROGRESS);
        courseModel.setName("Spring Boot");
        courseModel.setDescription("Course de Spring Boot");
        courseModel.setImageUrl("https://image.url");
        courseModel.setUserInstructor(UUID.randomUUID());
        courseModel.setCreationDate(DATE);
        courseModel.setLastUpdateDate(DATE);
        return courseModel;
    }

    @Test
    public void test() throws Exception {
        String uri = "/courses";
        CourseModel courseModel = getCourseModel(null);
        CourseModel expected = getCourseModel(UUID.randomUUID());

        when(courseService.save(courseModel)).thenReturn(expected);

        String inputJson = super.mapToJson(courseModel);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        CourseModel actual = super.mapFromJson(content, CourseModel.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "userInstructor")
                .isEqualTo(expected);
    }

}
