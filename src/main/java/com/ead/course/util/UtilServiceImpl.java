package com.ead.course.util;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilServiceImpl implements UtilsService{
    final String REQUEST_URI = "http://localhost:8087";

    public String createUrl(UUID courseId, Pageable pageable){
        return REQUEST_URI + "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}
