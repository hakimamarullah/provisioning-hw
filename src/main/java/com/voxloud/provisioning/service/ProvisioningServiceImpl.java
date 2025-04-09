package com.voxloud.provisioning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voxloud.provisioning.core.DeviceConfigurationGenerator;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.exceptions.DeviceNotFoundException;
import com.voxloud.provisioning.exceptions.InvalidDeviceModelException;
import com.voxloud.provisioning.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProvisioningServiceImpl implements ProvisioningService {

    private final BeanFactory beanFactory;

    private final DeviceRepository deviceRepository;


    @Override
    public String getProvisioningFile(String macAddress) throws JsonProcessingException {
        Device device = deviceRepository.findFirstByMacAddress(macAddress).orElseThrow(() -> new DeviceNotFoundException("Device Not Found: " + macAddress));
        if (Objects.isNull(device.getModel())) {
            throw new InvalidDeviceModelException();
        }
        DeviceConfigurationGenerator deviceConfigurationGenerator = (DeviceConfigurationGenerator) beanFactory.getBean(device.getModel().getName());
        return deviceConfigurationGenerator.generateConfig(device);
    }
}
