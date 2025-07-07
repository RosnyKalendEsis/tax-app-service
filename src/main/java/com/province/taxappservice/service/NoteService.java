package com.province.taxappservice.service;

import com.province.taxappservice.model.Note;
import com.province.taxappservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getAll() {
        return repository.findAll();
    }

    public Note getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Note create(Note note) {
        return repository.save(note);
    }

    public Note update(String id, Note data) {
        return repository.findById(id)
                .map(n -> {
                    n.setMontant(data.getMontant());
                    n.setDate(data.getDate());
                    n.setPeriode(data.getPeriode());
                    n.setInspection(data.getInspection());
                    return repository.save(n);
                })
                .orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Note validerNote(String id) {
        return repository.findById(id).map(n -> {
            n.setValide(true);
            return repository.save(n);
        }).orElse(null);
    }

}
