 
# Microservice Produits

Ce microservice est responsable de la gestion des opérations CRUD sur les produits. Il inclut un endpoint de simulation de délai pour tester la résilience côté client (microservice-commandes).

## 🚀 Technologies Utilisées

- **Spring Boot 3+**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Spring Cloud Config Client**
- **Spring Cloud Eureka Client**
- **Spring Boot Actuator**
- **Springdoc-openapi / Swagger UI**
- **Maven**
- **Java 17+**

## ⚙️ Comment le Lancer ?

**Prérequis :**
- `config-server` doit être lancé (sur `http://localhost:8888`).
- `service-discovery` doit être lancé (sur `http://localhost:8761`).

**Lancement :**

Pour la première instance :
```bash
mvn spring-boot:run

(Port 8082, configuré via le Config Server)

Pour une deuxième instance (pour tester le load balancing) :

mvn spring-boot:run -Dspring-boot.run.profiles=instance2

Monitoring et Documentation


Swagger UI : http://localhost:8082/swagger-ui.html
<img width="1821" height="1016" alt="Swagger Produits Instance 1" src="../screenshots/swagger-produits-1.png" />

Swagger UI (instance 2) : http://localhost:8083/swagger-ui.html
<img width="1827" height="1016" alt="Swagger Produits Instance 2" src="../screenshots/swagger-produits-2.png" />

🔄 Démonstration du Load Balancing (via les logs des consoles)
Logs montrant la distribution des requêtes entre les instances :
<img width="1733" height="539" alt="Load Balancing Logs" src="../screenshots/loadbalancing-logs.png" />