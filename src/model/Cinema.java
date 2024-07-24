package model;

import java.util.HashSet;
import java.util.Set;

public class Cinema {
    private String nameCinema;
    private int numberOfScreenRoom;
    private Set<ScreenRoom> nameScreenRoom;
    private Set<Showtime> showtimes;

    public Cinema(String nameCinema, int numberOfScreenRoom) {
        this.nameCinema = nameCinema;
        this.numberOfScreenRoom = numberOfScreenRoom;
        this.showtimes = new HashSet<>();
        this.nameScreenRoom = new HashSet<>();
    }

    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    public int getNumberOfScreenRoom() {
        return numberOfScreenRoom;
    }

    public void setNumberOfScreenRoom(int numberOfScreenRoom) {
        this.numberOfScreenRoom = numberOfScreenRoom;
    }

    public Set<ScreenRoom> getNameScreenRoom() {
        return nameScreenRoom;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }
}
