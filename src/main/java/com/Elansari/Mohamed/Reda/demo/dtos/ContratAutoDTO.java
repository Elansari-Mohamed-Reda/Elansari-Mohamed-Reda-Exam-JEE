package com.Elansari.Mohamed.Reda.demo.dtos;

import lombok.*;

@Data @EqualsAndHashCode(callSuper = true)
@NoArgsConstructor @AllArgsConstructor
public class ContratAutoDTO extends ContratAssuranceDTO {
    private String numeroImmatriculation;
    private String marque;
    private String modele;
}