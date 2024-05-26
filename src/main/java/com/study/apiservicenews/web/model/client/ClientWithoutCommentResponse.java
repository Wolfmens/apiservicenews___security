package com.study.apiservicenews.web.model.client;

import com.study.apiservicenews.web.model.novelty.NoveltyWithoutCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientWithoutCommentResponse {

    private Long id;

    private String name;

    private String password;

    private List<NoveltyWithoutCommentResponse> novelties = new ArrayList<>();

}
