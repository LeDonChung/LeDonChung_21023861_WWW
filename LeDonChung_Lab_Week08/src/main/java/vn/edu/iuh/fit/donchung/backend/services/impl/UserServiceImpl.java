package vn.edu.iuh.fit.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.backend.entities.User;
import vn.edu.iuh.fit.donchung.backend.repositories.UserRepository;
import vn.edu.iuh.fit.donchung.backend.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public PageDto<UserDto> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<User> pageUser = userRepository.findAll(pageRequest);

        List<UserDto> userDtoList = pageUser.stream()
                .map((value) -> UserDto.builder()
                        .id(value.getId())
                        .intro(value.getIntro())
                        .firstName(value.getFirstName())
                        .lastName(value.getLastName())
                        .build())
                .toList();
        return PageDto.<UserDto>builder()
                .page(page)
                .size(size)
                .totalPages(pageUser.getTotalPages())
                .values(userDtoList)
                .build();
    }
}
