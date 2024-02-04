package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.NoveltyComment;
import com.study.apiservicenews.service.NoveltyService;
import com.study.apiservicenews.web.model.noveltycomment.IncomingNoveltyCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NoveltyCommentMapperDelegate implements NoveltyCommentMapper {

    @Autowired
    private NoveltyService noveltyService;

    @Override
    public NoveltyComment requestToNoveltyComment(IncomingNoveltyCommentRequest request) {
        NoveltyComment comment = new NoveltyComment();
        comment.setNovelty(noveltyService.findById(request.getNoveltyId()));
        comment.setText(request.getText());

        return comment;
    }

    @Override
    public NoveltyComment requestToNoveltyComment(Long noveltyCommentId, IncomingNoveltyCommentRequest request) {
        NoveltyComment comment = requestToNoveltyComment(request);
        comment.setId(noveltyCommentId);

        return comment;
    }

}
