package com.study.apiservicenews.service.impl;

import com.study.apiservicenews.exception.NotFoundEntityException;
import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.reposittory.NoveltyRepository;
import com.study.apiservicenews.reposittory.SpecificationNovelty;
import com.study.apiservicenews.service.ClientService;
import com.study.apiservicenews.service.NoveltyCategoryService;
import com.study.apiservicenews.service.NoveltyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoveltyServiceImpl implements NoveltyService {

    private final NoveltyRepository noveltyRepository;

    private final ClientService clientService;

    private final NoveltyCategoryService noveltyCategoryService;

    @Override
    public List<Novelty> filterByAuthorOrCategory(NoveltyFilter noveltyFilter) {
        return noveltyRepository.findAll(
                SpecificationNovelty.byFilter(noveltyFilter),
                PageRequest.of(
                        noveltyFilter.getPageNumber(),
                        noveltyFilter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Novelty> findAll(NoveltyFilter filter) {
        return noveltyRepository.findAll(PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize()
        )).getContent();
    }

    @Override
    public Novelty findById(Long id) {
        return noveltyRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public Novelty saveNovelty(Novelty novelty) {
        Client clientFromBase = clientService.findById(novelty.getClient().getId());
        novelty.setClient(clientFromBase);


        return noveltyRepository.save(novelty);
    }

    @Override
    public Novelty update(Novelty novelty) {
        Client clientFromBase = clientService.findById(novelty.getClient().getId());
        NoveltyCategory categoryFromBase = noveltyCategoryService.findByName(novelty.getCategory().getName());
        Novelty noveltyFromBase = findById(novelty.getId());

        noveltyFromBase.setClient(clientFromBase);
        noveltyFromBase.setTitle(novelty.getTitle());
        noveltyFromBase.setCategory(novelty.getCategory());
        noveltyFromBase.setComments(novelty.getComments());

        return noveltyRepository.save(noveltyFromBase);
    }

    @Override
    public void deleteById(Long id) {
        noveltyRepository.deleteById(id);
    }
}
