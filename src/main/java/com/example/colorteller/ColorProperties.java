package com.example.colorteller;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("color")
public record ColorProperties(String code) {
}
