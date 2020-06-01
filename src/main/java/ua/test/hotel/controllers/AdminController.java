package ua.test.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.test.hotel.model.Application;
import ua.test.hotel.services.ApplicationService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final ApplicationService applicationService;

    public AdminController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public String getAllApplications(Model model) {
        List<Application> allApplicationListFromDB = applicationService.findAll();
        List<Application> applicationListFromDB = allApplicationListFromDB.stream()
                .filter(application -> !application.isApproved())
                .collect(Collectors.toList());
        model.addAttribute("applicationListFromDB", applicationListFromDB);
        return "applications";
    }

    @GetMapping("/applications/approved")
    public String getAllApprovedApplications(Model model) {
        model.addAttribute("applicationListFromDB", applicationService.showApprove());
        return "archive-applications";
    }

    @PostMapping("/applications/{id}/approve")
    public String approve(@PathVariable(value = "id") Long id, Model model) {
        applicationService.approveApplication(id);

        return "redirect:/applications";
    }

    @PostMapping("/applications/{id}/del")
    public String del(@PathVariable(value = "id") Long id, Model model) {

        applicationService.deleteApplicationAndFreeRoom(id);

        return "redirect:/applications";
    }


}
