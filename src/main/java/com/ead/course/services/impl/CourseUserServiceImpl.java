package com.ead.course.services.impl;

import com.ead.course.repositories.CourseUserRrepository;
import com.ead.course.services.CourseUserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseUserServiceImpl implements CourseUserSerivice {
    @Autowired
    CourseUserRrepository repository;
}
