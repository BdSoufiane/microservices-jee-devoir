package com.example.microservice_produit.config;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.simulation")
public class SimulationConfig {
    private boolean timeout = false;
    private int delay = 0;

    // Getters et Setters
    public boolean isTimeout() { return timeout; }
    public void setTimeout(boolean timeout) { this.timeout = timeout; }

    public int getDelay() { return delay; }
    public void setDelay(int delay) { this.delay = delay; }
}