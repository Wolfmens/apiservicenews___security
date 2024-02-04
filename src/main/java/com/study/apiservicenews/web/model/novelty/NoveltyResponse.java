package com.study.apiservicenews.web.model.novelty;

import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryResponse;
import com.study.apiservicenews.web.model.noveltycomment.NoveltyCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyResponse {

    private Long id;

    private String title;

    private NoveltyCategoryResponse category;

    private List<NoveltyCommentResponse> comments = new ArrayList<>();
}
