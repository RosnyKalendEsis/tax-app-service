package com.province.taxappservice.controller;

import com.province.taxappservice.model.Administrateur;
import com.province.taxappservice.model.Analyste;
import com.province.taxappservice.model.Role;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Utilisateur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable String id) {
        Utilisateur u = service.getById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur u) {
        if(u.getRole().equals(Role.ADMIN)){
            Administrateur  administrateur = new Administrateur();
            administrateur.setNom(u.getNom());
            administrateur.setLogin(u.getLogin());
            administrateur.setRole(u.getRole());
            administrateur.setPwd(u.getPwd());
            return service.create(administrateur);
        }else{
            Analyste analyste = new Analyste();
            analyste.setNom(u.getNom());
            analyste.setLogin(u.getLogin());
            analyste.setRole(u.getRole());
            analyste.setPwd(u.getPwd());
            return service.create(analyste);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable String id, @RequestBody Utilisateur u) {
        Utilisateur updated = service.update(id, u);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
