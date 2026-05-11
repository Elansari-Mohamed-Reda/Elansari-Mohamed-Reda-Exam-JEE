package com.Elansari.Mohamed.Reda.demo.services;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;

public interface ContratService {
    ContratAutoDTO saveContratAuto(ContratAutoDTO dto);
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    ContratDTO updateStatus(Long id, String status);
}
