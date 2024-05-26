package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.mapper.NoveltyCategoryMapper;
import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.model.NoveltyFilter;
import com.study.apiservicenews.service.NoveltyCategoryService;
import com.study.apiservicenews.web.model.noveltycategory.IncomingNoveltyCategoryRequest;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryListResponse;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news/novcat")
public class NoveltyCategoryController {

    private final NoveltyCategoryService noveltyCategoryService;

    private final NoveltyCategoryMapper noveltyCategoryMapper;

    @GetMapping

    public ResponseEntity<NoveltyCategoryListResponse> findAll(@Valid NoveltyFilter filter) {

        return ResponseEntity.ok().body(
                noveltyCategoryMapper.noveltyCategoryListToNoveltyCategoryListResponse(noveltyCategoryService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoveltyCategoryResponse> findByID(@PathVariable
                                                            @NotNull(message = "Novelty category ID {value.notblank}") Long id) {

        return ResponseEntity.ok().body(noveltyCategoryMapper
                .noveltyCategoryToNoveltyCategoryResponse(noveltyCategoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NoveltyCategoryResponse> create(@RequestBody @Valid IncomingNoveltyCategoryRequest request) {
        NoveltyCategory category = noveltyCategoryService.saveNoveltyCategory(noveltyCategoryMapper.requestToNoveltyCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(noveltyCategoryMapper.noveltyCategoryToNoveltyCategoryResponse(category));
    }

    @PutMapping("{id}")
    public ResponseEntity<NoveltyCategoryResponse> update(@PathVariable
                                                          @NotNull(message = "Novelty category ID {value.notblank}") Long id,
                                                          @RequestBody @Valid IncomingNoveltyCategoryRequest request) {
        NoveltyCategory updatedCategory = noveltyCategoryService.update(noveltyCategoryMapper.requestToNoveltyCategory(id, request));

        return ResponseEntity.ok().body(noveltyCategoryMapper.noveltyCategoryToNoveltyCategoryResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable
                                       @NotNull(message = "Novelty category ID {value.notblank}") Long id) {
        noveltyCategoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
