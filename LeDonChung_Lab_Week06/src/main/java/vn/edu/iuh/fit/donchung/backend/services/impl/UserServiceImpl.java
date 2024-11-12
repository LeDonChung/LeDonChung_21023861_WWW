package vn.edu.iuh.fit.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.backend.entities.User;
import vn.edu.iuh.fit.donchung.backend.exceptions.UserException;
import vn.edu.iuh.fit.donchung.backend.mappers.UserMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.UserRepository;
import vn.edu.iuh.fit.donchung.backend.services.UserService;
import vn.edu.iuh.fit.donchung.backend.utils.PasswordUtils;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserLoginRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRegisterRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.UserResponse;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageDto<UserResponse> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<User> pageUser = userRepository.findAll(pageRequest);

        List<UserResponse> userDtoList = pageUser.stream()
                .map((value) -> UserResponse.builder()
                        .id(value.getId())
                        .intro(value.getIntro())
                        .firstName(value.getFirstName())
                        .lastName(value.getLastName())
                        .build())
                .toList();

        return PageDto.<UserResponse>builder()
                .page(page)
                .size(size)
                .totalPages(pageUser.getTotalPages())
                .values(userDtoList)
                .build();
    }

    @Override
    public UserResponse register(UserRegisterRequest request) throws UserException {
        User user = userRepository.findByMobile(request.getMobile());
        if (user != null) {
            throw new UserException("Tài khoản đã tồn tại.");
        }

        User userRegister = userMapper.toEntity(request);
        userRegister.setPasswordHash(PasswordUtils.hashPassword(request.getPasswordRaw()));
        userRegister.setRegisteredAt(Timestamp.from(Instant.now()));

        user = userRepository.save(userRegister);

        return userMapper.toDto(user);
    }

    @Override
    public UserResponse login(UserLoginRequest request) throws UserException {
        User user = userRepository.findByMobile(request.getPhone());
        if (user == null) {
            throw new UserException("Tài khoản hoặc mật khẩu không đúng.");
        }


        if (!PasswordUtils.checkPassword(request.getPasswordRaw(), user.getPasswordHash())) {
            throw new UserException("Tài khoản hoặc mật khẩu không đúng.");
        }
        user.setLastLogin(Timestamp.from(Instant.now()));
        User userLogin = userRepository.save(user);


        return userMapper.toDto(userLogin);
    }

    @Override
    public UserResponse update(UserRequest request) throws UserException {

        Optional<User> userOptional = userRepository.findById(request.getId());
        if (userOptional.isEmpty()) {
            throw new UserException("Tài khoản không tồn tại.");
        }

        User user = userOptional.get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMiddleName(request.getMiddleName());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setIntro(request.getIntro());
        user.setProfile(request.getProfile());

        User userSave = userRepository.save(user);
        return userMapper.toDto(userSave);
    }


}
