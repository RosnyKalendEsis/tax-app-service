package com.province.taxappservice.service;

import com.province.taxappservice.model.Paiement;
import com.province.taxappservice.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    private final PaiementRepository repository;

    public PaiementService(PaiementRepository repository) {
        this.repository = repository;
    }

    public List<Paiement> getAll() {
        return repository.findAll();
    }

    public Paiement getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Paiement create(Paiement paiement) {
        return repository.save(paiement);
    }

    public Paiement update(String id, Paiement data) {
        return repository.findById(id)
                .map(p -> {
                    p.setNumTransaction(data.getNumTransaction());
                    p.setMontant(data.getMontant());
                    p.setDatePaiement(data.getDatePaiement());
                    p.setModePaiement(data.getModePaiement());
                    p.setNote(data.getNote());
                    p.setTransaction(data.getTransaction());
                    return repository.save(p);
                })
                .orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
