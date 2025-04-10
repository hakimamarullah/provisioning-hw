package com.voxloud.provisioning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voxloud.provisioning.models.ConfigFileDTO;
import com.voxloud.provisioning.service.ProvisioningService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class ProvisioningController {

    private final ProvisioningService provisioningService;

    public ProvisioningController(ProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    @GetMapping("/provisioning/{macAddress}")
    public ResponseEntity<String> generateConfig(@PathVariable String macAddress) throws JsonProcessingException {
        return ResponseEntity.ok(provisioningService.getProvisioningFile(macAddress));
    }

    @GetMapping(value = "/download/{macAddress}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadConfigFile(@PathVariable String macAddress) throws JsonProcessingException {
        ConfigFileDTO fileDTO = provisioningService.getProvisioningFileDTO(macAddress);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getFileName() + "\"")
                .contentLength(fileDTO.getLength())
                .body(stream(fileDTO));

    }

    private StreamingResponseBody stream(ConfigFileDTO fileDTO) {
        return outputStream -> {
            try (InputStream inputStream = new ByteArrayInputStream(fileDTO.getContent().getBytes())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        };
    }
}