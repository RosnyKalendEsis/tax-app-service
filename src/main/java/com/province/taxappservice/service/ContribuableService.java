package com.province.taxappservice.service;

import com.province.taxappservice.model.Contribuable;
import com.province.taxappservice.repository.ContribuableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContribuableService {

    private final ContribuableRepository repository;

    public ContribuableService(ContribuableRepository repository) {
        this.repository = repository;
    }

    public List<Contribuable> getAll() {
        return repository.findAll();
    }

    public Contribuable getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Contribuable create(Contribuable c) {
        return repository.save(c);
    }

    public Contribuable update(String id, Contribuable c) {
        return repository.findById(id).map(existing -> {
            existing.setNom(c.getNom());
            existing.setLogin(c.getLogin());
            existing.setPwd(c.getPwd());
            existing.setMail(c.getMail());
            existing.setTel(c.getTel());
            existing.setAdresse(c.getAdresse());
            existing.setSuperficie(c.getSuperficie());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}

