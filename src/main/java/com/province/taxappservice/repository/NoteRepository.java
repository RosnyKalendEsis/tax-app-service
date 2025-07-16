package com.province.taxappservice.repository;

import com.province.taxappservice.model.Inspection;
import com.province.taxappservice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    Optional<Note> findByInspection(Inspection inspection);
}
