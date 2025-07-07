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
public class Inspection {
    @Id
    private String idinspection = UUID.randomUUID().toString();

    private LocalDate date;
    private String status;
    private String contenu;

    private String signature;
    @ManyToOne
    private Contribuable contribuable;

    @ManyToOne
    private Inspecteur inspecteur;

    @ManyToOne
    private Inspecteur inspecteur_chef;

}

