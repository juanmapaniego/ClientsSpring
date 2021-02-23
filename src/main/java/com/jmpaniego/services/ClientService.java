package com.jmpaniego.services;

import com.jmpaniego.entities.Client;

import java.util.List;

public interface ClientService {
    public List<Client> findAll();

    public Client findById(Client client) throws Exception;

    public Client add(Client client);

    public Client modify(Client client);

    public boolean delete(Client client);
}
