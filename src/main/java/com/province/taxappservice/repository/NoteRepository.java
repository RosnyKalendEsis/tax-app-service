package com.province.taxappservice.repository;

import com.province.taxappservice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
}
