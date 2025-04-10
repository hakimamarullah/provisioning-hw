package com.voxloud.provisioning.models;

import lombok.Data;

import java.util.Optional;

@Data
public class ConfigFileDTO {

    private String content;
    private String fileName;
    private String contentType;

    public int getLength() {
        return Optional.ofNullable(content)
                .map(String::getBytes)
                .map(b -> b.length)
                .orElse(0);
    }
}
