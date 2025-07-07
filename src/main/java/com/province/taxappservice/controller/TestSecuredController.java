package com.province.taxappservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecuredController {

    @GetMapping("/api/secure/hello")
    public String securedHello() {
        return "✅ Accès autorisé ! Tu es bien authentifié via JWT.";
    }
}
