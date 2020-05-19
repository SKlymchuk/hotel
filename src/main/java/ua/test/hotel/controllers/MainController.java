package ua.test.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.test.hotel.model.Message;
import ua.test.hotel.repo.MessageRepo;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Model model)
    {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model)
    {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> filteredTag;
        if (filter != null && !filter.isEmpty()) {
            filteredTag = messageRepo.findByTag(filter);
        }
        else {
            filteredTag = messageRepo.findAll();
        }
        model.put("messages", filteredTag);
        return "main";
    }
}