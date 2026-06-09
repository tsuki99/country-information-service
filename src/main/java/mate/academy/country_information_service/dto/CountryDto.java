package mate.academy.country_information_service.dto;

import java.util.List;
import lombok.Data;

@Data
public class CountryDto {
    private String name;
    private List<String> capital;
    private String region;
}
