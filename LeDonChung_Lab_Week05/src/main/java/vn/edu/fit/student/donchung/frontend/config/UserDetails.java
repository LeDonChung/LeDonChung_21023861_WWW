package vn.edu.fit.student.donchung.frontend.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.edu.fit.student.donchung.backend.dtos.RoleDto;
import vn.edu.fit.student.donchung.backend.dtos.UserDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final UserDto userDto;

    public UserDetails(UserDto userDto) {
        this.userDto = userDto;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(RoleDto role : userDto.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }
    public UserDto getUser() {
        return userDto;
    }
}
