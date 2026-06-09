package mate.academy.country_information_service.dto.error;

import java.util.List;

public record ErrorResponse(List<FieldErrorDto> errors) {
}
