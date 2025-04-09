package com.voxloud.provisioning.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.voxloud.provisioning.entity.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class ConferenceConfigGeneratorTest extends DeviceConfigGeneratorBaseTest {

    @InjectMocks
    private ConferenceConfigGenerator conferenceConfigGenerator;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doReturn(getProvisioning()).when(provisioningProps).getProvisioning();
        ReflectionTestUtils.setField(conferenceConfigGenerator, "mapper", mapper);
    }

    @Test
    void givenOverrideFragmentIsNotNullWhenGenerateConfigThenShouldReturnCorrectConfig() throws JsonProcessingException {
        // Inputs
        String overrideFragment = "{\"domain\":\"override.domain.com\"}";
        Device device = new Device();
        device.setOverrideFragment(overrideFragment);
        device.setUsername("username");
        device.setPassword("password");


        // Act
        String actual = conferenceConfigGenerator.generateConfig(device);

        // Assert
        Map<String, String> expected = new HashMap<>();
        expected.put("domain", "override.domain.com");
        expected.put("port", "5161");
        expected.put("username", "username");
        expected.put("password", "password");
        assertEquals(expected, mapper.readValue(actual, new TypeReference<Map<String, String>>() {
        }));
    }

    @Test
    void givenOverrideFragmentIsNullWhenGenerateConfigThenShouldReturnCorrectConfig() throws JsonProcessingException {
        // Inputs
        Device device = new Device();
        device.setUsername("username");
        device.setPassword("password");


        // Act
        String actual = conferenceConfigGenerator.generateConfig(device);

        // Assert
        Map<String, String> expected = getProvisioning();
        expected.put("username", "username");
        expected.put("password", "password");
        assertEquals(expected, mapper.readValue(actual, new TypeReference<Map<String, String>>() {
        }));
    }


}