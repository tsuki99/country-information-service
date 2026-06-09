package mate.academy.country_information_service.service;

import java.util.List;
import mate.academy.country_information_service.dto.CountryComparisonDto;
import mate.academy.country_information_service.dto.CountryDetailDto;
import mate.academy.country_information_service.dto.CountryDto;

public interface CountryService {
    List<CountryDto> findCountriesByName(String name);

    CountryDetailDto findSpecificCountryWithDetails(String name);

    CountryComparisonDto compareTwoCountries(
            String firstCountryName,
            String secondCountryName
    );
}
