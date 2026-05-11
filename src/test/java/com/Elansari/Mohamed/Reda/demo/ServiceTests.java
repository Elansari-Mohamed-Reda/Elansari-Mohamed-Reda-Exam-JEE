package com.Elansari.Mohamed.Reda.demo;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratAutoDTO;
import com.Elansari.Mohamed.Reda.demo.dtos.ContratDTO;
import com.Elansari.Mohamed.Reda.demo.entities.Statut;
import com.Elansari.Mohamed.Reda.demo.services.ClientService;
import com.Elansari.Mohamed.Reda.demo.services.ContratService;
import com.Elansari.Mohamed.Reda.demo.services.PaiementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServiceTests {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContratService contratService;

    @Autowired
    private PaiementService paiementService;

    @Test
    public void testCreateClientAndContrat() {
        // Create Client
        ClientDTO clientDTO = new ClientDTO(null, "John Doe", "john@example.com");
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        assertNotNull(savedClient.getId());
        assertEquals("John Doe", savedClient.getNom());

        // Create Contrat Auto
        ContratAutoDTO autoDTO = new ContratAutoDTO();
        autoDTO.setClientId(savedClient.getId());
        autoDTO.setNumeroImmatriculation("123-ABC");
        autoDTO.setMarque("Toyota");
        autoDTO.setStatut(Statut.EN_COURS);
        
        ContratAutoDTO savedAuto = contratService.saveContratAuto(autoDTO);
        assertNotNull(savedAuto.getId());
        assertEquals(savedClient.getId(), savedAuto.getClientId());

        // Get Client Contrats
        List<ContratDTO> contrats = clientService.getContratsByClientId(savedClient.getId());
        assertEquals(1, contrats.size());

        // Validate Contrat
        ContratDTO validated = contratService.updateStatus(savedAuto.getId(), "Validé");
        assertEquals(Statut.VALIDE, validated.getStatut());
        assertNotNull(validated.getDateValidation());

        // Create Paiement
        com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO paiementDTO = new com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO();
        paiementDTO.setContratId(validated.getId());
        paiementDTO.setMontant(100.0);
        paiementDTO.setDate(java.time.LocalDate.now());
        paiementDTO.setType(com.Elansari.Mohamed.Reda.demo.entities.TypePaiement.MENSUALITE);

        com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO savedPaiement = paiementService.savePaiement(paiementDTO);
        assertNotNull(savedPaiement.getId());
        assertEquals(validated.getId(), savedPaiement.getContratId());

        // Get Paiements by Contrat
        List<com.Elansari.Mohamed.Reda.demo.dtos.PaiementDTO> paiements = paiementService.getPaiementsByContrat(validated.getId());
        assertEquals(1, paiements.size());
    }
}
