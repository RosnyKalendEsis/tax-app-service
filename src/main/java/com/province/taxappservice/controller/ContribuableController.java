package com.province.taxappservice.controller;

import com.province.taxappservice.model.Contribuable;
import com.province.taxappservice.service.ContribuableService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contribuables")
public class ContribuableController {

    private final ContribuableService service;
    private final PasswordEncoder passwordEncoder;

    public ContribuableController(ContribuableService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Contribuable> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contribuable> getById(@PathVariable String id) {
        Contribuable c = service.getById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Contribuable create(@RequestBody Contribuable c) {
        c.setPwd(passwordEncoder.encode(c.getPwd()));
        return service.create(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contribuable> update(@PathVariable String id, @RequestBody Contribuable c) {
        Contribuable updated = service.update(id, c);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
