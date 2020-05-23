package ua.test.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.test.hotel.model.Role;
import ua.test.hotel.model.User;
import ua.test.hotel.services.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration.html";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        Optional<User> userFromDB = userService.findByUsername(user.getUsername());
//todo optional
//        userFromDB.ifPresent();

        if (userFromDB.isPresent()) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.saveUser(user);

        return "redirect:/login";
    }
}
