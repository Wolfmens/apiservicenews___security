package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<Client> findByName(String name);

}
