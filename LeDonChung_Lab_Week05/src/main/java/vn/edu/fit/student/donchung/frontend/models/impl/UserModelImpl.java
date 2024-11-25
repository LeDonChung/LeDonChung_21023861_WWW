package vn.edu.fit.student.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.frontend.dto.*;
import vn.edu.fit.student.donchung.frontend.models.UserModel;
import vn.edu.fit.student.donchung.frontend.utils.AppUtils;
import vn.edu.fit.student.donchung.frontend.utils.RoleUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserModelImpl implements UserModel {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public UserDto loadByUsername(String username) {
        return restTemplate.getForObject(AppUtils.API_URL + "/users/loadByUsername?username=" + username, UserDto.class);
    }

    @Override
    public UserDto register(UserRegisterDto userRegisterDTO) {
        return restTemplate.postForObject(AppUtils.API_URL + "/users/register", userRegisterDTO, UserDto.class);
    }
}
