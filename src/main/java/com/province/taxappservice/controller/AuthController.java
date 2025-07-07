package com.province.taxappservice.controller;

import com.province.taxappservice.configs.JwtUtil;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.repository.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UtilisateurRepository utilisateurRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String login = loginRequest.get("login");
        String pwd = loginRequest.get("pwd");

        if (login == null || pwd == null) {
            return ResponseEntity.badRequest().body("Login ou mot de passe manquant");
        }

        Utilisateur utilisateur = utilisateurRepository.findAll().stream()
                .filter(u -> u.getLogin().equals(login) && passwordEncoder.matches(pwd,u.getPwd()))
                .findFirst()
                .orElse(null);

        if (utilisateur == null) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }

        String token = jwtUtil.generateToken(utilisateur.getLogin(), utilisateur.getRole().name());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "utilisateur", utilisateur
        ));
    }
}
