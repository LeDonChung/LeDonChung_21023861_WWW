package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.UserDto;
import vn.edu.fit.student.donchung.backend.dtos.UserRegisterDto;

public interface UserService {
    public UserDto loadByUsername(String username);

    UserDto register(UserRegisterDto userDto);
}
