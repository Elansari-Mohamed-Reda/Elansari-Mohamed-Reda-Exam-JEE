package com.Elansari.Mohamed.Reda.demo.dtos;

import com.Elansari.Mohamed.Reda.demo.entities.TypePaiement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class PaiementDTO {
    private Long id;
    
    @NotNull(message = "La date du paiement est obligatoire")
    private LocalDate date;
    
    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double montant;
    
    @NotNull(message = "Le type de paiement est obligatoire")
    private TypePaiement type;
    
    @NotNull(message = "L'ID du contrat est obligatoire")
    private Long contratId;
}
