package ua.test.hotel.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.test.hotel.config.UserDetailsImpl;
import ua.test.hotel.model.Application;
import ua.test.hotel.services.ApplicationService;
import ua.test.hotel.services.RoomService;
import ua.test.hotel.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    ApplicationService applicationService;
    UserService userService;
    RoomService roomService;

    public UserController(ApplicationService applicationService, UserService userService, RoomService roomService) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @GetMapping("/statistics")
    public String userApplication(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Application> allApplicationList = applicationService.findAllByUser(userDetails.getUser());
        if (allApplicationList.isEmpty()) {
            model.addAttribute("message", "Empty");
        }
        List<Application> applicationList = allApplicationList.stream()
                .filter(Application::isApproved)
                .collect(Collectors.toList());
        model.addAttribute("applicationList", applicationList);

        return "user-stat";
    }

//    @PostMapping("/statistics/{id}/del")
//    public String deleteApplication(@PathVariable(value = "id") Long id, Authentication authentication) {
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        if (applicationService.findById(id).get().getUser().equals(userDetails.getUser())) {
//            applicationService.deleteApplicationAndFreeRoom(id);
//        }
//        return "redirect:/statistics";
//    }
}
