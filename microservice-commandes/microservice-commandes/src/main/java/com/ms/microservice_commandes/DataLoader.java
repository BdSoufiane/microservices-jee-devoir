package com.ms.microservice_commandes;




import com.ms.microservice_commandes.model.Commande;
import com.ms.microservice_commandes.repository.CommandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CommandeRepository commandeRepository;

    public DataLoader(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (commandeRepository.count() == 0) {
            // Charger 20 commandes de test avec idProduit
            commandeRepository.save(new Commande("Commande Laptops Dell XPS", 5, LocalDate.now(), 7500.00, 1L));
            commandeRepository.save(new Commande("Commande iPhone 15 Pro", 10, LocalDate.now().minusDays(1), 8000.00, 2L));
            commandeRepository.save(new Commande("Commande Samsung Galaxy Tabs", 8, LocalDate.now().minusDays(2), 4800.00, 3L));
            commandeRepository.save(new Commande("Commande Monitors LG UltraGear", 3, LocalDate.now().minusDays(3), 1500.00, 4L));
            commandeRepository.save(new Commande("Commande Keyboards Mechanical", 15, LocalDate.now().minusDays(4), 1200.00, 5L));

            commandeRepository.save(new Commande("Commande Gaming Mice Logitech", 20, LocalDate.now().minusDays(5), 1000.00, 6L));
            commandeRepository.save(new Commande("Commande Webcams HD 4K", 7, LocalDate.now().minusDays(6), 700.00, 7L));
            commandeRepository.save(new Commande("Commande SSD 1TB Samsung", 12, LocalDate.now().minusDays(7), 2400.00, 8L));
            commandeRepository.save(new Commande("Commande RAM 16GB Corsair", 25, LocalDate.now().minusDays(8), 2000.00, 9L));
            commandeRepository.save(new Commande("Commande Power Supplies", 6, LocalDate.now().minusDays(9), 900.00, 10L));

            commandeRepository.save(new Commande("Commande Graphics Cards RTX", 4, LocalDate.now().minusDays(10), 3200.00, 11L));
            commandeRepository.save(new Commande("Commande Motherboards ASUS", 8, LocalDate.now().minusDays(11), 1600.00, 12L));
            commandeRepository.save(new Commande("Commande CPU Intel i7", 10, LocalDate.now().minusDays(12), 3000.00, 13L));
            commandeRepository.save(new Commande("Commande CPU Coolers", 15, LocalDate.now().minusDays(13), 750.00, 14L));
            commandeRepository.save(new Commande("Commande PC Cases", 5, LocalDate.now().minusDays(14), 1250.00, 15L));

            commandeRepository.save(new Commande("Commande Network Switches", 3, LocalDate.now().minusDays(15), 600.00, 16L));
            commandeRepository.save(new Commande("Commande Wireless Routers", 8, LocalDate.now().minusDays(16), 1200.00, 17L));
            commandeRepository.save(new Commande("Commande External Hard Drives", 12, LocalDate.now().minusDays(17), 1800.00, 18L));
            commandeRepository.save(new Commande("Commande USB-C Hubs", 20, LocalDate.now().minusDays(18), 800.00, 19L));
            commandeRepository.save(new Commande("Commande Laptop Stands", 10, LocalDate.now().minusDays(19), 500.00, 20L));

            System.out.println("✅ 20 commandes de test chargées avec succès !");
        } else {
            System.out.println("ℹ️  Des données existent déjà (" + commandeRepository.count() + " commandes)");
        }
    }
}