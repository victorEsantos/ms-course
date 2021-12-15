package com.ead.course.repositories;

import com.ead.course.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID>, JpaSpecificationExecutor<Lesson> {

    @Query(value = "select lesson from Lesson lesson where lesson.module.moduleId = :moduleId", nativeQuery = false)
    List<Lesson> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);

    @Query(value = "select lesson from Lesson lesson where lesson.module.moduleId = :moduleId and lesson.lessonId = :lessonId")
    Optional<Lesson> findLessonIntoModule(@Param("moduleId")UUID moduleId, @Param("lessonId")UUID lessonId);
}
