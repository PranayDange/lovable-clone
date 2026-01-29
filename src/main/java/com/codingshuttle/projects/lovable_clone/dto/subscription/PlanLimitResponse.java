package com.codingshuttle.projects.lovable_clone.dto.subscription;

public record PlanLimitResponse(
        String planName,
        int maxTokenPerDay,
        int maxProjects,
        boolean unlimitedAi
) {
}
