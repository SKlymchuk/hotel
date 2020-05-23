package ua.test.hotel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;
    private int capacity;

    private Type type;

    public Room(int number, int capacity, Type type) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
    }
}
