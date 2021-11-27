package com.ead.course.services.impl;

import com.ead.course.models.Lesson;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository repository;

    @Override
    public void delete(Lesson lesson) {
        repository.delete(lesson);
    }

    @Override
    public void save(Lesson lesson) {
        repository.save(lesson);
    }

    @Override
    public Optional<Lesson> findLessonIntoModule(UUID moduleId, UUID lessonId) {
        return repository.findLessonIntoModule(moduleId, lessonId);
    }

    @Override
    public List<Lesson> findAllByModule(UUID moduleId) {
        return repository.findAllLessonsIntoModule(moduleId);
    }

    @Override
    public Page<Lesson> findAllByModule(Specification<Lesson> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
