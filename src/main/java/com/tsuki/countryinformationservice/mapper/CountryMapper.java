package com.tsuki.countryinformationservice.mapper;

import com.tsuki.countryinformationservice.config.mapper.MapperConfig;
import com.tsuki.countryinformationservice.dto.CountryDetailDto;
import com.tsuki.countryinformationservice.dto.CountryDto;
import com.tsuki.countryinformationservice.dto.CreateCountryRequestDto;
import com.tsuki.countryinformationservice.dto.field.CurrenciesDto;
import com.tsuki.countryinformationservice.dto.field.NameDto;
import java.util.Map;
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
