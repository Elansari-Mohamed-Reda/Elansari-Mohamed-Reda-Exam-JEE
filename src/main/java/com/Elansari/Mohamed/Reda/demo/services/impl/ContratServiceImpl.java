package com.Elansari.Mohamed.Reda.demo.services.impl;

import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratHabitationDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratSanteDTO;
import com.Elansari.Mohamed.Reda.demo.entities.ContratAssurance;
import com.Elansari.Mohamed.Reda.demo.entities.ContratAuto;
import com.Elansari.Mohamed.Reda.demo.entities.ContratHabitation;
import com.Elansari.Mohamed.Reda.demo.entities.ContratSante;
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
import java.util.List;
import java.util.stream.Collectors;

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
            entity.setClient(clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + dto.getClientId())));
        }
        entity.setStatut(Statut.EN_COURS);
        return contratMapper.toAutoDto(contratRepository.save(entity));
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        ContratHabitation entity = contratMapper.toHabitationEntity(dto);
        if (dto.getClientId() != null) {
            entity.setClient(clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + dto.getClientId())));
        }
        entity.setStatut(Statut.EN_COURS);
        return contratMapper.toHabitationDto(contratRepository.save(entity));
    }

    @Override
    public ContratSanteDTO saveContratSante(ContratSanteDTO dto) {
        ContratSante entity = contratMapper.toSanteEntity(dto);
        if (dto.getClientId() != null) {
            entity.setClient(clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + dto.getClientId())));
        }
        entity.setStatut(Statut.EN_COURS);
        return contratMapper.toSanteDto(contratRepository.save(entity));
    }

    @Override
    public ContratDTO getContratById(Long id) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé avec l'ID: " + id));
        ContratDTO dto = new ContratDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
        return dto;
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contrat -> {
                    ContratDTO dto = new ContratDTO();
                    BeanUtils.copyProperties(contrat, dto);
                    if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ContratDTO updateStatus(Long id, String status) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé avec l'ID: " + id));
        
        try {
            contrat.setStatut(Statut.valueOf(status.toUpperCase().replace("É", "E")));
        } catch (IllegalArgumentException e) {
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

    @Override
    public void deleteContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new RuntimeException("Contrat non trouvé avec l'ID: " + id);
        }
        contratRepository.deleteById(id);
    }
}
