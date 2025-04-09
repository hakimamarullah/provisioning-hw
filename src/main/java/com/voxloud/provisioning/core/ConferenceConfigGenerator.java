package com.voxloud.provisioning.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.provisioning.config.ProvisioningProps;
import com.voxloud.provisioning.entity.Device;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component(ConfigBeanType.CONFERENCE)
public class ConferenceConfigGenerator implements DeviceConfigurationGenerator {

    private final ProvisioningProps provisioningProps;

    private final ObjectMapper mapper;

    public ConferenceConfigGenerator(ProvisioningProps provisioningProps, ObjectMapper mapper) {
        this.provisioningProps = provisioningProps;
        this.mapper = mapper;
    }

    @Override
    public String generateConfig(Device device) throws JsonProcessingException {
        Map<String, Object> mergeConfig = new HashMap<>(provisioningProps.getProvisioning());

        if (!Objects.isNull(device.getOverrideFragment())) {
            mergeConfig.putAll(mapper.readValue(device.getOverrideFragment(), new TypeReference<Map<String, Object>>() {
            }));
        }
        mergeConfig.put("username", device.getUsername());
        mergeConfig.put("password", device.getPassword());

        return mapper.writeValueAsString(mergeConfig);
    }
}
