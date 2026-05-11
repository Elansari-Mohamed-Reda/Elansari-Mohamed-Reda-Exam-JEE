package com.Elansari.Mohamed.Reda.demo.services;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import java.util.List;

public interface ClientService {
    ClientDTO getClientById(Long id);
    List<ContratDTO> getContratsByClientId(Long clientId);
    ClientDTO saveClient(ClientDTO clientDTO);
}
