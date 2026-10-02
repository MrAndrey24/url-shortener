package io.github.mrandrey24.controller.dto;

import java.time.Instant;

public record UrlResponseStats(
        String url,
        String shortCode,
        Instant createdAt,
        Instant updatedAt,
        Integer accessCount
) {
}
