package ua.test.hotel.repo;

import org.springframework.data.repository.CrudRepository;
import ua.test.hotel.model.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
