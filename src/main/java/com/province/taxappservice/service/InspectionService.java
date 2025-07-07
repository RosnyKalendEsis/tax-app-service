package com.province.taxappservice.service;

import com.province.taxappservice.model.Inspection;
import com.province.taxappservice.repository.InspectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionService {
    private final InspectionRepository repository;

    public InspectionService(InspectionRepository repository) {
        this.repository = repository;
    }

    public List<Inspection> getAll() {
        return repository.findAll();
    }

    public Inspection getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Inspection create(Inspection inspection) {
        return repository.save(inspection);
    }

    public Inspection update(String id, Inspection data) {
        return repository.findById(id)
                .map(i -> {
                    i.setDate(data.getDate());
                    i.setStatus(data.getStatus());
                    i.setContenu(data.getContenu());
                    i.setContribuable(data.getContribuable());
                    i.setInspecteur(data.getInspecteur());
                    i.setInspecteur_chef(data.getInspecteur_chef());
                    i.setSignature(data.getSignature());
                    return repository.save(i);
                })
                .orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
