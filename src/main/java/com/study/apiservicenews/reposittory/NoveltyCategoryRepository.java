package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.NoveltyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoveltyCategoryRepository extends JpaRepository<NoveltyCategory, Long> {

    Optional<NoveltyCategory> findByName(String name);

}
