package com.study.apiservicenews.web.model.noveltycategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoveltyCategoryRequest {

    @NotBlank(message = "Novelty category {value.notblank}")
    private String name;

}
