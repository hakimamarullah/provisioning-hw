package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.exceptions.DeviceNotFoundException;
import com.voxloud.provisioning.exceptions.InvalidDeviceModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerAdvisor {

    @ExceptionHandler({DeviceNotFoundException.class})
    public ResponseEntity<String> deviceNotFoundExceptionHandler(DeviceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDeviceModelException.class})
    public ResponseEntity<String> invalidDeviceModelExceptionHandler() {
        return new ResponseEntity<>("Invalid Device Type", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error("Internal Server Error", e);
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
