package vn.edu.iuh.fit.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.frontend.models.UserModel;

import java.lang.reflect.ParameterizedType;

@Component
public class UserModelImpl implements UserModel {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageDto<UserDto> getAllByPage(int page, int size) {
        String BASE_URL_API = "http://localhost:8080/api";
        ResponseEntity<PageDto<UserDto>> response = restTemplate.exchange(
                        BASE_URL_API +"/users?page="+page+"&size="+size,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PageDto<UserDto>>() {}
                );
        return response.getBody();
    }
}
