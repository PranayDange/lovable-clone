package com.codingshuttle.projects.lovable_clone.dto.subscription;

public record PlanLimitResponse(
        String planName,
        Integer maxTokenPerDay,
        Integer maxProjects,
        Boolean unlimitedAi
) {
}
