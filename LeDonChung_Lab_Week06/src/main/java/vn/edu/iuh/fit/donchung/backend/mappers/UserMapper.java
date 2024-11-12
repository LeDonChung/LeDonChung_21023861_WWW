package vn.edu.iuh.fit.donchung.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.backend.entities.User;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserLoginRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRegisterRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.UserResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRegisterRequest userDto);

    User toEntity(UserLoginRequest userDto);


    UserResponse toDto(User user);

}