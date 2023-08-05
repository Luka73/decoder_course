package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    @Query(value = "select * from td_lessons where module_id =:id", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("id") UUID moduleId);

    @Query(value = "select * from tb_lessons where module_id =:module_id and id =:lesson_id", nativeQuery = true)
    Optional<LessonModel> findLessonIntoModule(@Param("module_id") UUID moduleId, @Param("lesson_id") UUID lessonId);
}
