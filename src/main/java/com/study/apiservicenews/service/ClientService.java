package com.study.apiservicenews.service;

import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.NoveltyFilter;

import java.util.List;

public interface ClientService {

    Client findByName(String name);

    List<Client> findAll(NoveltyFilter filter);

    Client findById(Long id);

    Client saveClient(Client client);

    Client update(Client client);

    void deleteById(Long id);

}
