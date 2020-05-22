package ua.test.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.test.hotel.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
