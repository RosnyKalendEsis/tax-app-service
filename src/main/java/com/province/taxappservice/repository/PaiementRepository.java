package com.province.taxappservice.repository;

import com.province.taxappservice.model.Note;
import com.province.taxappservice.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, String> {
    Optional<Paiement> findByNote(Note note);
}
