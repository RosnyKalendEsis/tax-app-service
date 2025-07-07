package com.province.taxappservice.controller;

import com.province.taxappservice.model.Administrateur;
import com.province.taxappservice.model.Analyste;
import com.province.taxappservice.model.Role;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.service.AdministrateurService;
import com.province.taxappservice.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    private final AdministrateurService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Administrateur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getById(@PathVariable String id) {
        Administrateur u = service.getById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Administrateur create(@RequestBody Administrateur u) {
        u.setPwd(passwordEncoder.encode(u.getPwd()));
        return service.create(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> update(@PathVariable String id, @RequestBody Administrateur u) {
        Administrateur updated = service.update(id, u);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
