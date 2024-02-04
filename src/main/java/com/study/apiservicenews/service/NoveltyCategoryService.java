package com.study.apiservicenews.service;

import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.model.NoveltyFilter;

import java.util.List;

public interface NoveltyCategoryService {

    List<NoveltyCategory> findAll(NoveltyFilter filter);

    NoveltyCategory findById(Long id);

    NoveltyCategory saveNoveltyCategory(NoveltyCategory noveltyCategory);

    NoveltyCategory update(NoveltyCategory noveltyCategory);

    void deleteById(Long id);

    NoveltyCategory findByName(String name);

}
