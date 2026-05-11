package com.Elansari.Mohamed.Reda.demo.services;

import com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO;
import java.util.List;

public interface PaiementService {
    PaiementDTO savePaiement(PaiementDTO dto);
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
}
