package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients")
public class ClientController {
    @Autowired private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/{id}/contrats")
    public List<ContratDTO> getClientContrats(@PathVariable Long id) {
        return clientService.getContratsByClientId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }
}
