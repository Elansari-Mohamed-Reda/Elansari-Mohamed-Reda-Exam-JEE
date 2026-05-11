package com.Elansari.Mohamed.Reda.demo.services.impl;

import com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO;
import com.Elansari.Mohamed.Reda.demo.entities.Paiement;
import com.Elansari.Mohamed.Reda.demo.mappers.PaiementMapper;
import com.Elansari.Mohamed.Reda.demo.repositories.ContratAssuranceRepository;
import com.Elansari.Mohamed.Reda.demo.repositories.PaiementRepository;
import com.Elansari.Mohamed.Reda.demo.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

    @Autowired private PaiementRepository paiementRepository;
    @Autowired private ContratAssuranceRepository contratRepository;
    @Autowired private PaiementMapper paiementMapper;

    @Override
    public PaiementDTO savePaiement(PaiementDTO dto) {
        Paiement entity = paiementMapper.toEntity(dto);
        if (dto.getContratId() != null) {
            entity.setContrat(contratRepository.findById(dto.getContratId()).orElse(null));
        }
        return paiementMapper.toDto(paiementRepository.save(entity));
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toDto)
                .collect(Collectors.toList());
    }
}
