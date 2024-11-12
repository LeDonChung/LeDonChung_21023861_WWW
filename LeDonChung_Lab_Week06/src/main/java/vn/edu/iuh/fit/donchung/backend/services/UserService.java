package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.backend.exceptions.UserException;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserLoginRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRegisterRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.UserResponse;

public interface UserService {
    public PageDto<UserResponse> getAll(int page, int size);

    UserResponse register(UserRegisterRequest request) throws UserException;

    UserResponse login(UserLoginRequest request) throws UserException;

    UserResponse update(UserRequest request) throws UserException;
}
