package com.study.apiservicenews.reposittory;

import com.study.apiservicenews.model.Novelty;
import com.study.apiservicenews.model.NoveltyFilter;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationNovelty {

    static Specification<Novelty> byFilter(NoveltyFilter noveltyFilter) {
     return Specification.where(findingAuthors(noveltyFilter))
             .and(findingCategories(noveltyFilter));
    }

    static Specification<Novelty> findingCategories(NoveltyFilter noveltyFilter) {
        return ((root, query, criteriaBuilder) -> {
            if (noveltyFilter.getCategory() == null) {
                return null;
            }
           return criteriaBuilder.equal(root.get("category").get("name"), noveltyFilter.getCategory());
        });
    }

    static Specification<Novelty> findingAuthors(NoveltyFilter noveltyFilter) {
        return ((root, query, criteriaBuilder) -> {
            if (noveltyFilter.getAuthor() == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("client").get("name"), noveltyFilter.getAuthor());
        });
    }


}
