package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NoveltyCommentRepository extends JpaRepository<NoveltyComment, Long>, JpaSpecificationExecutor<NoveltyComment> {

    List<NoveltyComment> findAllByNoveltyId(Long id);

}
