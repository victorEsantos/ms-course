package com.ead.course.services;

import com.ead.course.models.Course;
import com.ead.course.models.CourseUser;

import java.util.UUID;

public interface CourseUserSerivice {
    boolean existsByCourseAndUserId(Course course, UUID userId);

    CourseUser save(CourseUser courseUser);
}
