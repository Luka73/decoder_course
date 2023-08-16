package com.ead.course.controllers;

import com.ead.course.AbstractTest;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

import static com.ead.course.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class CourseControllerTest extends AbstractTest {

    @Mock
    CourseService courseService;
    @InjectMocks
    CourseController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testSaveCourse() throws Exception {
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
                .ignoringFields("id", "userInstructor", "creationDate", "lastUpdateDate")
                .isEqualTo(expected);
    }

    @Test
    public void testDeleteCourseHappyPath() throws Exception {
        String uri = "/courses/" + STRMyUUID;
        CourseModel savedCourse = getCourseModel(MyUUID);
        when(courseService.findById(savedCourse.getId())).thenReturn(Optional.of(savedCourse));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is deleted successsfully");
    }

    @Test
    public void testDeleteCourseCourseNotFound() throws Exception {
        String uri = "/courses/" + STRMyUUID;
        when(courseService.findById(MyUUID)).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Course Not Found.");
    }


}
