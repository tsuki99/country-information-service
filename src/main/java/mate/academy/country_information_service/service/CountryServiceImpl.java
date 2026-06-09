package mate.academy.country_information_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.country_information_service.client.ApiClient;
import mate.academy.country_information_service.dto.CountryComparisonDto;
import mate.academy.country_information_service.dto.CountryDetailDto;
import mate.academy.country_information_service.dto.CountryDto;
import mate.academy.country_information_service.dto.CreateCountryRequestDto;
import mate.academy.country_information_service.exception.EntityNotFoundException;
import mate.academy.country_information_service.mapper.CountryMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private static final String URL_FILTER_BY_NAME = "https://restcountries.com/v3.1/name/";
    private static final String URL_SPECIFIC_COUNTRY_BY_NAME =
            "https://restcountries.com/v3.1/name/%s?fullText=true";
    private static final String COMMA = ", ";
    private static final String EQUALS_VALUES = "Same";
    private static final String EXCEPTION_MESSAGE_COUNTRY_NOT_FOUND = "No such country with name: ";
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> findCountriesByName(String name) {
        String pageJson = apiClient.getPage(getUrlWithFilteringByName(name));

        try {
            JsonNode jsonNode = objectMapper.readTree(pageJson);

            if (jsonNode.has("message")) {
                throw new EntityNotFoundException(EXCEPTION_MESSAGE_COUNTRY_NOT_FOUND + name);
            }

            List<CreateCountryRequestDto> requestDtos = objectMapper.convertValue(
                    jsonNode,
                    new TypeReference<>() {}
            );

            List<CountryDto> countryDtos = requestDtos.stream()
                    .map(countryMapper::toCountryDto)
                    .toList();

            if (countryDtos.isEmpty()) {
                throw new EntityNotFoundException(EXCEPTION_MESSAGE_COUNTRY_NOT_FOUND + name);
            }

            return countryDtos;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CountryDetailDto findSpecificCountryWithDetails(String name) {
        String pageJson = apiClient.getPage(getUrlWithSpecificName(name));

        try {
            JsonNode jsonNode = objectMapper.readTree(pageJson);

            if (jsonNode.has("message")) {
                throw new EntityNotFoundException(EXCEPTION_MESSAGE_COUNTRY_NOT_FOUND + name);
            }

            List<CreateCountryRequestDto> requestDtos = objectMapper.convertValue(
                    jsonNode,
                    new TypeReference<>() {}
            );

            List<CountryDetailDto> countryDetailDto = requestDtos.stream()
                    .map(countryMapper::toCountryDetailDto)
                    .toList();

            if (countryDetailDto.isEmpty()) {
                throw new EntityNotFoundException(EXCEPTION_MESSAGE_COUNTRY_NOT_FOUND + name);
            }

            return countryDetailDto.get(0);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CountryComparisonDto compareTwoCountries(
            String firstCountryName,
            String secondCountryName
    )
    {
        CountryDetailDto firstCountry = findSpecificCountryWithDetails(firstCountryName);
        CountryDetailDto secondCountry = findSpecificCountryWithDetails(secondCountryName);

        return createComparisonObject(firstCountry, secondCountry);
    }

    private static String getUrlWithFilteringByName(String name) {
        return URL_FILTER_BY_NAME.concat(name);
    }

    private static String getUrlWithSpecificName(String name) {
        return URL_SPECIFIC_COUNTRY_BY_NAME.formatted(name);
    }

    private static boolean currenciesCompare(String currenciesFirst, String currenciesSecond) {
        String[] arrCur1 = currenciesFirst.split(COMMA);
        String[] arrCur2 = currenciesSecond.split(COMMA);

        HashSet<String> setCur1 = new HashSet<>(Arrays.asList(arrCur1));

        for (String currency : arrCur2) {
            if (setCur1.contains(currency)) {
                return true;
            }
        }

        return false;
    }

    private static String hasBiggerPopulation(CountryDetailDto firstCountry, CountryDetailDto secondCountry) {
        return firstCountry.getPopulation() > secondCountry.getPopulation()
                ? firstCountry.getName()
                : secondCountry.getPopulation() > firstCountry.getPopulation()
                ? secondCountry.getName()
                : EQUALS_VALUES;
    }

    private static String hasBiggerArea(CountryDetailDto firstCountry, CountryDetailDto secondCountry) {
        return firstCountry.getArea() > secondCountry.getArea()
                ? firstCountry.getName()
                : secondCountry.getArea() > firstCountry.getArea()
                ? secondCountry.getName()
                : EQUALS_VALUES;
    }

    private static CountryComparisonDto createComparisonObject(
            CountryDetailDto firstCountry,
            CountryDetailDto secondCountry
    ) {
        CountryComparisonDto countryComparisonDto = new CountryComparisonDto();
        countryComparisonDto
                .setFirstCountry(firstCountry.getName());
        countryComparisonDto
                .setSecondCountry(secondCountry.getName());
        countryComparisonDto
                .setSameRegion(firstCountry.getRegion().equals(secondCountry.getRegion()));
        countryComparisonDto
                .setSameCurrency(currenciesCompare(
                        firstCountry.getCurrencies(),
                        secondCountry.getCurrencies()
                ));
        countryComparisonDto
                .setBiggerPopulation(hasBiggerPopulation(firstCountry, secondCountry));
        countryComparisonDto
                .setBiggerArea(hasBiggerArea(firstCountry, secondCountry));

        return countryComparisonDto;
    }
}
