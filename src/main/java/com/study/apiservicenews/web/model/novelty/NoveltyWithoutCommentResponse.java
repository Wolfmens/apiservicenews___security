package com.study.apiservicenews.web.model.novelty;

import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyWithoutCommentResponse {

    private Long id;

    private String title;

    private NoveltyCategoryResponse category;

    private Integer countComments;
}
