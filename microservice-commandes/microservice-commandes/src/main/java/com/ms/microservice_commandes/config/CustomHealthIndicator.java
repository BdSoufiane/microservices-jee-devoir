package com.ms.microservice_commandes.config;



import com.ms.microservice_commandes.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private CommandeService commandeService;

    @Override
    public Health health() {
        long commandesCount = commandeService.getCommandesCount();

        if (commandesCount > 0) {
            return Health.up()
                    .withDetail("message", "Le microservice commandes est en bonne santé")
                    .withDetail("nombreCommandes", commandesCount)
                    .withDetail("status", "UP")
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Aucune commande dans la base de données")
                    .withDetail("nombreCommandes", commandesCount)
                    .withDetail("status", "DOWN")
                    .build();
        }
    }
}