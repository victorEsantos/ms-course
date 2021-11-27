package com.ead.course.services.impl;

import com.ead.course.models.Lesson;
import com.ead.course.models.Module;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository repository;

    @Autowired
    private LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(Module module) {
        List<Lesson> lessonList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
        if (!lessonList.isEmpty()) {
            lessonRepository.deleteAll(lessonList);
        }

        repository.delete(module);
    }

    @Override
    public Optional<Module> findById(UUID moduleId) {
        return repository.findById(moduleId);
    }

    @Override
    public void save(Module module) {
        repository.save(module);
    }

    @Override
    public Optional<Module> findModuleIntoCourse(UUID courseId, UUID moduleId) {
        return repository.findModuleIntoCourse(courseId, moduleId);
    }

    @Override
    public List<Module> findAllByCourse(UUID courseId) {
        return repository.findAllModulesIntoCourse(courseId);
    }

    @Override
    public Page<Module> findAllByCourse(Specification<Module> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
