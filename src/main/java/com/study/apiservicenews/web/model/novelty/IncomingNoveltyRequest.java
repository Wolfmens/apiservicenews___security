package com.study.apiservicenews.web.model.novelty;

import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingNoveltyRequest {


    @NotNull(message = "Client id {value.notblank}")
    private Long clientId;

    @NotBlank(message = "Novelty title {value.notblank}")
    @Size(min = 1, max = 40, message = "Novelty title {range.text.value}")
    private String title;

    @NotNull(message = "Category {value.notblank}")
    private NoveltyCategoryRequest category;

}
