package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.Novelty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoveltyRepository extends JpaRepository<Novelty,Long>, JpaSpecificationExecutor<Novelty> {


}
