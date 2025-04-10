package com.voxloud.provisioning;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(
        info = @Info(
                title = "Provisioning Service",
                version = "1.0",
                description = "Provisioning Service",
                contact = @Contact(name = "Captain", email = "hakimamarullah@gmail.com")
        )
)
public class ProvisioningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvisioningApplication.class, args);
    }

}