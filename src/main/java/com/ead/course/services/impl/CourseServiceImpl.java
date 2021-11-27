package com.ead.course.services.impl;

import com.ead.course.models.Course;
import com.ead.course.models.Lesson;
import com.ead.course.models.Module;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
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
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    @Transactional
    public void delete(Course course) {
        List<Module> moduleList = moduleRepository.findAllModulesIntoCourse(course.getCourseId());
        if (!moduleList.isEmpty()) {
            moduleList.forEach(moduleModel -> {
                List<Lesson> lessonList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
                if (!lessonList.isEmpty()) {
                    lessonRepository.deleteAll(lessonList);
                }
            });

            moduleRepository.deleteAll(moduleList);
        }

        repository.delete(course);
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);

    }

    @Override
    public Optional<Course> findById(UUID courseId) {
        return repository.findById(courseId);
    }

    @Override
    public Page<Course> findAll(Specification<Course> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
