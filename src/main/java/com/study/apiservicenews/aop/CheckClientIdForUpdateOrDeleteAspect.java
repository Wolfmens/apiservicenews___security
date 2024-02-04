package com.study.apiservicenews.aop;

import com.study.apiservicenews.exception.IncorrectClientIIdException;

import com.study.apiservicenews.service.NoveltyCommentService;
import com.study.apiservicenews.service.NoveltyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckClientIdForUpdateOrDeleteAspect {

    @Autowired
    private final NoveltyService noveltyService;

    @Autowired
    private final NoveltyCommentService noveltyCommentService;


    @Before(value = "@annotation(CheckClientId)")
    public void checkClientIdBefore(JoinPoint joinPoint) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        var pathVariables = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Long clientId = Long.parseLong(pathVariables.get("clientId"));
        Long entityId = Long.parseLong(pathVariables.get("id"));

        String nameClass = joinPoint.getTarget().getClass().getSimpleName();

        Long clientIdFromBase = 0L;

        if (nameClass.equals("NoveltyController")) {
            clientIdFromBase = noveltyService.findById(entityId).getClient().getId();
        }
        if (nameClass.equals("NoveltyCommentController")) {
            clientIdFromBase = noveltyCommentService.findById(entityId).getNovelty().getClient().getId();
        }

        if (clientId == null || !Objects.equals(clientIdFromBase, clientId)) {
            throw new IncorrectClientIIdException(MessageFormat.format("Client id {0} for editing/deleting a novelty or comment is incorrect", clientId));
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

}
