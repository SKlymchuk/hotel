package ua.test.hotel.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.test.hotel.config.UserDetailsImpl;
import ua.test.hotel.exceptions.NoSuchEntityException;
import ua.test.hotel.model.Room;
import ua.test.hotel.model.dto.ApplicationDTO;
import ua.test.hotel.services.ApplicationService;
import ua.test.hotel.services.RoomService;

import java.util.Optional;

@Controller
public class RoomController {


    private final RoomService roomService;
    private final ApplicationService applicationService;

    public RoomController(RoomService roomService, ApplicationService applicationService) {
        this.roomService = roomService;
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Room> rooms = roomService.findAllRooms();
        model.addAttribute("roomsFromBD", rooms);
        return "main";
    }

    @GetMapping("/room/{id}")
    public String roomDetails(@PathVariable(value = "id") long id, Model model) {
        try {
            roomService.findById(id).map(room -> model.addAttribute("room", room))
                    .orElseThrow(() -> new NoSuchEntityException("No such room", id));

        } catch (NoSuchEntityException ex) {
            return "redirect:/main";
        }
        return "room-reserve";
    }

    @PostMapping("/room/{id}")
    public String saveApplication(@PathVariable(value = "id") Long id,
                                  ApplicationDTO applicationDTO,
                                  Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Room> room = roomService.findById(id);
        room.ifPresent(value -> applicationService.saveApplicationFromDTO(applicationDTO, value, userDetails.getUser()));
        return "redirect:/main";
    }

/*
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        if (filter != null && !filter.isEmpty()) {
            List <Room> rooms = new ArrayList<>();
            Optional<Room> typedRoom = roomService.findByType(Type.STANDARD);
            model.addAttribute("roomsFromBD", Collections.singletonList());
        } else {
            model.addAttribute("roomsFromBD", roomService.findAllRooms());
        }
        return "main";
    }
*/
}