package com.example.microservice_produit.service;





import com.example.microservice_produit.config.SimulationConfig;
import com.example.microservice_produit.model.Produit;
import com.example.microservice_produit.repository.ProduitRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;







@Service
public class ProduitService {


    private Random random = new Random();

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private SimulationConfig simulationConfig;

    public List<Produit> getAllProduits() {
        simulateTimeout();
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        simulateTimeout();
        return produitRepository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        simulateTimeout();
        Optional<Produit> optionalProduit = produitRepository.findById(id);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setNom(produitDetails.getNom());
            produit.setDescription(produitDetails.getDescription());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantiteStock(produitDetails.getQuantiteStock());
            produit.setCategorie(produitDetails.getCategorie());
            return produitRepository.save(produit);
        }
        return null;
    }

    public boolean deleteProduit(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void simulateTimeout() {
        if (simulationConfig.isTimeout() && simulationConfig.getDelay() > 0) {
            try {
                Thread.sleep(simulationConfig.getDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    // Méthode avec Circuit Breaker pour simuler timeout
    @CircuitBreaker(name = "produitService", fallbackMethod = "getProduitWithTimeoutFallback")
    @TimeLimiter(name = "produitService")
    @Retry(name = "produitService")
    public CompletableFuture<Produit> getProduitWithTimeout(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulation de timeout aléatoire (40% de chance)
            try {
                if (random.nextDouble() > 0.6) {
                    System.out.println("⏰ Simulation de timeout pour le produit " + id);
                    TimeUnit.SECONDS.sleep(5); // Timeout de 5 secondes
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Timeout simulé", e);
            }

            return produitRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé - ID: " + id));
        });
    }

    // Fallback method pour Circuit Breaker (version avec setters)
    public CompletableFuture<Produit> getProduitWithTimeoutFallback(Long id, Exception e) {
        System.out.println("🔴 Circuit Breaker ACTIVÉ - Fallback pour produit " + id);

        Produit fallbackProduit = new Produit();
        fallbackProduit.setNom("Produit temporairement indisponible");
        fallbackProduit.setDescription("Service en maintenance - Circuit Breaker activé (" + e.getMessage() + ")");
        fallbackProduit.setPrix(0.0);
        fallbackProduit.setQuantiteStock(0);

        return CompletableFuture.completedFuture(fallbackProduit);
    }

    // Méthode pour simuler des erreurs aléatoires
    @CircuitBreaker(name = "produitService", fallbackMethod = "getProduitWithErrorFallback")
    @Retry(name = "produitService")
    public Produit getProduitWithRandomError(Long id) {
        // 30% de chance d'erreur
        if (random.nextDouble() > 0.7) {
            System.out.println("Simulation d'erreur aléatoire pour produit " + id);
            throw new RuntimeException("Erreur simulée du service produit");
        }

        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    // Fallback pour erreurs aléatoires (version avec setters)
    public Produit getProduitWithErrorFallback(Long id, Exception e) {
        System.out.println("🔴 Fallback pour erreur - Produit " + id);

        Produit fallbackProduit = new Produit();
        fallbackProduit.setNom("Produit en erreur");
        fallbackProduit.setDescription("Service temporairement indisponible - " + e.getMessage());
        fallbackProduit.setPrix(0.0);
        fallbackProduit.setQuantiteStock(0);

        return fallbackProduit;
    }



}
