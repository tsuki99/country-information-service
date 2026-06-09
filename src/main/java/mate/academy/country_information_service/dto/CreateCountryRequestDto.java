package mate.academy.country_information_service.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import mate.academy.country_information_service.dto.field.CurrenciesDto;
import mate.academy.country_information_service.dto.field.NameDto;

@Data
public class CreateCountryRequestDto {
    private NameDto name;
    private List<String> capital;
    private String region;
    private Long population;
    private Long area;
    private Map<String, String> languages;
    private Map<String, CurrenciesDto> currencies;
}
