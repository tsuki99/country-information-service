package com.tsuki.countryinformationservice.dto;

import com.tsuki.countryinformationservice.dto.field.CurrenciesDto;
import java.util.List;
import java.util.Map;
import lombok.Data;
import com.tsuki.countryinformationservice.dto.field.NameDto;

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
