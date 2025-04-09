package com.voxloud.provisioning.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties
@Setter
@Getter
public class ProvisioningProps {

    private Map<String, String> provisioning = new HashMap<>();

}
