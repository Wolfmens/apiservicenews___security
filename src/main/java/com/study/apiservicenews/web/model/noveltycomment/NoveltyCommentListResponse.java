package com.study.apiservicenews.web.model.noveltycomment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyCommentListResponse {

    private List<NoveltyCommentResponse> comments = new ArrayList<>();

}
