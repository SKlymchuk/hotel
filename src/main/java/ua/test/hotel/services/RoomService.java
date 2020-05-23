package ua.test.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.test.hotel.repo.RoomRepo;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

//    @PostConstruct
//    public void init(){
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(new Room(11, 2, Type.ECONOMY));
//        rooms.add(new Room(12, 3, Type.STANDARD));
//        rooms.add(new Room(13, 4, Type.LUX));
//        rooms.add(new Room(14, 1, Type.ECONOMY));
//
//
//        roomRepo.saveAll(rooms);
//    }
}
