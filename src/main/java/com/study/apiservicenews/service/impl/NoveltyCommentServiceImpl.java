package com.study.apiservicenews.service.impl;

import com.study.apiservicenews.exception.NotFoundEntityException;
import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyComment;
import com.study.apiservicenews.reposittory.NoveltyCommentRepository;
import com.study.apiservicenews.service.NoveltyCommentService;
import com.study.apiservicenews.service.NoveltyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoveltyCommentServiceImpl implements NoveltyCommentService {

    private final NoveltyCommentRepository noveltyCommentRepository;

    private final NoveltyService noveltyService;

    @Override
    public List<NoveltyComment> findAllByNoveltyId(Long id) {
        return noveltyCommentRepository.findAllByNoveltyId(id);
    }

    @Override
    public NoveltyComment findById(Long id) {
        return noveltyCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public NoveltyComment saveNoveltyComment(NoveltyComment noveltyComment) {
        Novelty noveltyFromBase = noveltyService.findById(noveltyComment.getNovelty().getId());
        noveltyComment.setNovelty(noveltyFromBase);

        return noveltyCommentRepository.save(noveltyComment);
    }

    @Override
    public NoveltyComment update(NoveltyComment noveltyComment) {
        Novelty noveltyFromBase = noveltyService.findById(noveltyComment.getNovelty().getId());
        NoveltyComment noveltyCommentFromBase = findById(noveltyComment.getId());

        noveltyCommentFromBase.setNovelty(noveltyFromBase);
        noveltyCommentFromBase.setText(noveltyComment.getText());

        return noveltyCommentRepository.save(noveltyCommentFromBase);
    }

    @Override
    public void deleteById(Long id) {
        noveltyCommentRepository.deleteById(id);
    }
}
