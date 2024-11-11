package vn.edu.iuh.fit.donchung.frontend.models;

import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;

public interface UserModel {
    public PageDto<UserDto> getAllByPage(int page , int size);
}
