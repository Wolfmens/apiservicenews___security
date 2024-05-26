package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.aop.annotation.CheckClientId;
import com.study.apiservicenews.aop.annotation.CheckRole;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news/novcom")
public class NoveltyCommentController {

    private final NoveltyCommentService noveltyCommentService;

    private final NoveltyCommentMapper noveltyCommentMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyCommentListResponse> findAllByNoveltyId(@NotNull(message = "Novelty id {value.notblank}")
                                                                         @RequestParam Long noveltyId) {
        return ResponseEntity.ok().body(noveltyCommentMapper.
                noveltyCommentListToNoveltyCommentListResponse(noveltyCommentService.findAllByNoveltyId(noveltyId)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyCommentResponse> findById(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id) {
        return ResponseEntity.ok().body(noveltyCommentMapper
                .noveltyCommentToNoveltyCommentResponse(noveltyCommentService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyCommentResponse> create(@RequestBody @Valid IncomingNoveltyCommentRequest request) {
        NoveltyComment comment = noveltyCommentService.saveNoveltyComment(noveltyCommentMapper.requestToNoveltyComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(noveltyCommentMapper.noveltyCommentToNoveltyCommentResponse(comment));
    }

    //    @PutMapping("/{id}/{clientId}")
    //    @CheckClientId
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckRole
    public ResponseEntity<NoveltyCommentResponse> update(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id,
                                                         @RequestBody @Valid IncomingNoveltyCommentRequest request) {
        NoveltyComment updatedComment = noveltyCommentService.update(noveltyCommentMapper.requestToNoveltyComment(id, request));

        return ResponseEntity.ok().body(noveltyCommentMapper.noveltyCommentToNoveltyCommentResponse(updatedComment));
    }

    //    @DeleteMapping("/{id}/{clientId}")
    //    @CheckClientId
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckRole
    public ResponseEntity<Void> delete(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id) {
        noveltyCommentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
