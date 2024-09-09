package com.example.poc_error_handling.proposal;

import com.example.poc_error_handling.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestErrorHandling {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException() {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Business Exception 001");

        // details
        Map mapProperties = new HashMap();
        mapProperties.put("new-key-01", "new-value-01");
        mapProperties.put("new-key-02", "new-value-02");
        mapProperties.put("new-key-03", "new-value-03");
        problemDetail.setProperties(mapProperties);

        // trace id
        problemDetail.setProperty("traceid", "");
        return problemDetail;
    }
}
