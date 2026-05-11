package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO;
import com.Elansari.Mohamed.Reda.demo.services.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements", description = "API pour la gestion des paiements")
public class PaiementController {
    @Autowired private PaiementService paiementService;

    @GetMapping
    @Operation(summary = "Récupérer tous les paiements")
    public List<PaiementDTO> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un paiement par son ID")
    public PaiementDTO getPaiement(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Traiter un nouveau paiement")
    public PaiementDTO processPaiement(@Valid @RequestBody PaiementDTO dto) {
        return paiementService.savePaiement(dto);
    }

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Récupérer tous les paiements d'un contrat")
    public List<PaiementDTO> getByContrat(@PathVariable Long contratId) {
        return paiementService.getPaiementsByContrat(contratId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un paiement")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}
