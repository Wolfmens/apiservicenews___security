package com.study.apiservicenews.model;

import com.study.apiservicenews.validator.ValidNoveltyFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ValidNoveltyFilter
public class NoveltyFilter {

    private String category;

    private String author;

    private Integer pageNumber;

    private Integer pageSize;
}
