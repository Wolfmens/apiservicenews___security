package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
