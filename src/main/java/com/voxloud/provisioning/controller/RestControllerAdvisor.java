package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.exceptions.DeviceNotFoundException;
import com.voxloud.provisioning.exceptions.InvalidDeviceModelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler({DeviceNotFoundException.class})
    public ResponseEntity<String> deviceNotFoundExceptionHandler(DeviceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDeviceModelException.class})
    public ResponseEntity<String> invalidDeviceModelExceptionHandler() {
        return new ResponseEntity<>("Invalid Device Type", HttpStatus.BAD_REQUEST);
    }
}
