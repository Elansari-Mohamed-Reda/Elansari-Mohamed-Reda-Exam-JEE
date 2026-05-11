package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO;
import com.Elansari.Mohamed.Reda.demo.services.PaiementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements")
public class PaiementController {
    @Autowired private PaiementService paiementService;

    @PostMapping
    public PaiementDTO processPaiement(@RequestBody PaiementDTO dto) {
        return paiementService.savePaiement(dto);
    }

    @GetMapping("/contrat/{contratId}")
    public List<PaiementDTO> getByContrat(@PathVariable Long contratId) {
        return paiementService.getPaiementsByContrat(contratId);
    }
}
