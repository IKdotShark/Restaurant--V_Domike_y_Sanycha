package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
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

    public Client removeBonusCard(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        client.setBonusCard(null);
        return clientRepository.save(client);
    }

    public Client updateClient(Client client, Client clientInfo) {
        if (clientInfo.getName() != null) {
            client.setName(clientInfo.getName());
        }
        if (clientInfo.getBirthday() != null) {
            client.setBirthday(clientInfo.getBirthday());
        }
        if (clientInfo.getContact() != null) {
            client.setContact(clientInfo.getContact());
        }
        if (clientInfo.getEmail() != null) {
            client.setEmail(clientInfo.getEmail());
        }
        if (clientInfo.getBonusCard() != null) {
            client.setBonusCard(clientInfo.getBonusCard());
        }
        if (clientInfo.getAdress() != null) {
            client.setAdress(clientInfo.getAdress());
        }
        return clientRepository.save(client);
    }
}