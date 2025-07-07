package com.province.taxappservice.model;

import jakarta.persistence.Entity;
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
public class Paiement {
    @Id
    private String idPaiement = UUID.randomUUID().toString();

    private String numTransaction;
    private double montant;
    private LocalDate datePaiement;
    private String modePaiement;

    @ManyToOne
    private Note note;

    @ManyToOne
    private Transaction transaction;
}
