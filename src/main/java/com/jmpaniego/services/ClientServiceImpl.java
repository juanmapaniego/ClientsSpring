package com.jmpaniego.services;

import com.jmpaniego.entities.Client;
import com.jmpaniego.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Client client) throws Exception {
        return clientRepository.findById(client.getClientId()).orElseThrow(Exception::new);
    }

    @Override
    @Transactional
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client modify(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public boolean delete(Client client) {
        try {
            clientRepository.delete(client);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
