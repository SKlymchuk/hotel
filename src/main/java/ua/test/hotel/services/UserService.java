package ua.test.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.test.hotel.model.User;
import ua.test.hotel.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
}
