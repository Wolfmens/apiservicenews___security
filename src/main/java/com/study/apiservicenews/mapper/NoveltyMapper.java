package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.web.model.novelty.*;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@DecoratedWith(NoveltyMapperDelegate.class)
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NoveltyCategoryMapper.class,
                NoveltyCommentMapper.class})
public interface NoveltyMapper {

    Novelty requestToNovelty(IncomingNoveltyRequest request, UserDetails userDetails);

    @Mapping(source = "noveltyId", target = "id")
    Novelty requestToNovelty(Long noveltyId, IncomingNoveltyRequest request, UserDetails userDetails);

    NoveltyResponse noveltyToNoveltyResponse(Novelty novelty);

    List<NoveltyResponse> noveltyListToNoveltyResponseList(List<Novelty> novelties);

    default NoveltyListResponse noveltyListToNoveltyListResponse(List<Novelty> novelties) {
        NoveltyListResponse noveltyListResponse = new NoveltyListResponse();
        noveltyListResponse.setNovelties(noveltyListToNoveltyResponseList(novelties));

        return noveltyListResponse;
    }

    @Mapping(target = "countComments", expression = "java(novelty.getComments() != null ? novelty.getComments().size() : 0)")
    NoveltyWithoutCommentResponse noveltyToNoveltyWithoutCommentResponse(Novelty novelty);

    List<NoveltyWithoutCommentResponse> noveltyListToNoveltyWithoutCommentResponseList(List<Novelty> novelties);

    default NoveltyWithoutCommentListResponse noveltyListToNoveltyWithoutCommentListResponse(List<Novelty> novelties) {
        NoveltyWithoutCommentListResponse noveltyWithoutCommentListResponse =
                new NoveltyWithoutCommentListResponse();
        noveltyWithoutCommentListResponse.setNovelties(noveltyListToNoveltyWithoutCommentResponseList(novelties));

        return noveltyWithoutCommentListResponse;
    }






}
