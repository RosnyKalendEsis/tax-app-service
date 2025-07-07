package com.province.taxappservice.service;

import com.province.taxappservice.model.Analyste;
import com.province.taxappservice.repository.AnalysteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnalysteService {

    private final AnalysteRepository repository;

    public List<Analyste> getAll() {
        return repository.findAll();
    }

    public Analyste getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Analyste create(Analyste analyste) {
        return repository.save(analyste);
    }

    public Analyste update(String id, Analyste u) {
        return repository.findById(id).map(existing -> {
            existing.setNom(u.getNom());
            existing.setLogin(u.getLogin());
            existing.setPwd(u.getPwd());
            existing.setRole(u.getRole());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
