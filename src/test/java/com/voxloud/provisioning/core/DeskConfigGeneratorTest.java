package com.voxloud.provisioning.core;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.utils.MapUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class DeskConfigGeneratorTest extends DeviceConfigGeneratorBaseTest {

    @InjectMocks
    private DeskConfigGenerator deskConfigGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doReturn(getProvisioning()).when(provisioningProps).getProvisioning();
    }

    @Test
    void givenOverrideFragmentIsNotNullWhenGenerateConfigThenShouldReturnCorrectConfig() {
        // Input
        String overrideFragment = "domain=override.domain.com\nport=5162";
        Device device = new Device();
        device.setOverrideFragment(overrideFragment);
        device.setUsername("username");
        device.setPassword("password");

        // Act
        String actual = deskConfigGenerator.generateConfig(device);

        // Assert
        Map<String, String> expected = new HashMap<>();
        expected.put("domain", "override.domain.com");
        expected.put("port", "5162");
        expected.put("username", "username");
        expected.put("password", "password");
        assertEquals(expected, MapUtils.fromPropertyString(actual));
    }

    @Test
    void givenOverrideFragmentIsNullWhenGenerateConfigThenShouldReturnCorrectConfig() {
        // Input
        Device device = new Device();
        device.setUsername("username");
        device.setPassword("password");

        // Act
        String actual = deskConfigGenerator.generateConfig(device);

        // Assert
        Map<String, String> expected = getProvisioning();
        expected.put("username", "username");
        expected.put("password", "password");
        assertEquals(expected, MapUtils.fromPropertyString(actual));
    }
}