package vn.edu.fit.student.donchung.frontend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.edu.fit.student.donchung.frontend.dto.UserDto;
import vn.edu.fit.student.donchung.frontend.models.UserModel;

public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserModel userModel;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userModel.loadByUsername(username);
        if(userDto == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(userDto);
        return new vn.edu.fit.student.donchung.frontend.config.UserDetails(userDto);

    }
}
