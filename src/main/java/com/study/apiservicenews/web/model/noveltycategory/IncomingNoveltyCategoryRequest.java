package com.study.apiservicenews.web.model.noveltycategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingNoveltyCategoryRequest {

    @NotBlank(message = "Novelty category name {value.notblank}")
    @Size(min = 2, max = 25, message = "Novelty category name {range.text.value}")
    private String name;

}
