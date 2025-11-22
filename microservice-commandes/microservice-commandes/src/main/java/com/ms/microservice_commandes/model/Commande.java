package com.ms.microservice_commandes.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "COMMANDE")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantite;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @Min(value = 0, message = "Le montant ne peut pas être négatif")
    private double montant;

    @NotNull(message = "L'ID produit est obligatoire")
    private Long idProduit;

    // Constructeurs
    public Commande() {}

    public Commande(String description, int quantite, LocalDate date, double montant, Long idProduit) {
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
        this.idProduit = idProduit;
    }

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
}