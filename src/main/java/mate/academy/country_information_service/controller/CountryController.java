package mate.academy.country_information_service.controller;

import static mate.academy.country_information_service.config.swagger.SwaggerConstants.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.country_information_service.dto.CountryComparisonDto;
import mate.academy.country_information_service.dto.CountryDetailDto;
import mate.academy.country_information_service.dto.CountryDto;
import mate.academy.country_information_service.service.CountryService;
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
                    description = COUNTRY_NAME_DESC,
                    example = FIRST_COUNTRY_EXAMPLE
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
                    description = COUNTRY_NAME_DESC,
                    example = FIRST_COUNTRY_EXAMPLE
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
                    description = FIRST_COUNTRY_DESC,
                    example = FIRST_COUNTRY_EXAMPLE
            )
            String first,
            @RequestParam @Parameter(
                    description = SECOND_COUNTRY_DESC,
                    example = SECOND_COUNTRY_EXAMPLE
            )
            String second
    ) {
        return countryService.compareTwoCountries(first, second);
    }
}
