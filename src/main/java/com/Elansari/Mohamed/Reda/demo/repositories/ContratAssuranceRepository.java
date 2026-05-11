package com.Elansari.Mohamed.Reda.demo.repositories;

import com.Elansari.Mohamed.Reda.demo.entities.ContratAssurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByClientId(Long clientId);
}