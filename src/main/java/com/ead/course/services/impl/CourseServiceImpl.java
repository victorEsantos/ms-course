package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void delete(CourseModel courseModel) {
        List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
        if (!moduleModelList.isEmpty()) {
            moduleModelList.forEach(moduleModel -> {
                List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
                if (!lessonModelList.isEmpty()) {
                    lessonRepository.deleteAll(lessonModelList);
                }
            });

            moduleRepository.deleteAll(moduleModelList);
        }

        repository.delete(courseModel);
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        return repository.save(courseModel);

    }

    @Override
    public Optional<CourseModel> findById(UUID courseId) {
        return repository.findById(courseId);
    }

    @Override
    public List<CourseModel> findAll() {
        return repository.findAll();
    }
}
