package io.github.mrandrey24.controller.dto;

import java.util.Date;

public record UrlResponse(
        String url,
        String shortCode,
        Date createdAt,
        Date updatedAt,
        Integer accessCount
) {
}
