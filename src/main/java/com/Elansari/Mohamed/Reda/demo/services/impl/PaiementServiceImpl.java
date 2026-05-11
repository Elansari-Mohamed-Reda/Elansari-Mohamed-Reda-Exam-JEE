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
            entity.setContrat(contratRepository.findById(dto.getContratId())
                    .orElseThrow(() -> new RuntimeException("Contrat non trouvé avec l'ID: " + dto.getContratId())));
        }
        return paiementMapper.toDto(paiementRepository.save(entity));
    }

    @Override
    public PaiementDTO getPaiementById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec l'ID: " + id));
        return paiementMapper.toDto(paiement);
    }

    @Override
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        if (!contratRepository.existsById(contratId)) {
            throw new RuntimeException("Contrat non trouvé avec l'ID: " + contratId);
        }
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePaiement(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new RuntimeException("Paiement non trouvé avec l'ID: " + id);
        }
        paiementRepository.deleteById(id);
    }
}
