/* (C)2024 */
package com.example.poc_error_handling.proposal;

import brave.Tracer;
import com.example.poc_error_handling.exception.BusinessException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandling {

    private final Tracer tracer;

    public RestErrorHandling(Tracer tracer) {
        this.tracer = tracer;
    }

    /**
     * Handles BusinessException and returns a ProblemDetail object.
     *
     * @return A ProblemDetail object with a conflict status and custom details.
     */
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        final ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        Map mapProperties = new HashMap();
        mapProperties.put("new-key-01", "new-value-01");
        mapProperties.put("new-key-02", "new-value-02");
        mapProperties.put("new-key-03", "new-value-03");
        problemDetail.setProperty("traceid", tracer.currentSpan().context().traceIdString());
        return problemDetail;
    }
}
