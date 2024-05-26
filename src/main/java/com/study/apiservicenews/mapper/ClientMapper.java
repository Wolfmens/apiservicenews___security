package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.Role;
import com.study.apiservicenews.web.model.client.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NoveltyMapper.class})
public interface ClientMapper {

    @Mapping(target = "roles", expression = "java(request.getRoles().stream().map(Role::from).toList())")
    Client requestToClient(IncomingClientRequest request);

    @Mapping(source = "clientId", target = "id")
    Client requestToClient(Long clientId, IncomingClientRequest request);

    ClientResponse clientToResponse(Client client);

    default ClientListResponse clientListToClientListResponse(List<Client> clients) {
        ClientListResponse clientListResponse = new ClientListResponse();
        clientListResponse.setClients(clients.stream()
                .map(this::clientToResponse)
                .toList());

        return clientListResponse;
    }

    ClientWithoutCommentResponse clientToClientWithoutCommentResponse(Client client);

    default ClientWithoutCommentList clientListToClientWithoutCommentList(List<Client> clients) {
        ClientWithoutCommentList clientWithoutCommentList = new ClientWithoutCommentList();
        clientWithoutCommentList.setClients(clients.stream()
                .map(this::clientToClientWithoutCommentResponse)
                .toList());

        return clientWithoutCommentList;
    }

}
