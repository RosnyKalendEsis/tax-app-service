package com.province.taxappservice.model;

import jakarta.persistence.*;
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
    private LocalDate expiration;
    private boolean isExpired;
    @ManyToOne
    private Inspection inspection;

    @PrePersist
    @PreUpdate
    public void checkIfExpired() {
        if (expiration != null) {
            this.isExpired = expiration.isBefore(LocalDate.now());
        }
    }
}

