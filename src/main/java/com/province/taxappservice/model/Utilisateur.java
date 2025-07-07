package com.province.taxappservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {

    @Id
    protected String id = UUID.randomUUID().toString();

    protected String nom;
    protected String login;
    protected String pwd;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    protected Role role;
}

