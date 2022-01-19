package com.ead.course.services.impl;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.models.Course;
import com.ead.course.models.CourseUser;
import com.ead.course.repositories.CourseUserRrepository;
import com.ead.course.services.CourseUserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CourseUserServiceImpl implements CourseUserSerivice {
    @Autowired
    CourseUserRrepository repository;

    @Autowired
    AuthUserClient client;

    @Override
    public boolean existsByCourseAndUserId(Course course, UUID userId) {
        return repository.existsByCourseAndUserId(course, userId);
    }

    @Override
    public boolean existsByUserId(UUID userId){
        return repository.existsByUserId(userId);
    }

    @Override
    public CourseUser save(CourseUser courseUser) {
        return repository.save(courseUser);
    }

    @Transactional
    @Override
    public CourseUser saveAndSendSubscriptionUserInCourse(CourseUser courseUser) {
        courseUser = repository.save(courseUser);

        client.postSubscriptionUserInCourse(courseUser.getCourse().getCourseId(), courseUser.getUserId());

        return courseUser;
    }

    @Override
    public void deleteCourseUserByUser(UUID userId)
    {
        repository.deleteAllByUserId(userId);
    }
}
