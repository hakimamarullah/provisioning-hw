package com.voxloud.provisioning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voxloud.provisioning.models.ConfigFileDTO;

public interface ProvisioningService {

    String getProvisioningFile(String macAddress) throws JsonProcessingException;

    ConfigFileDTO getProvisioningFileDTO(String macAddress) throws JsonProcessingException;
}
