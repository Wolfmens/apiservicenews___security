package com.study.apiservicenews.validator;

import com.study.apiservicenews.model.NoveltyFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class ValidatorNoveltyFilter implements ConstraintValidator<ValidNoveltyFilter, NoveltyFilter> {

    @Override
    public boolean isValid(NoveltyFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNumber(),value.getPageSize())) {
            return false;
        }
        return true;
    }
}
