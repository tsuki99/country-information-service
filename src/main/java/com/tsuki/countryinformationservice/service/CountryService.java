package com.tsuki.countryinformationservice.service;

import com.tsuki.countryinformationservice.dto.CountryComparisonDto;
import java.util.List;
import com.tsuki.countryinformationservice.dto.CountryDetailDto;
import com.tsuki.countryinformationservice.dto.CountryDto;

public interface CountryService {
    List<CountryDto> findCountriesByName(String name);

    CountryDetailDto findSpecificCountryWithDetails(String name);

    CountryComparisonDto compareTwoCountries(
            String firstCountryName,
            String secondCountryName
    );
}
