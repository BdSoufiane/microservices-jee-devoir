# Service Discovery (Eureka Server)

Ce service fait office de registre central pour la découverte automatique des microservices.

##  Technologies Utilisées

- **Spring Boot 3+**
- **Spring Cloud Eureka Server**
- **Maven**
- **Java 17+**

##  Comment le Lancer ?

1. **Prérequis :**
   - Aucun (c'est le service racine)

2. **Lancement :**
   ```bash
   mvn spring-boot:run 

## (Port 8761)
## Dashboard Eureka
Ouvrez votre navigateur et accédez au tableau de bord Eureka : `http://localhost:8761`.
Après le démarrage des autres microservices, vous devriez les voir s'enregistrer et apparaître dans la liste des instances disponibles.
<img width="1808" height="966" alt="image" src="../screenshots/eureka-dashboard.png" />
