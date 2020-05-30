package ua.test.hotel.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.test.hotel.config.UserDetailsImpl;
import ua.test.hotel.model.Application;
import ua.test.hotel.services.ApplicationService;
import ua.test.hotel.services.UserService;

import java.util.List;

@Controller
public class UserController {

    ApplicationService applicationService;
    UserService userService;

    public UserController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping("/statistics")
    public String userApplication(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Application> applicationList = applicationService.findAllByUser(userDetails.getUser());
        if (applicationList.isEmpty()) {
            model.addAttribute("message", "Empty");
        }
        model.addAttribute("applicationList", applicationList);

        return "user-stat";
    }

    @PostMapping("/statistics/{id}/del")
    public String deleteApplication(@PathVariable(value = "id") Long id, Authentication authentication) {
        //todo check if paid

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        applicationService.deleteById(id);

        return "redirect:/statistics";
    }
}
