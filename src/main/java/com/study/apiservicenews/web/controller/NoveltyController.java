package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.aop.annotation.CheckClientId;
import com.study.apiservicenews.aop.annotation.CheckRole;
import com.study.apiservicenews.mapper.NoveltyMapper;
import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.service.NoveltyService;
import com.study.apiservicenews.web.model.novelty.IncomingNoveltyRequest;
import com.study.apiservicenews.web.model.novelty.NoveltyResponse;
import com.study.apiservicenews.web.model.novelty.NoveltyWithoutCommentListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news/novelty")
public class NoveltyController {

    private final NoveltyMapper noveltyMapper;

    private final NoveltyService noveltyService;

    @GetMapping("/filter")
    public ResponseEntity<NoveltyWithoutCommentListResponse> filterByAuthorsOrCategory(@Valid NoveltyFilter filter) {
        return ResponseEntity.ok().body(
                noveltyMapper.noveltyListToNoveltyWithoutCommentListResponse
                        (noveltyService.filterByAuthorOrCategory(filter)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyWithoutCommentListResponse> findAll(@Valid NoveltyFilter filter) {
        return ResponseEntity.ok().body(
                noveltyMapper.noveltyListToNoveltyWithoutCommentListResponse(noveltyService.findAll(filter)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyResponse> findById(@PathVariable @NotNull(message = "Novelty ID {value.notblank}") Long id) {
        return ResponseEntity.ok().body(noveltyMapper.noveltyToNoveltyResponse(noveltyService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NoveltyResponse> create(@RequestBody @Valid IncomingNoveltyRequest request,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        Novelty novelty = noveltyService.saveNovelty(noveltyMapper.requestToNovelty(request, userDetails));

        return ResponseEntity.status(HttpStatus.CREATED).body(noveltyMapper.noveltyToNoveltyResponse(novelty));
    }

    //    @PutMapping("/{id}/{clientId}")
    //    @CheckClientId
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckRole
    public ResponseEntity<NoveltyResponse> update(@PathVariable @NotNull(message = "Novelty comment ID {value.notblank}") Long id,
                                                  @RequestBody @Valid IncomingNoveltyRequest request,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        Novelty updatedNovelty = noveltyService.update(noveltyMapper.requestToNovelty(id, request, userDetails));

        return ResponseEntity.ok().body(noveltyMapper.noveltyToNoveltyResponse(updatedNovelty));
    }

//    @DeleteMapping("/{id}/{clientId}")
//    @CheckClientId
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckRole
    public ResponseEntity<Void> delete(@PathVariable(name = "id")
                                       @NotNull(message = "Novelty ID {value.notblank}") Long id) {
        noveltyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
