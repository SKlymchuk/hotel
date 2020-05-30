package ua.test.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.test.hotel.model.Application;
import ua.test.hotel.services.ApplicationService;

import java.util.List;

@Controller
public class AdminController {

    private final ApplicationService applicationService;

    public AdminController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public String getAllApplications(Model model) {
        List<Application> applicationListFromDB = applicationService.findAll();
        model.addAttribute("applicationListFromDB", applicationListFromDB);
        return "applications";
    }

}
