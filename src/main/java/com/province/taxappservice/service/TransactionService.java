package com.province.taxappservice.service;

import com.province.taxappservice.model.Transaction;
import com.province.taxappservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction update(String id, Transaction data) {
        return repository.findById(id)
                .map(t -> {
                    t.setMontant(data.getMontant());
                    t.setDatetransaction(data.getDatetransaction());
                    return repository.save(t);
                })
                .orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Transaction validerTransaction(String id) {
        return repository.findById(id)
                .map(t -> {
                    t.setValide(true);
                    return repository.save(t);
                })
                .orElse(null);
    }
}
