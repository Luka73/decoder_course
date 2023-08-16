package com.ead.course.services;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.Impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.ead.course.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;
    @Mock
    ModuleRepository moduleRepository;
    @Mock
    LessonRepository lessonRepository;
    @InjectMocks
    CourseServiceImpl courseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CourseModel courseModel = getCourseModel(null);
        CourseModel expected = getCourseModel(MyUUID);

        when(courseRepository.save(courseModel)).thenReturn(expected);

        CourseModel actual = courseService.save(courseModel);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }


    @Test
    void testFindById() {
        Optional<CourseModel> expected = Optional.of(getCourseModel(MyUUID));

        when(courseRepository.findById(MyUUID)).thenReturn(expected);

        Optional<CourseModel> actual = courseService.findById(MyUUID);

        assertThat(actual.get())
                .usingRecursiveComparison()
                .isEqualTo(expected.get());
    }


    @Test
    void testDelete() {

        CourseModel fullCourseModel = getFullCourseModel();
        List<ModuleModel> modules = (List<ModuleModel>) fullCourseModel.getModules();
        List<LessonModel> lessons = (List<LessonModel>) modules.get(0).getLessons();

        when(moduleRepository.findAllModulesIntoCourse(fullCourseModel.getId())).thenReturn(modules);
        when(lessonRepository.findAllLessonsIntoModule(modules.get(0).getId())).thenReturn(lessons);
    }

}
