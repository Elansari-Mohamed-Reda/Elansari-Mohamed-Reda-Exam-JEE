package com.Elansari.Mohamed.Reda.demo.repositories;

import com.Elansari.Mohamed.Reda.demo.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
}