package com.province.taxappservice.controller;

import com.province.taxappservice.model.Administrateur;
import com.province.taxappservice.model.Analyste;
import com.province.taxappservice.model.Role;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.service.AnalysteService;
import com.province.taxappservice.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/analystes")
public class AnalysteController {
    private final AnalysteService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Analyste> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyste> getById(@PathVariable String id) {
        Analyste u = service.getById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Analyste create(@RequestBody Analyste u) {
        u.setPwd(passwordEncoder.encode(u.getPwd()));
        return service.create(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyste> update(@PathVariable String id, @RequestBody Analyste u) {
        Analyste updated = service.update(id, u);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
