package com.study.apiservicenews.web.model.noveltycategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyCategoryListResponse {

    private List<NoveltyCategoryResponse> categories = new ArrayList<>();

}
