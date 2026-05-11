package com.Elansari.Mohamed.Reda.demo.mappers;

import com.Elansari.Mohamed.Reda.demo.dtos.*;
import com.Elansari.Mohamed.Reda.demo.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {

    public ContratAutoDTO toAutoDto(ContratAuto entity) {
        if (entity == null) return null;
        ContratAutoDTO dto = new ContratAutoDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getClient() != null) dto.setClientId(entity.getClient().getId());
        return dto;
    }

    public ContratAuto toAutoEntity(ContratAutoDTO dto) {
        if (dto == null) return null;
        ContratAuto entity = new ContratAuto();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public ContratHabitationDTO toHabitationDto(ContratHabitation entity) {
        if (entity == null) return null;
        ContratHabitationDTO dto = new ContratHabitationDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getClient() != null) dto.setClientId(entity.getClient().getId());
        return dto;
    }

    public ContratHabitation toHabitationEntity(ContratHabitationDTO dto) {
        if (dto == null) return null;
        ContratHabitation entity = new ContratHabitation();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public ContratSanteDTO toSanteDto(ContratSante entity) {
        if (entity == null) return null;
        ContratSanteDTO dto = new ContratSanteDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getClient() != null) dto.setClientId(entity.getClient().getId());
        return dto;
    }

    public ContratSante toSanteEntity(ContratSanteDTO dto) {
        if (dto == null) return null;
        ContratSante entity = new ContratSante();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}