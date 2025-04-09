package com.voxloud.provisioning.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voxloud.provisioning.entity.Device;

public interface DeviceConfigurationGenerator {

    String generateConfig(Device device) throws JsonProcessingException;
}
