package com.study.apiservicenews.web.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientWithoutCommentList {

    private List<ClientWithoutCommentResponse> clients = new ArrayList<>();

}
