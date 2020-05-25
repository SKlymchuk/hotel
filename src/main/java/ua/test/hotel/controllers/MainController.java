package ua.test.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.test.hotel.exceptions.NoEntityException;
import ua.test.hotel.model.Room;
import ua.test.hotel.repo.MessageRepo;
import ua.test.hotel.services.RoomService;

@Controller
public class MainController {


    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private RoomService roomService;

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
                    .orElseThrow(() -> new NoEntityException("No such room", id));

        } catch (NoEntityException ex) {
            return "redirect:/main";
        }
        return "room-reserve";
    }

//todo filter

//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model) {
//        if (filter != null && !filter.isEmpty()) {
//            List <Room> rooms = new ArrayList<>();
//            Optional<Room> typedRoom = roomService.findByType(Type.STANDARD);
//            model.addAttribute("roomsFromBD", Collections.singletonList());
//        } else {
//            model.addAttribute("roomsFromBD", roomService.findAllRooms());
//        }
//        return "main";
//    }
}