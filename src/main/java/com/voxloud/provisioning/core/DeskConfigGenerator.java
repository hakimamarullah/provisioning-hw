package com.voxloud.provisioning.core;

import com.voxloud.provisioning.config.ProvisioningProps;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("desk")
@RequiredArgsConstructor
public class DeskConfigGenerator implements DeviceConfigurationGenerator {

    private final ProvisioningProps provisioningProps;

    @Override
    public String generateConfig(Device device) {
        Map<String, Object> mergeConfig = new HashMap<>(provisioningProps.getProvisioning());
        mergeConfig.putAll(MapUtils.fromPropertyString(device.getOverrideFragment()));
        mergeConfig.put("username", device.getUsername());
        mergeConfig.put("password", device.getPassword());
        return MapUtils.writeAsPropertiesString(mergeConfig);
    }
}
