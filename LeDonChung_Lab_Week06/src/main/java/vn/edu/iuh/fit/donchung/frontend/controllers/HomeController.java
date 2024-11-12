package vn.edu.iuh.fit.donchung.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;
import vn.edu.iuh.fit.donchung.frontend.models.UserModel;

@Controller
public class HomeController {
    @Autowired
    private UserModel userModel;
    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String home(Model model, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size ) {

        PageDto<UserDto> users = userModel.getAllByPage(page, size);
        model.addAttribute("userPage", users);
        return "index";
    }
}
