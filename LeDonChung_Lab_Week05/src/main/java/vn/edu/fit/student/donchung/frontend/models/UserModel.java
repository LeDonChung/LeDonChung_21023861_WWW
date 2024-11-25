package vn.edu.fit.student.donchung.frontend.models;


import vn.edu.fit.student.donchung.frontend.dto.UserDto;

public interface UserModel {
    public UserDto loadByUsername(String username);
}
