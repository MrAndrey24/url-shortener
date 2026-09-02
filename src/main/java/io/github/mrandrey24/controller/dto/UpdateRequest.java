package io.github.mrandrey24.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UpdateRequest(
        @NotBlank @URL String url
) {
}
