package com.Elansari.Mohamed.Reda.demo.dtos;

import com.Elansari.Mohamed.Reda.demo.entities.Statut;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class ContratAssuranceDTO {
    private Long id;
    
    @NotNull(message = "La date de souscription est obligatoire")
    private LocalDate dateSouscription;
    
    private Statut statut;
    
    private LocalDate dateValidation;
    
    @NotNull(message = "Le montant de la cotisation est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double montantCotisation;
    
    @NotNull(message = "La durée du contrat est obligatoire")
    @Positive(message = "La durée doit être positive")
    private Integer dureeContrat;
    
    @NotNull(message = "Le taux de couverture est obligatoire")
    @Positive(message = "Le taux de couverture doit être positif")
    private Double tauxCouverture;
    
    @NotNull(message = "L'ID du client est obligatoire")
    private Long clientId;
}
