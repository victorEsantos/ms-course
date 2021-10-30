package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

    //consulta com fetchtype EAGER
    @EntityGraph(attributePaths = {"course"})
    ModuleModel findByTitle(String title);

//    @Query(value = "select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    @Query(value = "select module from ModuleModel module where module.course.courseId = : courseId and module.moduleId = :moduleId", nativeQuery = false)
    List<ModuleModel>findAllModulesIntoCourse(@Param("courseId") UUID courseId);

    @Query(value = "select module from ModuleModel module where module.course.courseId = : courseId and module.moduleId = :moduleId")
    Optional<ModuleModel> findModuleIntoCourse(@Param("courseId")UUID courseId, @Param("moduleId")UUID moduleId);
}
