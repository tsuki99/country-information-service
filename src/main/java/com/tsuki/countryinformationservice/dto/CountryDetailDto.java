package com.tsuki.countryinformationservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class CountryDetailDto {
    private String name;
    private List<String> capital;
    private String region;
    private Long population;
    private Long area;
    private String languages;
    private String currencies;
}
