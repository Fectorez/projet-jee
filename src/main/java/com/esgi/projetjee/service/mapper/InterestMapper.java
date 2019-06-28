package com.esgi.projetjee.service.mapper;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.service.dto.InterestDto;
import org.mapstruct.*;

@Mapper(uses = { InterestDto.class }, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterestMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    InterestDto interestToInterestDto(Interest interest);

    @InheritInverseConfiguration
    Interest interestDtoToInterest(InterestDto interestDto);
}
