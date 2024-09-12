/* (C)2024 */
package com.example.poc_error_handling.api;

import brave.Tracer;
import brave.baggage.BaggageField;
import com.example.poc_error_handling.exception.BusinessException;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sb")
@Slf4j
public class RestAPI {
    private final Tracer tracer;

    @NonNull
    @Qualifier("idToPropagate01")
    private final BaggageField idToPropagate01; // information that we need to propagate

    @NonNull
    @Qualifier("idToPropagate02")
    private final BaggageField idToPropagate02; // information that we need to propagate

    @Autowired
    public RestAPI(
            Tracer tracer,
            @Qualifier("idToPropagate01") BaggageField idToPropagate01,
            @Qualifier("idToPropagate02") BaggageField idToPropagate02) {
        this.tracer = tracer;
        this.idToPropagate01 = idToPropagate01;
        this.idToPropagate02 = idToPropagate02;
    }

    @GetMapping("/testme")
    public List<String> callMe() {
        idToPropagate01.updateValue("id-propagate-0001");
        idToPropagate02.updateValue("id-propagate-0002");
        log.info("Proceso /testme");
        return List.of("Uno", "Dos", tracer.currentSpan().context().traceIdString());
    }

    @GetMapping("/testme/exception")
    public List<String> callMeWithException() {
        idToPropagate01.updateValue("id-propagate-0001");
        idToPropagate02.updateValue("id-propagate-0002");
        log.error("Proceso /testme/exception");
        throw new BusinessException("Business Exception 001 - call: /testme/exception");
    }
}
