package com.tsuki.countryinformationservice.controller;

import com.tsuki.countryinformationservice.config.swagger.SwaggerConstants;
import com.tsuki.countryinformationservice.dto.CountryComparisonDto;
import com.tsuki.countryinformationservice.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.tsuki.countryinformationservice.dto.CountryDetailDto;
import com.tsuki.countryinformationservice.dto.CountryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Countries API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @Operation(
            summary = "Get countries by name",
            description = "Get list of countries using filter of searching by part of name")
    @GetMapping("/search-by-part-name")
    public List<CountryDto> getCountriesByName(
            @RequestParam @Parameter(
                    description = SwaggerConstants.COUNTRY_NAME_DESC,
                    example = SwaggerConstants.FIRST_COUNTRY_EXAMPLE
            )
            String name
    ) {
        return countryService.findCountriesByName(name);
    }

    @Operation(
            summary = "Get country by name",
            description = "Get specific country with detail info using search by full name"
    )
    @GetMapping("/search-by-full-name")
    public CountryDetailDto getSpecificCountryByName(
            @RequestParam @Parameter(
                    description = SwaggerConstants.COUNTRY_NAME_DESC,
                    example = SwaggerConstants.FIRST_COUNTRY_EXAMPLE
            )
            String name
    ) {
        return countryService.findSpecificCountryWithDetails(name);
    }

    @Operation(
            summary = "Get comparison of two countries",
            description = "Get comparison object of two countries "
                    + "with compare by currency, population, area and region"
    )
    @GetMapping("/compare")
    public CountryComparisonDto compareTwoCountries(
            @RequestParam @Parameter(
                    description = SwaggerConstants.FIRST_COUNTRY_DESC,
                    example = SwaggerConstants.FIRST_COUNTRY_EXAMPLE
            )
            String first,
            @RequestParam @Parameter(
                    description = SwaggerConstants.SECOND_COUNTRY_DESC,
                    example = SwaggerConstants.SECOND_COUNTRY_EXAMPLE
            )
            String second
    ) {
        return countryService.compareTwoCountries(first, second);
    }
}
