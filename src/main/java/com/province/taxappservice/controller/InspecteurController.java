package com.province.taxappservice.controller;

import com.province.taxappservice.model.Inspecteur;
import com.province.taxappservice.service.InspecteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspecteurs")
public class InspecteurController {
    private final InspecteurService service;
    private final PasswordEncoder passwordEncoder;

    public InspecteurController(InspecteurService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Inspecteur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspecteur> getById(@PathVariable String id) {
        Inspecteur i = service.getById(id);
        return i != null ? ResponseEntity.ok(i) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Inspecteur create(@RequestBody Inspecteur i) {
        i.setPwd(passwordEncoder.encode(i.getPwd()));
        return service.create(i);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inspecteur> update(@PathVariable String id, @RequestBody Inspecteur i) {
        Inspecteur updated = service.update(id, i);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

