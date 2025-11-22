package com.example.microservice_produit.controller;





import com.example.microservice_produit.model.Produit;
import com.example.microservice_produit.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits", description = "API de gestion des produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    @Operation(summary = "Obtenir tous les produits")
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un produit par son ID")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau produit")
    public Produit createProduit(@Valid @RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un produit")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id,
                                                 @Valid @RequestBody Produit produitDetails) {
        Produit updatedProduit = produitService.updateProduit(id, produitDetails);
        return updatedProduit != null ? ResponseEntity.ok(updatedProduit)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        boolean deleted = produitService.deleteProduit(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }



    // Ajouter ces endpoints

    @GetMapping("/{id}/with-timeout")
    @Operation(summary = "Obtenir un produit avec simulation de timeout")
    public CompletableFuture<Produit> getProduitWithTimeout(@PathVariable Long id) {
        return produitService.getProduitWithTimeout(id);
    }

    @GetMapping("/{id}/with-error")
    @Operation(summary = "Obtenir un produit avec simulation d'erreur")
    public Produit getProduitWithRandomError(@PathVariable Long id) {
        return produitService.getProduitWithRandomError(id);
    }
}