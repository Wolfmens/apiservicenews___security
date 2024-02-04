package com.study.apiservicenews.web.model.novelty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyListResponse {

    private List<NoveltyResponse> novelties = new ArrayList<>();

}
