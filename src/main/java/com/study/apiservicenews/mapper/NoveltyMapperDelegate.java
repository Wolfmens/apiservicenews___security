package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.service.ClientService;
import com.study.apiservicenews.service.NoveltyCategoryService;
import com.study.apiservicenews.web.model.novelty.IncomingNoveltyRequest;
import com.study.apiservicenews.web.model.novelty.NoveltyWithoutCommentResponse;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public abstract class NoveltyMapperDelegate implements NoveltyMapper {

    @Autowired
    private ClientService clientService;

    @Autowired
    private NoveltyCategoryService noveltyCategoryService;

    @Override
    public Novelty requestToNovelty(IncomingNoveltyRequest request) {
        Novelty novelty = new Novelty();
        novelty.setTitle(request.getTitle());
        novelty.setClient(clientService.findById(request.getClientId()));
        novelty.setCategory(noveltyCategoryService.findByName(request.getCategory().getName()));
        return novelty;
    }

    @Override
    public Novelty requestToNovelty(Long noveltyId, IncomingNoveltyRequest request) {
        Novelty novelty = requestToNovelty(request);
        novelty.setId(noveltyId);

        return novelty;
    }

}
