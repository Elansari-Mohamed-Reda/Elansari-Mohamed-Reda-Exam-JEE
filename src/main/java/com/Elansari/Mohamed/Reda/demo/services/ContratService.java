package com.Elansari.Mohamed.Reda.demo.services;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratSanteDTO;
import java.util.List;

public interface ContratService {
    ContratAutoDTO saveContratAuto(ContratAutoDTO dto);
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    ContratSanteDTO saveContratSante(ContratSanteDTO dto);
    ContratDTO getContratById(Long id);
    List<ContratDTO> getAllContrats();
    ContratDTO updateStatus(Long id, String status);
    void deleteContrat(Long id);
}
