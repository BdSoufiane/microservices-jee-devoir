package com.ms.microservice_commandes.service;



import com.ms.microservice_commandes.dto.CommandeWithProduitDTO;
import com.ms.microservice_commandes.dto.ProduitDTO;

import com.ms.microservice_commandes.config.CustomConfig;
import com.ms.microservice_commandes.model.Commande;
import com.ms.microservice_commandes.repository.CommandeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private RestTemplate restTemplate;








    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }




    public Commande updateCommande(Long id, Commande commandeDetails) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setDescription(commandeDetails.getDescription());
            commande.setQuantite(commandeDetails.getQuantite());
            commande.setDate(commandeDetails.getDate());
            commande.setMontant(commandeDetails.getMontant());
            return commandeRepository.save(commande);
        }
        return null;
    }

    public boolean deleteCommande(Long id) {
        if (commandeRepository.existsById(id)) {
            commandeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Commande> getCommandesLastDays() {
        LocalDate startDate = LocalDate.now().minusDays(customConfig.getCommandesLast());
        return commandeRepository.findCommandesFromLastDays(startDate);
    }

    public int getCommandesLastConfig() {
        return customConfig.getCommandesLast();
    }

    public long getCommandesCount() {
        return commandeRepository.count();
    }





    // Ajouter ces méthodes
    public List<CommandeWithProduitDTO> getAllCommandesWithProduit() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream()
                .map(this::convertToCommandeWithProduitDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommandeWithProduitDTO> getCommandeWithProduitById(Long id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        return commande.map(this::convertToCommandeWithProduitDTO);
    }

    public List<CommandeWithProduitDTO> getCommandesLastDaysWithProduit() {
        LocalDate startDate = LocalDate.now().minusDays(customConfig.getCommandesLast());
        List<Commande> commandes = commandeRepository.findCommandesFromLastDays(startDate);
        return commandes.stream()
                .map(this::convertToCommandeWithProduitDTO)
                .collect(Collectors.toList());
    }

    private CommandeWithProduitDTO convertToCommandeWithProduitDTO(Commande commande) {
        CommandeWithProduitDTO dto = new CommandeWithProduitDTO();
        dto.setId(commande.getId());
        dto.setDescription(commande.getDescription());
        dto.setQuantite(commande.getQuantite());
        dto.setDate(commande.getDate());
        dto.setMontant(commande.getMontant());
        dto.setIdProduit(commande.getIdProduit());

        // Appel au service produit avec circuit breaker
        try {
            ProduitDTO produit = produitService.getProduitById(commande.getIdProduit());
            dto.setProduit(produit);
        } catch (Exception e) {
            // Le circuit breaker gère déjà le fallback
            ProduitDTO fallbackProduit = new ProduitDTO();
            fallbackProduit.setId(commande.getIdProduit());
            fallbackProduit.setNom("Information produit non disponible");
            fallbackProduit.setDescription("Erreur de communication avec le service produit");
            dto.setProduit(fallbackProduit);
        }

        return dto;
    }




    // Méthode pour obtenir les détails du produit avec Circuit Breaker
    @CircuitBreaker(name = "produitService", fallbackMethod = "getProduitDetailsFallback")
    @Retry(name = "produitService")
    public String getProduitDetails(Long produitId) {
        System.out.println("🔍 Appel du service produit pour ID: " + produitId);
        String url = "http://microservice-produit/api/produits/" + produitId;
        return restTemplate.getForObject(url, String.class);
    }

    // Fallback method
    public String getProduitDetailsFallback(Long produitId, Exception e) {
        System.out.println("🔴 Circuit Breaker ACTIVÉ dans Commandes - Fallback pour produit " + produitId);
        return "{\"nom\":\"Produit indisponible - Circuit Breaker\", " +
                "\"prix\":0, " +
                "\"message\":\"Service produit temporairement indisponible: " + e.getMessage() + "\"}";
    }
}