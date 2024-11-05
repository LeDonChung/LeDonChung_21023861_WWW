package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.UserMapper;
import vn.edu.fit.student.donchung.backend.dtos.UserDto;
import vn.edu.fit.student.donchung.backend.repositories.UserRepository;
import vn.edu.fit.student.donchung.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto loadByUsername(String username) {
        return userMapper.toDto(
                userRepository.findByUsername(username)
        );
    }
}
