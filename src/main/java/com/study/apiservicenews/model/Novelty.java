package com.study.apiservicenews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "novelties")
public class Novelty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @ToStringExclude
    private Client client;

    @ManyToOne
    @ToStringExclude
    private NoveltyCategory category;

    @OneToMany(mappedBy = "novelty", cascade = CascadeType.ALL)
    @ToStringExclude
    @Builder.Default
    private List<NoveltyComment> comments = new ArrayList<>();

    public void addComment(NoveltyComment comment) {
        comments.add(comment);
    }

    public void removeComment(NoveltyComment comment) {
        comments.remove(comment);
    }



}
