package com.ead.course.services;

import com.ead.course.models.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {
    void delete(Module module);

    Optional<Module> findById(UUID moduleId);

    void save(Module module);

    Optional<Module> findModuleIntoCourse(UUID courseId, UUID moduleId);

    List<Module> findAllByCourse(UUID courseId);

    Page<Module> findAllByCourse(Specification<Module> spec, Pageable pageable);
}
