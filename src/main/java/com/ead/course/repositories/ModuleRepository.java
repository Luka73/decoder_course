package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {

//    @EntityGraph(attributePaths = {"course"})
//    ModuleModel findByTitle(String title);

    @Query(value = "select * from tb_modules where course_id =:course_id", nativeQuery = true)
    List<ModuleModel> findAllModulesIntoCourse(@Param("course_id") UUID courseId);

    @Query(value = "select * from tb_modules where course_id = :course_id and id =:module_id", nativeQuery = true)
    Optional<ModuleModel> findModuleIntoCourse(@Param("course_id") UUID courseId, @Param("module_id") UUID moduleId);
}
