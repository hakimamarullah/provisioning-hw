package com.voxloud.provisioning.exceptions;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
