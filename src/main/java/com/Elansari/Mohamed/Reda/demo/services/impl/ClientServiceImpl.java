package com.Elansari.Mohamed.Reda.demo.services.impl;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.entities.Client;
import com.Elansari.Mohamed.Reda.demo.mappers.ClientMapper;
import com.Elansari.Mohamed.Reda.demo.mappers.ContratMapper;
import com.Elansari.Mohamed.Reda.demo.repositories.ClientRepository;
import com.Elansari.Mohamed.Reda.demo.repositories.ContratAssuranceRepository;
import com.Elansari.Mohamed.Reda.demo.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired private ClientRepository clientRepository;
    @Autowired private ContratAssuranceRepository contratRepository;
    @Autowired private ClientMapper clientMapper;
    @Autowired private ContratMapper contratMapper;

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + id));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getContratsByClientId(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + clientId);
        }
        return contratRepository.findByClientId(clientId).stream()
                .map(contrat -> {
                    ContratDTO dto = new ContratDTO();
                    BeanUtils.copyProperties(contrat, dto);
                    if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + id));
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + id);
        }
        clientRepository.deleteById(id);
    }
}
