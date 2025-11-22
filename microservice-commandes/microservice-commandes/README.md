# Microservice Commandes

Ce microservice est responsable de la gestion des opérations CRUD (Create, Read, Update, Delete) sur les commandes. Il interagit avec le microservice-produits pour obtenir des informations sur les produits et implémente des mécanismes de résilience.

## Technologies Utilisées

- **Spring Boot 3+**
- **Spring Web** (pour les API REST)
- **Spring Data JPA** (pour la persistance des données)
- **H2 Database** (base de données en mémoire pour le développement)
- **Lombok** (pour réduire le code boilerplate)
- **Spring Cloud Config Client** (pour la configuration centralisée)
- **Spring Cloud Eureka Client** (pour l'enregistrement et la découverte)
- **Spring Boot Actuator** (pour le monitoring et le rafraîchissement à chaud)
- **Resilience4j** (pour le Circuit Breaker et la gestion des pannes)
- **Springdoc-openapi / Swagger UI** (pour la documentation API)
- **Maven**
- **Java 17+**

##  Comment le Lancer ?

- `config-server` doit être lancé (sur `http://localhost:8888`).
- `service-discovery` doit être lancé (sur `http://localhost:8761`).
- `microservice-produits` doit être lancé (sur `http://localhost:8082` ou autre).

Le microservice sera accessible sur `http://localhost:8081` (port configuré via le Config Server).

##  API Test

**Get all commandes (Postman) :**
![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/microservice_commande/getAllCommande1.PNG?raw=true)


##  Monitoring et Documentation

**Swagger UI :** `http://localhost:8081/swagger-ui.html`

![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/microservice_commande/monitoring_swager2.PNG?raw=true)

##  Démonstration Resilience4j

Pour tester le Circuit Breaker :

1. **Assurez-vous que microservice-produits a son endpoint `/delay/{seconds}` fonctionnel.**

2. **Accédez à `http://localhost:8081/actuator/circuitbreakers` pour voir l'état initial (CLOSED) :**

![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/microservice_commande/circuitBreaker3.PNG?raw=true)

3. **Envoyez plusieurs requêtes POST à `http://localhost:8081/actuator/refresh` pour recharger les propriétés Resilience4j depuis Git.**

4. **Envoyez plusieurs requêtes GET `http://localhost:8081/commandes/test-produit-delay/3` (le timeout est configuré à 2s). Vous devriez voir des réponses de fallback :**

5. **Vérifiez à nouveau `http://localhost:8081/actuator/circuitbreakers`. Le Circuit Breaker devrait passer à l'état CLOSED :**
![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/microservice_commande/circuit-breaker-closed5.PNG?raw=true)


## 🏥 Health Check Personnalisé

**Health Check UP (avec données) :**
<img width="676" height="828" alt="Health UP" src="../screenshots/health-up.png" />

**Health Check DOWN (sans données) :**
<img width="676" height="828" alt="Health DOWN" src="../screenshots/health-down.png" />

---




