package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.aop.annotation.CheckRole;
import com.study.apiservicenews.mapper.ClientMapper;
import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.service.ClientService;
import com.study.apiservicenews.web.model.client.ClientResponse;
import com.study.apiservicenews.web.model.client.ClientWithoutCommentList;
import com.study.apiservicenews.web.model.client.IncomingClientRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news/client")
public class ClientController {

    private final ClientMapper clientMapper;

    private final ClientService clientService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientWithoutCommentList> findAll(@Valid NoveltyFilter filter) {
        return ResponseEntity.ok().body(clientMapper.clientListToClientWithoutCommentList(clientService.findAll(filter)));
    }

    @GetMapping("/{id}")
    @CheckRole
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<ClientResponse> findById(@PathVariable
                                                   @NotNull(message = "Client ID {value.notblank}") Long id) {
        return ResponseEntity.ok().body(clientMapper.clientToResponse(clientService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid IncomingClientRequest request) {
        Client client = clientService.saveClient(clientMapper.requestToClient(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.clientToResponse(client));
    }

    @PutMapping("/{id}")
    @CheckRole
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<ClientResponse> update(@PathVariable
                                                 @NotNull(message = "Client ID {value.notblank}") Long id,
                                                 @RequestBody @Valid IncomingClientRequest request) {
        Client newClient = clientService.update(clientMapper.requestToClient(id, request));

        return ResponseEntity.ok().body(clientMapper.clientToResponse(newClient));
    }

    @DeleteMapping("/{id}")
    @CheckRole
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable
                                       @NotNull(message = "Client ID {value.notblank}") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
