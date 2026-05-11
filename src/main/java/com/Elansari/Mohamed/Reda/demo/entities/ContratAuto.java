package com.Elansari.Mohamed.Reda.demo.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ContratAuto extends ContratAssurance {
    private String numeroImmatriculation;
    private String marque;
    private String modele;
}