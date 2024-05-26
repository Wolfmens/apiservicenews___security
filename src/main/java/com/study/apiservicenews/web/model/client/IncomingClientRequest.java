package com.study.apiservicenews.web.model.client;

import com.study.apiservicenews.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingClientRequest {

    @NotBlank(message = "Client name {value.notblank}")
    private String name;

    private String password;

    private List<RoleType> roles;
}
