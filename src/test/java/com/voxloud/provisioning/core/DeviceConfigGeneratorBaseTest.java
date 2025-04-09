package com.voxloud.provisioning.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.provisioning.config.ProvisioningProps;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceConfigGeneratorBaseTest {

    @Mock
    protected ProvisioningProps provisioningProps;

    protected final ObjectMapper mapper = new ObjectMapper();



    protected Map<String, String> getProvisioning() {
        Map<String, String> provisioning = new HashMap<>();
        provisioning.put("domain", "default.domain.com");
        provisioning.put("port", "5161");
        return provisioning;
    }

    @Test
    void test() {
        assertNotNull(getProvisioning());
    }
}
