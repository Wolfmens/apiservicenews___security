package com.study.apiservicenews.service;

import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.model.NoveltyComment;

import java.util.List;

public interface NoveltyCommentService {

    List<NoveltyComment> findAllByNoveltyId(Long id);

    NoveltyComment findById(Long id);

    NoveltyComment saveNoveltyComment(NoveltyComment noveltyComment);

    NoveltyComment update(NoveltyComment noveltyComment);

    void deleteById(Long id);
}
