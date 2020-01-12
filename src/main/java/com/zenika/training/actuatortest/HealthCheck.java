package com.zenika.training.actuatortest;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        if(Math.random() > 0.5) {
            return Health.unknown().withDetail("Error", "An error").build();
        }
        return Health.up().build();
    }
}
