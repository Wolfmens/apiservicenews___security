package com.study.apiservicenews.service.impl;

import com.study.apiservicenews.exception.NotFoundEntityException;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.reposittory.NoveltyCategoryRepository;
import com.study.apiservicenews.service.NoveltyCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoveltyCategoryServiceImpl implements NoveltyCategoryService {

    private final NoveltyCategoryRepository noveltyCategoryRepository;

    @Override
    public List<NoveltyCategory> findAll(NoveltyFilter filter) {
        return noveltyCategoryRepository.findAll(PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize()
        )).getContent();
    }

    @Override
    public NoveltyCategory findById(Long id) {
        return noveltyCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public NoveltyCategory saveNoveltyCategory(NoveltyCategory noveltyCategory) {
        return noveltyCategoryRepository.save(noveltyCategory);
    }

    @Override
    public NoveltyCategory update(NoveltyCategory noveltyCategory) {
        NoveltyCategory noveltyCategoryFromBase = findById(noveltyCategory.getId());
        noveltyCategoryFromBase.setName(noveltyCategory.getName());

        return noveltyCategoryRepository.save(noveltyCategoryFromBase);
    }

    @Override
    public void deleteById(Long id) {
        noveltyCategoryRepository.deleteById(id);
    }

    @Override
    public NoveltyCategory findByName(String name) {
        return noveltyCategoryRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(MessageFormat.format("Category by name {0} not found", name)));
    }
}
