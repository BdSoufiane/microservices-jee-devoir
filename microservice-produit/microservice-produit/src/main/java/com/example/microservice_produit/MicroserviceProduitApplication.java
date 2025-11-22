package com.example.microservice_produit;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceProduitApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProduitApplication.class, args);
	}
}