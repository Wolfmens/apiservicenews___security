package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.aop.CheckClientId;
import com.study.apiservicenews.mapper.NoveltyCommentMapper;
import com.study.apiservicenews.model.NoveltyComment;
import com.study.apiservicenews.service.NoveltyCommentService;
import com.study.apiservicenews.web.model.noveltycomment.IncomingNoveltyCommentRequest;
import com.study.apiservicenews.web.model.noveltycomment.NoveltyCommentListResponse;
import com.study.apiservicenews.web.model.noveltycomment.NoveltyCommentResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news/novcom")
public class NoveltyCommentController {

    private final NoveltyCommentService noveltyCommentService;

    private final NoveltyCommentMapper noveltyCommentMapper;

    @GetMapping
    public ResponseEntity<NoveltyCommentListResponse> findAllByNoveltyId(@NotNull(message = "Novelty id {value.notblank}")
                                                                         @RequestParam Long noveltyId) {
        return ResponseEntity.ok().body(noveltyCommentMapper.
                noveltyCommentListToNoveltyCommentListResponse(noveltyCommentService.findAllByNoveltyId(noveltyId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoveltyCommentResponse> findById(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id) {
        return ResponseEntity.ok().body(noveltyCommentMapper
                .noveltyCommentToNoveltyCommentResponse(noveltyCommentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NoveltyCommentResponse> create(@RequestBody @Valid IncomingNoveltyCommentRequest request) {
        NoveltyComment comment = noveltyCommentService.saveNoveltyComment(noveltyCommentMapper.requestToNoveltyComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(noveltyCommentMapper.noveltyCommentToNoveltyCommentResponse(comment));
    }

    @PutMapping("/{id}/{clientId}")
    @CheckClientId
    public ResponseEntity<NoveltyCommentResponse> update(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id,
                                                         @RequestBody @Valid IncomingNoveltyCommentRequest request) {
        NoveltyComment updatedComment = noveltyCommentService.update(noveltyCommentMapper.requestToNoveltyComment(id, request));

        return ResponseEntity.ok().body(noveltyCommentMapper.noveltyCommentToNoveltyCommentResponse(updatedComment));
    }

    @DeleteMapping("/{id}/{clientId}")
    @CheckClientId
    public ResponseEntity<Void> delete(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id) {
        noveltyCommentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
