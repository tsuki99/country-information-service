package com.tsuki.countryinformationservice.dto;

import lombok.Data;

@Data
public class CountryComparisonDto {
    private String firstCountry;
    private String secondCountry;
    private boolean sameRegion;
    private boolean sameCurrency;
    private String biggerPopulation;
    private String biggerArea;
}
