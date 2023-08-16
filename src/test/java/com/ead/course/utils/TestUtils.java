package com.ead.course.utils;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.ead.course.models.CourseModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TestUtils {

    public static final LocalDateTime DATE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    public static final UUID MyUUID = UUID.fromString("93c391f8-e005-4eda-bd54-7b7c094046a3");
    public static final String STRMyUUID = "93c391f8-e005-4eda-bd54-7b7c094046a3";
    public static CourseModel getCourseModel(UUID id) {
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
}
