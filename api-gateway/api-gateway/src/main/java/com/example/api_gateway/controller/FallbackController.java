package com.example.api_gateway.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/commandes")
    public ResponseEntity<Map<String, String>> commandesFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Service Commandes temporairement indisponible. Veuillez réessayer plus tard.");
        response.put("status", "SERVICE_UNAVAILABLE");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/produits")
    public ResponseEntity<Map<String, String>> produitsFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Service Produits temporairement indisponible. Veuillez réessayer plus tard.");
        response.put("status", "SERVICE_UNAVAILABLE");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}