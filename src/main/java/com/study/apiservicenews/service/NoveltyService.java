package com.study.apiservicenews.service;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyFilter;

import java.util.List;

public interface NoveltyService {

    List<Novelty> findAll(NoveltyFilter filter);

    Novelty findById(Long id);

    Novelty saveNovelty(Novelty novelty);

    Novelty update(Novelty novelty);

    void deleteById(Long id);

    List<Novelty> filterByAuthorOrCategory(NoveltyFilter noveltyFilter);
}
