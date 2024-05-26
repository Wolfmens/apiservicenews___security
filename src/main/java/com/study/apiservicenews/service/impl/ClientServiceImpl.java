package com.study.apiservicenews.service.impl;

import com.study.apiservicenews.exception.NotFoundEntityException;
import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.model.Role;
import com.study.apiservicenews.reposittory.ClientRepository;
import com.study.apiservicenews.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(
                        MessageFormat.format("Entity by name {0} not found", name)));
    }

    @Override
    public List<Client> findAll(NoveltyFilter filter) {
        return clientRepository.findAll(PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize()
        )).getContent();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundEntityException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public Client saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.getRoles().forEach(role -> role.setClient(client));

        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client clientFromBase = findById(client.getId());
        clientFromBase.setName(client.getName());
        clientFromBase.setNovelties(client.getNovelties());

        return clientRepository.save(clientFromBase);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
