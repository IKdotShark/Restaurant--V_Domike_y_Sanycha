package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found client with such " + id));
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) throw new ResourceNotFoundException("Not found client with such " + id);
        clientRepository.deleteById(id);
    }

    public Client findClientByBonusCardId(Long id) {
        return clientRepository.findByBonusCardId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found client by such card id" + id));
    }
}
