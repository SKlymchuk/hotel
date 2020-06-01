package ua.test.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.test.hotel.model.Application;
import ua.test.hotel.model.User;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    List<Application> findAllByUser(User user);

    List<Application> findAllByApprovedIsTrue();
}
