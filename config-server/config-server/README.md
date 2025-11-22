# Spring Cloud Config Server

## Technologies Utilisées

- **Spring Boot 3+**
- **Spring Cloud Config Server**
- **Maven**
- **Java 17+**

##  Comment le Lancer ?

**Lancement :**
```bash
mvn spring-boot:run

Vérification
Après le démarrage, vous pouvez vérifier que le serveur lit les configurations de votre dépôt Git en accédant à :

Configuration Commandes : http://localhost:8889/microservice-commandes/default
<img width="1269" height="901" alt="Config Commandes" src="[../screenshots/config-commandes.png](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/configServer/config-produits.png?raw=true)" />

Configuration Produits : http://localhost:8889/microservice-produits/default
<img width="1269" height="901" alt="Config Produits" src="../screenshots/config-produits.png" />

Configuration Gateway : http://localhost:8889/spring-cloud-gateway/default
<img width="1269" height="901" alt="Config Gateway" src="../screenshots/config-gateway.png" />

