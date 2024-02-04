package com.study.apiservicenews.web.model.client;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingClientRequest {

    @NotBlank(message = "Client name {value.notblank}")
    private String name;
}
