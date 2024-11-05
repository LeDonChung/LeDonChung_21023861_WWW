package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.UserDto;

public interface UserService {
    public UserDto loadByUsername(String username);
}
