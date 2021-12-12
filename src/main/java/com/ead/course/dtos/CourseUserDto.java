package com.ead.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class CourseUserDto {

    private UUID userId;

    @NotNull
    private UUID courseId;
}
