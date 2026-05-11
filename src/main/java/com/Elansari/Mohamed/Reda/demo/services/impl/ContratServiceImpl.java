package com.Elansari.Mohamed.Reda.demo.services.impl;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;
import com.Elansari.Mohamed.Reda.demo.entities.ContratAssurance;
import com.Elansari.Mohamed.Reda.demo.entities.ContratAuto;
import com.Elansari.Mohamed.Reda.demo.entities.ContratHabitation;
import com.Elansari.Mohamed.Reda.demo.entities.Statut;
import com.Elansari.Mohamed.Reda.demo.mappers.ContratMapper;
import com.Elansari.Mohamed.Reda.demo.repositories.ClientRepository;
import com.Elansari.Mohamed.Reda.demo.repositories.ContratAssuranceRepository;
import com.Elansari.Mohamed.Reda.demo.services.ContratService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    @Autowired private ContratAssuranceRepository contratRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private ContratMapper contratMapper;

    @Override
    public ContratAutoDTO saveContratAuto(ContratAutoDTO dto) {
        ContratAuto entity = contratMapper.toAutoEntity(dto);
        if (dto.getClientId() != null) {
            entity.setClient(clientRepository.findById(dto.getClientId()).orElse(null));
        }
        return contratMapper.toAutoDto(contratRepository.save(entity));
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        ContratHabitation entity = contratMapper.toHabitationEntity(dto);
        if (dto.getClientId() != null) {
            entity.setClient(clientRepository.findById(dto.getClientId()).orElse(null));
        }
        return contratMapper.toHabitationDto(contratRepository.save(entity));
    }

    @Override
    public ContratDTO updateStatus(Long id, String status) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
        
        try {
            contrat.setStatut(Statut.valueOf(status.toUpperCase().replace("É", "E")));
        } catch (IllegalArgumentException e) {
            // Fallback if the enum doesn't match exactly
            if (status.equalsIgnoreCase("Validé")) {
                contrat.setStatut(Statut.VALIDE);
            } else if (status.equalsIgnoreCase("En cours")) {
                contrat.setStatut(Statut.EN_COURS);
            } else {
                contrat.setStatut(Statut.RESILIE);
            }
        }
        
        if (contrat.getStatut() == Statut.VALIDE) {
            contrat.setDateValidation(LocalDate.now());
        }
        
        ContratAssurance saved = contratRepository.save(contrat);
        ContratDTO dto = new ContratDTO();
        BeanUtils.copyProperties(saved, dto);
        if (saved.getClient() != null) dto.setClientId(saved.getClient().getId());
        return dto;
    }
}
