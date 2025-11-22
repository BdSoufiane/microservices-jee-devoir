# Spring Cloud API Gateway

## Technologies Utilisées

- **Spring Boot 3+**
- **Spring Cloud Gateway**
- **Spring Cloud Eureka Client**
- **Spring Cloud Config Client**
- **Spring Boot Actuator**
- **Maven**
- **Java 17+**

## ⚙️ Comment le Lancer ?

**Lancement :**
```bash
mvn spring-boot:run 

La Gateway sera accessible sur http://localhost:8080 (port configuré via le Config Server).

##Vérification
Accédez au tableau de bord Eureka (http://localhost:8761) pour confirmer que la Gateway est enregistrée :
<img width="1807" height="965" alt="Eureka Dashboard" src="../screenshots/eureka-dashboard.png" />

Testez l'accès aux microservices via la Gateway :

Route Commandes : http://localhost:8080/commandes
<img width="1284" height="910" alt="Gateway Commandes" src="" />

Route Produits : http://localhost:8080/produits
<img width="1269" height="901" alt="Gateway Produits" src="" />

Vérifiez le load balancing en lançant plusieurs instances de microservice-produits et en observant les logs lors des appels via la Gateway à http://localhost:8080/produits :
<img width="1733" height="539" alt="Load Balancing Logs" src="" />

## Monitoring
Actuator Endpoints : http://localhost:8080/actuator (inclut health, info)
<img width="678" height="597" alt="Gateway Actuator" src="" />
