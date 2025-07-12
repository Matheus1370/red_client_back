package com.redclient.red_client_back.modules.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redclient.red_client_back.exceptions.UserFoundException;
import com.redclient.red_client_back.exceptions.UserNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private  ClientRepository repository;


    public List<ClientEntity> getAll(){
        List<ClientEntity> clients = repository.findAll();
        return clients;
    }

    public ClientEntity getById(Long id) {
        ClientEntity entity = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());

       return entity;

    }

    public ClientEntity create(ClientEntity client) {
        repository.findByEmail(client.getEmail()).ifPresent(c -> {
            throw new UserFoundException();
        });

        return repository.save(client);
    }

    public ClientEntity update(Long id, ClientEntity entity) {
        ClientEntity client = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());

        if (!client.getEmail().equalsIgnoreCase(entity.getEmail())) {
            var findByEmail = repository.findByEmail(entity.getEmail());
            if (findByEmail.isPresent() && !findByEmail.get().getId().equals(id)) {
                throw new UserFoundException();
            }
        }

        client.setName(entity.getName());
        client.setEmail(entity.getEmail());
        client.setPassword(entity.getPassword());

        return repository.save(client);
    }

    public void delete(Long id){
        repository.findById(id).orElseThrow(() -> new UserNotFoundException());
        repository.deleteById(id);
    }

}
