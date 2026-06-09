package mate.academy.country_information_service.config.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        implementationPackage = "mate.academy.country_information_service.mapper.impl"
)
public interface MapperConfig {
}
