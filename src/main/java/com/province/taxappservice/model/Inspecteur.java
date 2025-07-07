package com.province.taxappservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Inspecteur extends Utilisateur {

    private String matricule;
    private String zone;

    public Inspecteur() {
        this.role = Role.INSPECTEUR;
    }
}


