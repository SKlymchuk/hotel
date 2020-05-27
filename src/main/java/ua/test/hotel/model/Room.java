package ua.test.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Room(int number, int capacity, Type type) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
    }
}
