package com.ms.microservice_commandes.controller;




import com.ms.microservice_commandes.model.Commande;
import com.ms.microservice_commandes.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        return commande.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commande createCommande(@Valid @RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id,
                                                   @Valid @RequestBody Commande commandeDetails) {
        Commande updatedCommande = commandeService.updateCommande(id, commandeDetails);
        return updatedCommande != null ? ResponseEntity.ok(updatedCommande)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        boolean deleted = commandeService.deleteCommande(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/last-days")
    public List<Commande> getCommandesLastDays() {
        return commandeService.getCommandesLastDays();
    }

    @GetMapping("/config")
    public String getConfig() {
        return "Configuration actuelle: afficher les commandes des " +
                commandeService.getCommandesLastConfig() + " derniers jours";
    }




    // Ajouter cet endpoint

    @GetMapping("/{id}/produit-details")
    @Operation(summary = "Obtenir les détails du produit d'une commande")
    public String getProduitDetailsForCommande(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        if (commande.isPresent() && commande.get().getIdProduit() != null) {
            return commandeService.getProduitDetails(commande.get().getIdProduit());
        } else {
            return "{\"error\": \"Aucun produit associé à cette commande\"}";
        }
    }

    @GetMapping("/test-circuit-breaker")
    @Operation(summary = "Tester le Circuit Breaker")
    public String testCircuitBreaker() {
        try {
            String produitDetails = commandeService.getProduitDetails(1L);
            return "✅ Service Produit disponible: " + produitDetails;
        } catch (Exception e) {
            return "❌ Circuit Breaker activé: " + e.getMessage();
        }
    }
}
