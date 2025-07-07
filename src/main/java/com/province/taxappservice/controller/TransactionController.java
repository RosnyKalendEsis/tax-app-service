package com.province.taxappservice.controller;

import com.province.taxappservice.model.Transaction;
import com.province.taxappservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable String id) {
        Transaction t = service.getById(id);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction t) {
        return service.create(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable String id, @RequestBody Transaction t) {
        Transaction updated = service.update(id, t);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Transaction> valider(@PathVariable String id) {
        Transaction t = service.validerTransaction(id);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }
}
