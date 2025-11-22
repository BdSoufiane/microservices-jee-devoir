package com.ms.microservice_commandes.dto;



import java.time.LocalDate;

public class CommandeWithProduitDTO {
    private Long id;
    private String description;
    private int quantite;
    private LocalDate date;
    private double montant;
    private Long idProduit;
    private ProduitDTO produit;

    // Constructeurs
    public CommandeWithProduitDTO() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public Long getIdProduit() { return idProduit; }
    public void setIdProduit(Long idProduit) { this.idProduit = idProduit; }

    public ProduitDTO getProduit() { return produit; }
    public void setProduit(ProduitDTO produit) { this.produit = produit; }
}