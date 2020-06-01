package ua.test.hotel.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.test.hotel.model.Role;
import ua.test.hotel.model.User;
import ua.test.hotel.model.dto.UserDTO;
import ua.test.hotel.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void signUp(@NotNull UserDTO userDTO) {
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = User.builder()
                .username(userDTO.getUsername())
                .hashPassword(hashPassword)
                .active(true)
                .role(Role.USER)
                .build();

        saveUser(user);
    }

    public UserDTO toDTO(@NotNull User user) {
        return UserDTO.builder()
                .username(user.getUsername())
//                .cashAccount(user.getCashAccount())
                .build();
    }

}
