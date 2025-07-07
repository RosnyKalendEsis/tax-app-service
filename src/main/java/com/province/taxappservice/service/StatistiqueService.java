package com.province.taxappservice.service;

import com.province.taxappservice.model.Inspecteur;
import com.province.taxappservice.model.Statistique;
import com.province.taxappservice.repository.StatistiqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatistiqueService {
    private final StatistiqueRepository statistiqueRepository;

    public List<Statistique> getAll() {
        return statistiqueRepository.findAll();
    }

    public Statistique getById(String id) {
        return statistiqueRepository.findById(id).orElse(null);
    }

    public Statistique create(Statistique i) {
        return statistiqueRepository.save(i);
    }
}
