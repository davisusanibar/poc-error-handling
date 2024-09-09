package com.example.poc_error_handling.api;

import com.example.poc_error_handling.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sb")
public class RestAPI {

    @GetMapping("/testme")
    public List<String> callMe() {

        return List.of("Uno", "Dos");
    }


    @GetMapping("/testme/exception")
    public List<String> callMeWithException() {
        throw new BusinessException("Business Exception 001 - call: /callme/exception");
    }

}
