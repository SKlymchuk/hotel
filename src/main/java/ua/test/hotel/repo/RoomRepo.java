package ua.test.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.test.hotel.model.Room;
import ua.test.hotel.model.RoomState;
import ua.test.hotel.model.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByType(Type type);

    List<Room> findByRoomState(RoomState available);
}
