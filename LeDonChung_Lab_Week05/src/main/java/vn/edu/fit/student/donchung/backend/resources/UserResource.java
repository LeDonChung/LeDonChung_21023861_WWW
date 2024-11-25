package vn.edu.fit.student.donchung.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fit.student.donchung.backend.dtos.UserDto;
import vn.edu.fit.student.donchung.backend.dtos.UserRegisterDto;
import vn.edu.fit.student.donchung.backend.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;


    @GetMapping("/loadByUsername")
    public ResponseEntity<UserDto> loadByUsername(String username) {
        return ResponseEntity.ok(
                userService.loadByUsername(username)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto userDto) {
        return ResponseEntity.ok(
                userService.register(userDto)
        );
    }
}
