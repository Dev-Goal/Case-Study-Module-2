package model;

import java.util.HashSet;
import java.util.Set;

public class ScreenRoom {
    private String nameScreenRoom;
    private int numberOfSeats;
    private Set<Showtime> showtimes;

    public ScreenRoom(String nameScreenRoom, int numberOfSeats) {
        this.nameScreenRoom = nameScreenRoom;
        this.numberOfSeats = numberOfSeats;
        this.showtimes = new HashSet<>();
    }

    public String getNameScreenRoom() {
        return nameScreenRoom;
    }

    public void setNameScreenRoom(String nameScreenRoom) {
        this.nameScreenRoom = nameScreenRoom;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

}
