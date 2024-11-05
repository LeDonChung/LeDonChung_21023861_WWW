package vn.edu.fit.student.donchung.frontend.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.fit.student.donchung.frontend.utils.RoleUtils;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        Authentication authentication = (Authentication) principal;
        if(authentication == null) {
            return "redirect:/login";
        }

        if(authentication.isAuthenticated()) {
            if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RoleUtils.ROLE_CANDIDATE))) {
                // Role CANDIDATE -> show job phù hợp với skill đang tuyển
            } else if(authentication.isAuthenticated()) {
                if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RoleUtils.ROLE_COMPANY))) {
                    // Role COMPANY -> show ứng viên phù hợp với các job đang tuyển
                }
            }
        } else {
            return "redirect:/login";
        }


        model.addAttribute("username", principal.getName());
        return "index";
    }
}
