package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.UserMapper;
import vn.edu.fit.student.donchung.backend.dtos.*;
import vn.edu.fit.student.donchung.backend.entities.Candidate;
import vn.edu.fit.student.donchung.backend.entities.Company;
import vn.edu.fit.student.donchung.backend.entities.Role;
import vn.edu.fit.student.donchung.backend.entities.User;
import vn.edu.fit.student.donchung.backend.repositories.RoleRepository;
import vn.edu.fit.student.donchung.backend.repositories.UserRepository;
import vn.edu.fit.student.donchung.backend.services.UserService;
import vn.edu.fit.student.donchung.frontend.utils.RoleUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto loadByUsername(String username) {
        return userMapper.toDto(
                userRepository.findByUsername(username)
        );
    }

    @Override
    public UserDto register(UserRegisterDto userRegisterDTO) {

        User user = new User();
        List<Role> roles = new ArrayList<>();
        if(userRegisterDTO.isCompany()) {
            user = new Company();
            ((Company) user).setEmail(userRegisterDTO.getEmail());
            ((Company) user).setCompName(userRegisterDTO.getFullName());
            roles.add(
                    roleRepository.findByCode(RoleUtils.ROLE_COMPANY).get()
            );
        } else {
            user = new Candidate();
            ((Candidate) user).setEmail(userRegisterDTO.getEmail());
            ((Candidate) user).setFullName(userRegisterDTO.getFullName());
            roles.add(
                    roleRepository.findByCode(RoleUtils.ROLE_COMPANY).get()
            );
        }

        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user =  userRepository.save(user);

        return userMapper.toDto(user);
    }
}
