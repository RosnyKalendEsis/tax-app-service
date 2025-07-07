package com.province.taxappservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Contribuable extends Utilisateur {

    private String mail;
    private String tel;
    private String adresse;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Superficie superficie;

    @Column(name = "numero_fiscal", unique = true, nullable = false, length = 20)
    private String numero_fiscal;

    public Contribuable() {
        this.role = Role.CONTRIBUABLE;
        this.numero_fiscal = genererNumeroFiscal();
    }

    private String genererNumeroFiscal() {
        Random random = new Random();

        // Générer 9 chiffres
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            digits.append(random.nextInt(10));
        }

        // Lettre aléatoire
        char lettre = (char) ('A' + random.nextInt(26));

        return "CD-" + digits + "-" + lettre;
    }
}


