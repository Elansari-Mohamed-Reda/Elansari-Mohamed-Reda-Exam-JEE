package com.Elansari.Mohamed.Reda.demo.dtos;

import com.Elansari.Mohamed.Reda.demo.entities.TypeLogement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data @EqualsAndHashCode(callSuper = true)
@NoArgsConstructor @AllArgsConstructor
public class ContratHabitationDTO extends ContratAssuranceDTO {
    @NotNull(message = "Le type de logement est obligatoire")
    private TypeLogement typeLogement;
    
    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
    
    @NotNull(message = "La superficie est obligatoire")
    @Positive(message = "La superficie doit être positive")
    private Double superficie;
}
