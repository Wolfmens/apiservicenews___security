package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.service.ClientService;
import com.study.apiservicenews.service.NoveltyCategoryService;
import com.study.apiservicenews.web.model.novelty.IncomingNoveltyRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class NoveltyMapperDelegate implements NoveltyMapper {

    @Autowired
    private ClientService clientService;

    @Autowired
    private NoveltyCategoryService noveltyCategoryService;

    @Override
    public Novelty requestToNovelty(IncomingNoveltyRequest request, UserDetails userDetails) {
        Novelty novelty = new Novelty();
        novelty.setTitle(request.getTitle());
        novelty.setClient(clientService.findByName(userDetails.getUsername()));
        novelty.setCategory(noveltyCategoryService.findByName(request.getCategory().getName()));
        return novelty;
    }

    @Override
    public Novelty requestToNovelty(Long noveltyId, IncomingNoveltyRequest request, UserDetails userDetails) {
        Novelty novelty = requestToNovelty(request, userDetails);
        novelty.setId(noveltyId);

        return novelty;
    }

}
