package com.province.taxappservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Statistique {
    @Id
    private String id= UUID.randomUUID().toString();
    private LocalDate date;
    private boolean Valide;

    @ManyToMany
    List<Note>listNotes;
}
