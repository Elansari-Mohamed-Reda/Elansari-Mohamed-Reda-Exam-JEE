package com.Elansari.Mohamed.Reda.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @EqualsAndHashCode(callSuper = true)
@NoArgsConstructor @AllArgsConstructor
public class ContratAutoDTO extends ContratAssuranceDTO {
    @NotBlank(message = "Le numéro d'immatriculation est obligatoire")
    private String numeroImmatriculation;
    
    @NotBlank(message = "La marque est obligatoire")
    private String marque;
    
    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;
}
