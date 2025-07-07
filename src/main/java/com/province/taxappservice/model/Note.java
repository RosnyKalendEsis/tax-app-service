package com.province.taxappservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    private String idnote = UUID.randomUUID().toString();

    private double montant;
    private LocalDate date;
    private String periode;
    private boolean valide;
    private String signature;
    @ManyToOne
    private Inspection inspection;
}

