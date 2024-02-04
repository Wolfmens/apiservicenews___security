package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.NoveltyComment;
import com.study.apiservicenews.web.model.noveltycomment.IncomingNoveltyCommentRequest;
import com.study.apiservicenews.web.model.noveltycomment.NoveltyCommentListResponse;
import com.study.apiservicenews.web.model.noveltycomment.NoveltyCommentResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NoveltyCommentMapperDelegate.class)
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoveltyCommentMapper {

    NoveltyComment requestToNoveltyComment(IncomingNoveltyCommentRequest request);

    @Mapping(source = "noveltyCommentId", target = "id")
    NoveltyComment requestToNoveltyComment(Long noveltyCommentId, IncomingNoveltyCommentRequest request);

    NoveltyCommentResponse noveltyCommentToNoveltyCommentResponse(NoveltyComment comment);

    List<NoveltyCommentResponse> noveltyCommentListToNoveltyCommentResponseList(List<NoveltyComment> noveltyComments);

    default NoveltyCommentListResponse noveltyCommentListToNoveltyCommentListResponse(List<NoveltyComment> noveltyComments) {
        NoveltyCommentListResponse noveltyCommentListResponse = new NoveltyCommentListResponse();
        noveltyCommentListResponse.setComments(noveltyCommentListToNoveltyCommentResponseList(noveltyComments));

        return noveltyCommentListResponse;
    }

}
