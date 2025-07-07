package com.province.taxappservice.service;

import com.province.taxappservice.model.Utilisateur;
import com.province.taxappservice.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    public List<Utilisateur> getAll() {
        return repository.findAll();
    }

    public Utilisateur getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Utilisateur create(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    public Utilisateur update(String id, Utilisateur u) {
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
