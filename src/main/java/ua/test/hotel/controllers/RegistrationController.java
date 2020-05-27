package ua.test.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.test.hotel.model.User;
import ua.test.hotel.model.dto.UserDTO;
import ua.test.hotel.services.UserService;

import java.util.Optional;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDTO userDTO, Model model) {
        Optional<User> userFromDB = userService.findByUsername(userDTO.getUsername());

        if (userFromDB.isPresent()) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        userService.signUp(userDTO);

        return "redirect:/login";
    }
}
