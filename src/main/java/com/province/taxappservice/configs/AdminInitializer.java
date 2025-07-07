package com.province.taxappservice.configs;

import com.province.taxappservice.model.Administrateur;
import com.province.taxappservice.model.Role;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.repository.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdminInitializer {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        boolean adminExiste = utilisateurRepository.findAll().stream()
                .anyMatch(u -> u.getRole() == Role.ADMIN);

        if (!adminExiste) {
            Administrateur admin = new Administrateur();
            admin.setId(UUID.randomUUID().toString());
            admin.setNom("Super Admin");
            admin.setLogin("admin");
            admin.setPwd(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            utilisateurRepository.save(admin);

            System.out.println("✅ Admin par défaut créé (login: admin / pwd: admin123)");
        } else {
            System.out.println("ℹ️ Admin déjà existant, rien à faire.");
        }
    }
}
