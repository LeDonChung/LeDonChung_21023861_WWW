package vn.edu.fit.student.donchung.frontend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fit.student.donchung.frontend.dto.UserDto;
import vn.edu.fit.student.donchung.frontend.dto.UserRegisterDto;
import vn.edu.fit.student.donchung.frontend.models.UserModel;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserModel userModel;
    @GetMapping("/login")
    public String login(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login";
    }



    @GetMapping("/register")
    public String register(Model model) {
        UserRegisterDto registerDTO = new UserRegisterDto();
        model.addAttribute("userRegister", registerDTO);
        return "register";
    }

    @PostMapping("/do-register")
    public String doRegister(@Valid @ModelAttribute("userRegister") UserRegisterDto userRegister, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // Validate form
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Đã xảy ra lỗi, vui lòng kiểm tra lại thông tin.");
            return "register";
        }

        // Validate password
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp.");
            return "register";
        }


        // Register user
        UserDto userDto = userModel.register(userRegister);
        redirectAttributes.addFlashAttribute("message", "Đăng ký tài khoản thành công.");
        return "redirect:/auth/login";
    }
}
