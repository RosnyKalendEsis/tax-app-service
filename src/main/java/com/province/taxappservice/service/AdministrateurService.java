package com.province.taxappservice.service;

import com.province.taxappservice.model.Administrateur;
import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.repository.AdministrateurRepository;
import com.province.taxappservice.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdministrateurService {

    private final AdministrateurRepository repository;

    public List<Administrateur> getAll() {
        return repository.findAll();
    }

    public Administrateur getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Administrateur create(Administrateur administrateur) {
        return repository.save(administrateur);
    }

    public Administrateur update(String id, Administrateur u) {
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
