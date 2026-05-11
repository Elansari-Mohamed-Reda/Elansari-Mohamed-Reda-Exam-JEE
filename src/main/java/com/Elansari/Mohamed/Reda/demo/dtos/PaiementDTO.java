package com.Elansari.Mohamed.Reda.demo.dtos;

import com.Elansari.Mohamed.Reda.demo.entities.TypePaiement;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class PaiementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypePaiement type;
    private Long contratId;
}