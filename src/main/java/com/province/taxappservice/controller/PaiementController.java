package com.province.taxappservice.controller;

import com.province.taxappservice.model.Paiement;
import com.province.taxappservice.service.PaiementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @GetMapping
    public List<Paiement> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getById(@PathVariable String id) {
        Paiement p = service.getById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement p) {
        return service.create(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(@PathVariable String id, @RequestBody Paiement p) {
        Paiement updated = service.update(id, p);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byNote/{noteId}")
    public List<Paiement> getByNote(@PathVariable String noteId) {
        return service.getAll().stream()
                .filter(p -> p.getNote() != null && p.getNote().getIdnote().equals(noteId))
                .collect(Collectors.toList());
    }

    @PostMapping("/payer")
    public ResponseEntity<Paiement> payer(@RequestBody Paiement paiement) {
        Paiement saved = service.create(paiement);
        return ResponseEntity.ok(saved);
    }

}
