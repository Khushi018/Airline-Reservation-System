package com.airline.Airline;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        // Simulate a check for an external API or database
        boolean apiHealthy = checkExternalService();

        if (apiHealthy) {
            return Health.status(apiHealthy ? "Running" : "Not Running").build();
        } else {
            return Health.status(apiHealthy ? "Running" : "Not Running").build();
        }
    }

    private boolean checkExternalService() {
        return true; 
    }
}
 
