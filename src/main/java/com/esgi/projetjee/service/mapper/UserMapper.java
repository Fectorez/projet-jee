package com.esgi.projetjee.service.mapper;

import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.service.dto.UserDto;
import org.mapstruct.*;

@Mapper(uses = { UserDto.class }, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username")
    })
    UserDto userToUserDto(User user);

    @InheritInverseConfiguration
    User userDtoToUser(UserDto userDto);
}
