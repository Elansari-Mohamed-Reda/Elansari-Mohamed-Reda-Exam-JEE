package com.Elansari.Mohamed.Reda.demo.dtos;

import com.Elansari.Mohamed.Reda.demo.entities.Statut;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class ContratAssuranceDTO {
    private Long id;
    private LocalDate dateSouscription;
    private Statut statut;
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer dureeContrat;
    private Double tauxCouverture;
    private Long clientId; // On ne passe que l'ID du client pour simplifier
}