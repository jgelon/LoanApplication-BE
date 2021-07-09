package nl.testautomation.demoapplication.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties
public class ApplicationConfig {
    private Map<String, String> endpoints;
}