package ua.test.hotel.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString(exclude = "applications")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String hashPassword;
    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Application> applications;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
