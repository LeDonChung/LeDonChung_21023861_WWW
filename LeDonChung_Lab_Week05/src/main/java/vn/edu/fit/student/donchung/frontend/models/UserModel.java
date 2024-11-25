package vn.edu.fit.student.donchung.frontend.models;


import vn.edu.fit.student.donchung.frontend.dto.UserDto;
import vn.edu.fit.student.donchung.frontend.dto.UserRegisterDto;

public interface UserModel {
    UserDto loadByUsername(String username);

    UserDto register(UserRegisterDto userRegisterDTO);
}
