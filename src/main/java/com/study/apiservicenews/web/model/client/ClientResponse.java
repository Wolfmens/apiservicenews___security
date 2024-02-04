package com.study.apiservicenews.web.model.client;

import com.study.apiservicenews.web.model.novelty.NoveltyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long id;

    private String name;

    private List<NoveltyResponse> novelties = new ArrayList<>();
}
