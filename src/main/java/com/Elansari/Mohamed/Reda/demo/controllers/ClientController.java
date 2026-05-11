package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "API pour la gestion des clients")
public class ClientController {
    @Autowired private ClientService clientService;

    @GetMapping
    @Operation(summary = "Récupérer tous les clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/{id}/contrats")
    @Operation(summary = "Récupérer tous les contrats d'un client")
    public List<ContratDTO> getClientContrats(@PathVariable Long id) {
        return clientService.getContratsByClientId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un nouveau client", description = "Accessible uniquement par les administrateurs")
    public ClientDTO createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Mettre à jour un client existant")
    public ClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un client")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
