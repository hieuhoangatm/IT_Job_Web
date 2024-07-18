package com.dinhhieu.jobitweb.controller;

import com.dinhhieu.jobitweb.domain.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {
    @GetMapping("/home")
    public RestResponse getData() {
        RestResponse response = new RestResponse();
        response.setMessage("Some data");
        return response;
    }
}
