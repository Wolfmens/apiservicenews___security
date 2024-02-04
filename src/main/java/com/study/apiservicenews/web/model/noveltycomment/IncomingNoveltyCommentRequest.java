package com.study.apiservicenews.web.model.noveltycomment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingNoveltyCommentRequest {

    @NotBlank(message = "Comment text {value.notblank}")
    @Size(min = 3, max = 255, message = "Novelty comment {range.text.value}")
    private String text;

    @NotNull(message = "Novelty id {value.notblank}")
    private Long noveltyId;

}
