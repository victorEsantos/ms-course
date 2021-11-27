package com.ead.course.repositories;

import com.ead.course.models.Module;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID>, JpaSpecificationExecutor<Module> {

    //consulta com fetchtype EAGER
    @EntityGraph(attributePaths = {"course"})
    Module findByTitle(String title);

    //@Query(value = "select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    @Query(value = "select module from Module module where module.course.courseId = : courseId", nativeQuery = false)
    List<Module> findAllModulesIntoCourse(@Param("courseId") UUID courseId);

    @Query(value = "select * from tb_modules where course_course_id = :courseId and module_id = :moduleId", nativeQuery = true)
    Optional<Module> findModuleIntoCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}
