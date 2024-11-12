package vn.edu.iuh.fit.donchung.backend.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.services.UserService;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserLoginRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRegisterRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.UserRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Operation(summary = "Lấy tất cả người dùng", description = "Lấy danh sách tất cả người dùng từ hệ thống", responses = {
            @ApiResponse(responseCode = "200", description = "Lấy người dùng thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ")
    })
    @GetMapping
    public ResponseEntity<PageDto<UserResponse>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(
                userService.getAll(page, size)
        );
    }

    @Operation(summary = "Đăng ký người dùng mới", description = "Đăng ký người dùng mới vào hệ thống", responses = {
            @ApiResponse(responseCode = "200", description = "Đăng ký thành công"),
            @ApiResponse(responseCode = "400", description = "Thông tin không hợp lệ")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest userDto) throws Exception {
        return ResponseEntity.ok(
                userService.register(userDto)
        );
    }

    @Operation(summary = "Đăng nhập người dùng", description = "Đăng nhập người dùng vào hệ thống", responses = {
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công"),
            @ApiResponse(responseCode = "400", description = "Thông tin không hợp lệ")
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest userDto) throws Exception {
        return ResponseEntity.ok(
                userService.login(userDto)
        );
    }

    @Operation(summary = "Cập nhật thông tin người dùng", description = "Cập nhật thông tin người dùng trong hệ thống", responses = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công"),
            @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest request) throws Exception {
        return ResponseEntity.ok(
                userService.update(request)
        );
    }
}