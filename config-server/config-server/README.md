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
```


Vérification
Après le démarrage, vous pouvez vérifier que le serveur lit les configurations de votre dépôt Git en accédant à :


Configuration Commandes : http://localhost:8889/microservice-commandes/default
![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/configServer/config-commandes.PNG?raw=true)


Configuration Produits : http://localhost:8889/microservice-produits/default

  ![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/configServer/config-produits.png?raw=true)

Configuration Gateway : http://localhost:8889/spring-cloud-gateway/default
![image alt](https://github.com/BdSoufiane/microservices-jee-devoir/blob/main/documentation/screenshots/configServer/config-gateway.PNG?raw=true)



