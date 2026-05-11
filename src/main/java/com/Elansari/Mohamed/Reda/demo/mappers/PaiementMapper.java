package com.Elansari.Mohamed.Reda.demo.mappers;

import com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO;
import com.Elansari.Mohamed.Reda.demo.entities.Paiement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementDTO toDto(Paiement paiement) {
        if (paiement == null) return null;
        PaiementDTO dto = new PaiementDTO();
        BeanUtils.copyProperties(paiement, dto);
        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getId());
        }
        return dto;
    }

    public Paiement toEntity(PaiementDTO dto) {
        if (dto == null) return null;
        Paiement paiement = new Paiement();
        BeanUtils.copyProperties(dto, paiement);
        return paiement;
    }
}