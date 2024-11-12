package vn.edu.fit.student.donchung.frontend.models;

import vn.edu.fit.student.donchung.backend.dtos.UserDto;

public interface UserModel {
    public UserDto loadByUsername(String username);
}
