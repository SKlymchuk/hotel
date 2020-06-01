package ua.test.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private double price;

    public Room(int number, int capacity, double price, RoomState roomState, Type type) {
        this.capacity = capacity;
        this.number = number;
        this.price = price;
        this.roomState = roomState;
        this.type = type;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomState roomState;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

}
