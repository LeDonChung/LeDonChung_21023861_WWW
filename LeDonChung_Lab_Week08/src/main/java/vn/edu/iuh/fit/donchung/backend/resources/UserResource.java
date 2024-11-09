package vn.edu.iuh.fit.donchung.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.backend.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<PageDto<UserDto>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(
                userService.getAll(page, size)
        );
    }
}
