package com.tsuki.countryinformationservice.dto.error;

import java.util.List;

public record ErrorResponse(List<FieldErrorDto> errors) {
}
