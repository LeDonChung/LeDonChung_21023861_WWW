package vn.edu.fit.student.donchung.frontend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fit.student.donchung.backend.enums.SkillLevel;
import vn.edu.fit.student.donchung.frontend.config.UserDetails;
import vn.edu.fit.student.donchung.frontend.dto.*;
import vn.edu.fit.student.donchung.frontend.models.SkillModel;
import vn.edu.fit.student.donchung.frontend.models.UserModel;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserModel userModel;

    @Autowired
    private SkillModel skillModel;
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

    @GetMapping("/personal")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String personal(
            Model model,
            Principal principal,
            @RequestParam(required = false) String action,
            @RequestParam(required = false, defaultValue = "0") Integer num

    ) {

        Authentication authentication = (Authentication) principal;

        Long candidateId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

        CandidateDto candidate = userModel.getByCandidateId(candidateId);

        if ("newSkill".equals(action)) {
            for (int i = 1; i <= num; i++) {
                CandidateSkillDto newCandidateSkillDto = new CandidateSkillDto();
                newCandidateSkillDto.setSkill(new SkillDto());
                newCandidateSkillDto.getSkill().setId((long) - i);
                candidate.getCandidateSkills().add(newCandidateSkillDto);
            }
        }

        List<SkillDto> skills = skillModel.getAll();
        List<SkillLevel> skillLevels = List.of(SkillLevel.values());

        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("skills", skills);
        model.addAttribute("candidate", candidate);
        model.addAttribute("num", num);
        return "personal";
    }


    @PostMapping("/personal")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String updatePersonal(
            @ModelAttribute("candidate") CandidateDto candidate,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        candidate = userModel.updateCandidate(candidate);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin cá nhân thành công.");
        return "redirect:/auth/personal";
    }
}
