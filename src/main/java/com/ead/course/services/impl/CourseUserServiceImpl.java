package com.ead.course.services.impl;

import com.ead.course.models.Course;
import com.ead.course.models.CourseUser;
import com.ead.course.repositories.CourseUserRrepository;
import com.ead.course.services.CourseUserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseUserServiceImpl implements CourseUserSerivice {
    @Autowired
    CourseUserRrepository repository;

    @Override
    public boolean existsByCourseAndUserId(Course course, UUID userId) {
        return repository.existsByCourseAndUserId(course, userId);
    }

    @Override
    public CourseUser save(CourseUser courseUser) {
        return repository.save(courseUser);
    }
}
