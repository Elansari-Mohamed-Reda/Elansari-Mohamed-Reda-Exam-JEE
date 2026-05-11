package com.Elansari.Mohamed.Reda.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 21]
    private LocalDate date; [cite: 21]
    private Double montant; [cite: 21]

    @Enumerated(EnumType.STRING)
    private TypePaiement type; [cite: 21]

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private ContratAssurance contrat; [cite: 10]
}