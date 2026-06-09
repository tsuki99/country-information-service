package mate.academy.country_information_service.mapper;

import java.util.Map;
import mate.academy.country_information_service.config.mapper.MapperConfig;
import mate.academy.country_information_service.dto.CountryDetailDto;
import mate.academy.country_information_service.dto.CountryDto;
import mate.academy.country_information_service.dto.CreateCountryRequestDto;
import mate.academy.country_information_service.dto.field.CurrenciesDto;
import mate.academy.country_information_service.dto.field.NameDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CountryMapper {
    CountryDto toCountryDto(CreateCountryRequestDto requestDto);

    CountryDetailDto toCountryDetailDto(CreateCountryRequestDto requestDto);

    default String mapName(NameDto name) {
        return name.getCommon();
    }

    default String mapLanguages(Map<String, String> languages) {
        if (languages == null || languages.isEmpty()) {
            return null;
        }

        return String.join(", ", languages.values());
    }

    default String mapCurrencies(Map<String, CurrenciesDto> currencies) {
        if (currencies == null || currencies.isEmpty()) {
            return null;
        }

        return String.join(", ", currencies.keySet());
    }
}
