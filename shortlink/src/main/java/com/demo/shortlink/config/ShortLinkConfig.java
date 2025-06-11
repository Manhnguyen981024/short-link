package com.demo.shortlink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "short-link")
public class ShortLinkConfig {
    private String allowedCharacters;
    private int keyLength;
}
