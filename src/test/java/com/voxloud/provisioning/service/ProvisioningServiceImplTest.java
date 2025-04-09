package com.voxloud.provisioning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voxloud.provisioning.core.DeskConfigGenerator;
import com.voxloud.provisioning.core.DeviceConfigurationGenerator;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.exceptions.DeviceNotFoundException;
import com.voxloud.provisioning.exceptions.InvalidDeviceModelException;
import com.voxloud.provisioning.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.BeanFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProvisioningServiceImplTest {

    @Mock
    private BeanFactory beanFactory;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private ProvisioningServiceImpl provisioningService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenDeviceModelNullWhenGetProvisioningFileThenShouldThrowInvalidDeviceModelException() {
        // Input
        String macAddress = "00:00:00:00:00:00";

        // Mock Device
        Device device = new Device();
        doReturn(Optional.of(device)).when(deviceRepository).findFirstByMacAddress(macAddress);

        // Act and assert
        assertThrows(InvalidDeviceModelException.class, () -> provisioningService.getProvisioningFile(macAddress));
    }

    @Test
    void givenDeviceConfigurationGeneratorWhenGetProvisioningFileThenShouldReturnProvisioningFile() throws JsonProcessingException {
        // Input
        String macAddress = "00:00:00:00:00:00";
        String provisioningFile = "provisioning file content";

        // Mock Device
        Device device = new Device();
        device.setMacAddress(macAddress);
        device.setModel(Device.DeviceModel.DESK);
        doReturn(Optional.of(device)).when(deviceRepository).findFirstByMacAddress(macAddress);

        // Mock DeviceConfigurationGenerator
        DeviceConfigurationGenerator deskGenerator = mock(DeskConfigGenerator.class);
        doReturn(provisioningFile).when(deskGenerator).generateConfig(device);
        doReturn(deskGenerator).when(beanFactory).getBean(Device.DeviceModel.DESK.getName());

        // Act
        String actual = provisioningService.getProvisioningFile(macAddress);

        // Assert
        assertEquals(provisioningFile, actual);

        verify(deviceRepository).findFirstByMacAddress(macAddress);
        verify(deskGenerator).generateConfig(device);
        verify(beanFactory).getBean(Device.DeviceModel.DESK.getName());
    }

    @Test
    void givenDeviceIsNotFoundWhenGetProvisioningFileThenShouldThrowDeviceNotFoundException() {
        // Input
        String macAddress = "00:00:00:00:00:00";

        // Mock Device
        doReturn(Optional.empty()).when(deviceRepository).findFirstByMacAddress(macAddress);

        // Act and assert
        try {
            provisioningService.getProvisioningFile(macAddress);
            fail("Should throw DeviceNotFoundException");
        } catch (DeviceNotFoundException e) {
            assertEquals("Device Not Found: " + macAddress, e.getMessage());
        } catch (JsonProcessingException e) {
            fail("Should not throw JsonProcessingException");
        }
    }


}