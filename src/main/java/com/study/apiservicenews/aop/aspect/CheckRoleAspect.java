package com.study.apiservicenews.aop.aspect;

import com.study.apiservicenews.model.Client;
import com.study.apiservicenews.security.CustomUserDetail;
import com.study.apiservicenews.service.ClientService;
import com.study.apiservicenews.service.NoveltyCommentService;
import com.study.apiservicenews.service.NoveltyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.text.MessageFormat;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckRoleAspect {

    private final ClientService clientService;

    private final NoveltyService noveltyService;

    private final NoveltyCommentService noveltyCommentService;

    @Before(value = "@annotation(com.study.apiservicenews.aop.annotation.CheckRole)")
    public void checkRole(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        var pathVariables = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String nameClass = joinPoint.getTarget().getClass().getSimpleName();
        Long paramEntityId = Long.parseLong(pathVariables.get("id"));
        var authEntity = clientService.findByName(userDetail.getUsername());
        String nameMethod = joinPoint.getSignature().getName();

        boolean hasRoleAdminOrModerator = authentication.getAuthorities().stream().anyMatch((grantedAuthority ->
                grantedAuthority.getAuthority().contains("ADMIN") ||  grantedAuthority.getAuthority().contains("MODERATOR")));

        if (nameClass.equals("ClientController")) {
            if (!hasRoleAdminOrModerator && !authEntity.getId().equals(paramEntityId)) {
                throw new RuntimeException(
                        MessageFormat.format(
                                        "Client: {0} by id {1} have not required role (ADMIN or MODERATOR)"
                                        ,userDetail.getUsername()
                                        ,authEntity.getId()));
            }
        }
        if (nameClass.equals("NoveltyController")) {
            var noveltyEntity = noveltyService.findById(paramEntityId);
            Long creatorNoveltyId = noveltyEntity.getClient().getId();
            actionByNameMethodAndRoleClient(nameMethod,hasRoleAdminOrModerator, authEntity, creatorNoveltyId, paramEntityId);
        }
        if (nameClass.equals("NoveltyCommentController")) {
            var noveltyCommentEntity = noveltyCommentService.findById(paramEntityId);
            Long creatorCommentId = noveltyCommentEntity.getNovelty().getClient().getId();
            actionByNameMethodAndRoleClient(nameMethod,hasRoleAdminOrModerator,authEntity, creatorCommentId, paramEntityId);
        }
    }

    private void actionByNameMethodAndRoleClient(String nameMethod,
                                                 Boolean hasRole,
                                                 Client authEntity,
                                                 Long compareEntityId,
                                                 Long paramEntityIdFromRequest) {
        switch (nameMethod) {
            case "update" -> {
                if (!authEntity.getId().equals(compareEntityId)) {
                    throw new RuntimeException(
                            MessageFormat.format(
                                    "Update error!! Entity by id {0} from request created by another user",
                                    paramEntityIdFromRequest));
                }
            }
            case "delete" -> {
                if (!hasRole && !authEntity.getId().equals(compareEntityId)) {
                    throw new RuntimeException(
                            MessageFormat.format(
                                    "Delete error!! Entity by id {0} from request created by another user",
                                    paramEntityIdFromRequest));
                }
            }
        }
    }
}
