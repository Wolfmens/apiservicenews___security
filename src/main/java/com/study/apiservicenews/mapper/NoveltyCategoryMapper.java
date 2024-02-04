package com.study.apiservicenews.mapper;

import com.study.apiservicenews.model.NoveltyCategory;
import com.study.apiservicenews.web.model.noveltycategory.IncomingNoveltyCategoryRequest;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryListResponse;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryRequest;
import com.study.apiservicenews.web.model.noveltycategory.NoveltyCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoveltyCategoryMapper {

    NoveltyCategory requestToNoveltyCategory(IncomingNoveltyCategoryRequest request);

    @Mapping(source = "noveltyCategoryId", target = "id")
    NoveltyCategory requestToNoveltyCategory(Long noveltyCategoryId, IncomingNoveltyCategoryRequest request);

    NoveltyCategoryResponse noveltyCategoryToNoveltyCategoryResponse(NoveltyCategory category);

    List<NoveltyCategoryResponse> noveltyCategoryListToNoveltyCategoryResponseList(List<NoveltyCategory> categories);

    default NoveltyCategoryListResponse noveltyCategoryListToNoveltyCategoryListResponse(List<NoveltyCategory> categories) {
        NoveltyCategoryListResponse noveltyCategoryListResponse = new NoveltyCategoryListResponse();
        noveltyCategoryListResponse.setCategories(noveltyCategoryListToNoveltyCategoryResponseList(categories));

        return noveltyCategoryListResponse;
    }

    default NoveltyCategory NoveltyCategoryRequestToNoveltyCategory(NoveltyCategoryRequest categoryRequest) {
        NoveltyCategory category = new NoveltyCategory();
        category.setName(categoryRequest.getName());

        return category;
    }
}
