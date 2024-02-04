package com.study.apiservicenews.web.model.novelty;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyWithoutCommentListResponse {

    List<NoveltyWithoutCommentResponse> novelties = new ArrayList<>();

}
