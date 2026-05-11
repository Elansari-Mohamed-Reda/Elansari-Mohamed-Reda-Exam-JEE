package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;
import com.Elansari.Mohamed.Reda.demo.services.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats")
public class ContratController {
    @Autowired private ContratService contratService;

    @PostMapping("/auto")
    public ContratAutoDTO createAuto(@RequestBody ContratAutoDTO dto) {
        return contratService.saveContratAuto(dto);
    }

    @PostMapping("/habitation")
    public ContratHabitationDTO createHabitation(@RequestBody ContratHabitationDTO dto) {
        return contratService.saveContratHabitation(dto);
    }

    @PatchMapping("/{id}/valider")
    @PreAuthorize("hasRole('EMPLOYE') or hasRole('ADMIN')")
    @Operation(summary = "Valider un contrat (Change le statut à VALIDÉ)")
    public ContratDTO validerContrat(@PathVariable Long id) {
        return contratService.updateStatus(id, "Validé");
    }
}
