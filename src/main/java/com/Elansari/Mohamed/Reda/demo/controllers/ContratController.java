package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratSanteDTO;
import com.Elansari.Mohamed.Reda.demo.services.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats", description = "API pour la gestion des contrats d'assurance")
public class ContratController {
    @Autowired private ContratService contratService;

    @GetMapping
    @Operation(summary = "Récupérer tous les contrats")
    public List<ContratDTO> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un contrat par son ID")
    public ContratDTO getContrat(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    @PostMapping("/auto")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau contrat Auto")
    public ContratAutoDTO createAuto(@Valid @RequestBody ContratAutoDTO dto) {
        return contratService.saveContratAuto(dto);
    }

    @PostMapping("/habitation")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau contrat Habitation")
    public ContratHabitationDTO createHabitation(@Valid @RequestBody ContratHabitationDTO dto) {
        return contratService.saveContratHabitation(dto);
    }

    @PostMapping("/sante")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau contrat Santé")
    public ContratSanteDTO createSante(@Valid @RequestBody ContratSanteDTO dto) {
        return contratService.saveContratSante(dto);
    }

    @PatchMapping("/{id}/valider")
    @PreAuthorize("hasRole('EMPLOYE') or hasRole('ADMIN')")
    @Operation(summary = "Valider un contrat (Change le statut à VALIDE)")
    public ContratDTO validerContrat(@PathVariable Long id) {
        return contratService.updateStatus(id, "Validé");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un contrat")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }
}
