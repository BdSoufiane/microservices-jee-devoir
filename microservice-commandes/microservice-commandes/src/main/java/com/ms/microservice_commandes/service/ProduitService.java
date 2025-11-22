package com.ms.microservice_commandes.service;



import com.ms.microservice_commandes.dto.ProduitDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProduitService {

    private static final String PRODUIT_SERVICE_URL = "http://microservice-produits/api/produits";

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "produitService", fallbackMethod = "getProduitByIdFallback")
    public ProduitDTO getProduitById(Long id) {
        String url = PRODUIT_SERVICE_URL + "/" + id;
        return restTemplate.getForObject(url, ProduitDTO.class);
    }

    @CircuitBreaker(name = "produitService", fallbackMethod = "getAllProduitsFallback")
    public List<ProduitDTO> getAllProduits() {
        ProduitDTO[] produits = restTemplate.getForObject(PRODUIT_SERVICE_URL, ProduitDTO[].class);
        return Arrays.asList(produits != null ? produits : new ProduitDTO[0]);
    }

    // Fallback methods
    public ProduitDTO getProduitByIdFallback(Long id, Exception e) {
        System.out.println("⚠️  Fallback activé pour getProduitById(" + id + "): " + e.getMessage());

        ProduitDTO fallbackProduit = new ProduitDTO();
        fallbackProduit.setId(id);
        fallbackProduit.setNom("Produit non disponible - Service temporairement indisponible");
        fallbackProduit.setDescription("Le service produit est momentanément indisponible. Circuit Breaker activé.");
        fallbackProduit.setPrix(0.0);
        fallbackProduit.setQuantiteStock(0);
        fallbackProduit.setCategorie("Indisponible");
        return fallbackProduit;
    }

    public List<ProduitDTO> getAllProduitsFallback(Exception e) {
        System.out.println("⚠️  Fallback activé pour getAllProduits(): " + e.getMessage());
        return List.of();
    }
}
