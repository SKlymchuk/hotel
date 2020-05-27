package ua.test.hotel.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.test.hotel.config.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userCandidate = userService.findByUsername(username);
//        if (userCandidate.isPresent()) {
//            return new UserDetailsImpl(userCandidate.get());
//        } else throw new IllegalArgumentException("User not found");
//    }
        return new
                UserDetailsImpl(userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user!")));
    }
}
