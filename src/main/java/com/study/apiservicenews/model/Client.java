package com.study.apiservicenews.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToStringExclude
    @Builder.Default
    private List<Novelty> novelties = new ArrayList<>();

    public void addNovelty(Novelty novelty) {
        novelties.add(novelty);
    }

    public void removeNovelty(Novelty novelty) {
        novelties.remove(novelty);
    }


}
