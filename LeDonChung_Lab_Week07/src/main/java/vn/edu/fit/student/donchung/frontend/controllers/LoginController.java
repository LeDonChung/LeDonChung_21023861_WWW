package vn.edu.fit.student.donchung.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fit.student.donchung.backend.dtos.UserDto;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(value = {"/", ""})
    public String login(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login";
    }
}
