package ua.test.hotel.services;

import org.springframework.stereotype.Service;
import ua.test.hotel.model.Room;
import ua.test.hotel.model.RoomState;
import ua.test.hotel.model.Type;
import ua.test.hotel.repo.RoomRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepo roomRepo;

    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> findAllRooms() {
        return roomRepo.findAll();
    }

    public Optional<Room> findByType(Type type) {
        return roomRepo.findByType(type);
    }

    public Optional<Room> findById(Long id) {
        return roomRepo.findById(id);
    }

//    @PostConstruct
//    public void init(){
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(new Room(11, 2, 25.5, RoomState.AVAILABLE, Type.ECONOMY));
//        rooms.add(new Room(12, 3, 27.5, RoomState.AVAILABLE, Type.LUX));
//        rooms.add(new Room(13, 4, 28.5, RoomState.AVAILABLE, Type.STANDARD));
//        rooms.add(new Room(14, 5, 30.5, RoomState.AVAILABLE, Type.ECONOMY));
//        rooms.add(new Room(15, 6, 38.5, RoomState.AVAILABLE, Type.LUX));
//
//
//        roomRepo.saveAll(rooms);
//    }

    public List<Room> showAvailableRooms() {
        return roomRepo.findByRoomState(RoomState.AVAILABLE);
    }

    public void save(Room room) {
        roomRepo.save(room);
    }
}
