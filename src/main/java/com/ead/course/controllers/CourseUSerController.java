package com.ead.course.controllers;

import com.ead.course.clients.CourseClient;
import com.ead.course.dtos.SubscriptionDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.models.Course;
import com.ead.course.services.CourseService;
import com.ead.course.services.CourseUserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUSerController {

    @Autowired
    CourseClient courseClient;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseUserSerivice courseUserSerivice;

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Page<UserDto>> getAllUsersByCourse(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                             @PathVariable(value = "courseId") UUID courseId){
        return ResponseEntity.ok().body(courseClient.getAllUsersByCourse(courseId, pageable));

    }

    @PostMapping("/courses/{courseId}/users/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "courseId") UUID courseId,
                                                               @RequestBody @Valid SubscriptionDto dto){

        Optional<Course> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }

        if(courseUserSerivice.existsByCourseAndUserId(courseModelOptional.get(), dto.getUserId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Subscription already exists.");
        }
        //verificacao de user

        var courseUser = courseUserSerivice.save(courseModelOptional.get().convertToCourseUser(dto.getUserId()));

        return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully");
    }
}
