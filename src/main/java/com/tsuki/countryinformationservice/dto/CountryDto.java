package com.tsuki.countryinformationservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class CountryDto {
    private String name;
    private List<String> capital;
    private String region;
}
