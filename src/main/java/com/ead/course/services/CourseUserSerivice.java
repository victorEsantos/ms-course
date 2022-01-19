package com.ead.course.services;

import com.ead.course.models.Course;
import com.ead.course.models.CourseUser;

import java.util.UUID;

public interface CourseUserSerivice {
    boolean existsByCourseAndUserId(Course course, UUID userId);

    boolean existsByUserId(UUID userId);

    CourseUser save(CourseUser courseUser);

    CourseUser saveAndSendSubscriptionUserInCourse(CourseUser courseUser);

    void deleteCourseUserByUser(UUID userId);
}
