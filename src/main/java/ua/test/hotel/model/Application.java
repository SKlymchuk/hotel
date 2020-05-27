package ua.test.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Room room;

    @ManyToOne()
    private User user;

    private LocalDate fromDate;
    private LocalDate toDate;

    //todo price,


}
