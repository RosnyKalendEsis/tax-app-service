package com.province.taxappservice.service;

import com.province.taxappservice.model.Inspecteur;
import com.province.taxappservice.repository.InspecteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspecteurService {

    private final InspecteurRepository repository;

    public InspecteurService(InspecteurRepository repository) {
        this.repository = repository;
    }

    public List<Inspecteur> getAll() {
        return repository.findAll();
    }

    public Inspecteur getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Inspecteur create(Inspecteur i) {
        return repository.save(i);
    }


    public Inspecteur update(String id, Inspecteur i) {
        return repository.findById(id).map(existing -> {
            existing.setNom(i.getNom());
            existing.setLogin(i.getLogin());
            existing.setPwd(i.getPwd());
            existing.setMatricule(i.getMatricule());
            existing.setZone(i.getZone());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}

