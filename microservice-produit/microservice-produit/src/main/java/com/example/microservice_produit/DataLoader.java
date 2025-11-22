package com.example.microservice_produit;




import com.example.microservice_produit.model.Produit;
import com.example.microservice_produit.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProduitRepository produitRepository;

    public DataLoader(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (produitRepository.count() == 0) {
            // Charger 20 produits de test
            produitRepository.save(new Produit("Laptop Dell XPS 13", "Laptop ultra-fin haute performance", 1500.00, 50, "Informatique"));
            produitRepository.save(new Produit("iPhone 15 Pro", "Smartphone Apple dernière génération", 1200.00, 100, "Téléphonie"));
            produitRepository.save(new Produit("Samsung Galaxy Tab S9", "Tablette Android premium", 800.00, 30, "Tablette"));
            produitRepository.save(new Produit("Monitor LG UltraGear", "Écran gaming 27 pouces 144Hz", 400.00, 25, "Périphériques"));
            produitRepository.save(new Produit("Clavier Mécanique Razer", "Clavier gaming mécanique RGB", 150.00, 75, "Périphériques"));

            produitRepository.save(new Produit("Souris Gaming Logitech", "Souris gaming précise 16000 DPI", 80.00, 100, "Périphériques"));
            produitRepository.save(new Produit("Webcam HD 4K", "Webcam haute définition 4K", 120.00, 40, "Périphériques"));
            produitRepository.save(new Produit("SSD 1TB Samsung 970", "SSD NVMe haute vitesse", 100.00, 60, "Composants"));
            produitRepository.save(new Produit("RAM 16GB Corsair", "Mémoire DDR4 3200MHz", 70.00, 80, "Composants"));
            produitRepository.save(new Produit("Alimentation 750W", "Alimentation certifiée 80+ Gold", 110.00, 35, "Composants"));

            produitRepository.save(new Produit("Carte Graphique RTX 4070", "Carte graphique gaming", 600.00, 20, "Composants"));
            produitRepository.save(new Produit("Carte Mère ASUS ROG", "Carte mère gaming Z790", 300.00, 15, "Composants"));
            produitRepository.save(new Produit("Processeur Intel i7", "Processeur 13ème génération", 350.00, 40, "Composants"));
            produitRepository.save(new Produit("Ventilateur CPU", "Ventilateur processeur haute performance", 60.00, 50, "Composants"));
            produitRepository.save(new Produit("Boîtier PC Gaming", "Boîtier gaming avec vitre trempée", 90.00, 25, "Composants"));

            produitRepository.save(new Produit("Switch Réseau 8 ports", "Switch Gigabit 8 ports", 50.00, 30, "Réseau"));
            produitRepository.save(new Produit("Routeur Wi-Fi 6", "Routeur haute vitesse Wi-Fi 6", 120.00, 20, "Réseau"));
            produitRepository.save(new Produit("Disque Dur Externe 2TB", "Disque dur externe portable", 80.00, 45, "Stockage"));
            produitRepository.save(new Produit("Hub USB-C", "Hub USB-C 7-en-1", 40.00, 60, "Accessoires"));
            produitRepository.save(new Produit("Support Laptop", "Support réglable pour ordinateur portable", 25.00, 80, "Accessoires"));

            System.out.println("✅ 20 produits de test chargés avec succès !");
        } else {
            System.out.println("ℹ️  Des données existent déjà (" + produitRepository.count() + " produits)");
        }
    }
}