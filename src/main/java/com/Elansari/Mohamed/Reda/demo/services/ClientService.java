package com.Elansari.Mohamed.Reda.demo.services;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import java.util.List;

public interface ClientService {
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    List<ContratDTO> getContratsByClientId(Long clientId);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
}
