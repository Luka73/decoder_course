package com.ead.course.utils;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
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
        courseModel.setDescription("Curso de Spring Boot");
        courseModel.setImageUrl("https://image.course.url");
        courseModel.setUserInstructor(UUID.randomUUID());
        courseModel.setCreationDate(DATE);
        courseModel.setLastUpdateDate(DATE);
        return courseModel;
    }

    public static CourseModel getFullCourseModel(UUID id) {
        CourseModel courseModel = getCourseModel(id);
        ModuleModel moduleModel = getModuleModel(UUID.randomUUID());
        LessonModel lessonModel1 = getLessonModel(UUID.randomUUID());
        LessonModel lessonModel2 = getLessonModel(UUID.randomUUID());
        LessonModel lessonModel3 = getLessonModel(UUID.randomUUID());

        moduleModel.setLessons(Set.of(lessonModel1, lessonModel2, lessonModel3));
        courseModel.setModules(Set.of(moduleModel));
        lessonModel1.setModule(moduleModel);
        lessonModel2.setModule(moduleModel);
        lessonModel3.setModule(moduleModel);

        return courseModel;
    }

    public static ModuleModel getModuleModel(UUID id) {
        ModuleModel moduleModel = new ModuleModel();
        moduleModel.setId(id);
        moduleModel.setTitle("Annotation do Spring Data");
        moduleModel.setDescription("Curso de Annotation do Spring Data");
        moduleModel.setCreationDate(DATE);
        return moduleModel;
    }

    public static LessonModel getLessonModel(UUID id) {
        LessonModel lessonModel = new LessonModel();
        lessonModel.setId(id);
        lessonModel.setVideoUrl("https://video.lesson.url");
        lessonModel.setTitle("Annotation Transactional");
        lessonModel.setDescription("Curso de Annotation do Spring Data");
        lessonModel.setCreationDate(DATE);
        return lessonModel;
    }
}
