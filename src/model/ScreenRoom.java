package model;

import java.util.HashSet;
import java.util.Set;

public class ScreenRoom {
    private String idScreenRoom;
    private String nameScreenRoom;
    private int totalSeats;
    private Set<Showtime> showtimes;

    public ScreenRoom(String idScreenRoom, String nameScreenRoom, int totalSeats) {
        this.idScreenRoom = idScreenRoom;
        this.nameScreenRoom = nameScreenRoom;
        this.totalSeats = totalSeats;
        this.showtimes = new HashSet<>();
    }

    public String getIdScreenRoom() {
        return idScreenRoom;
    }

    public String getNameScreenRoom() {
        return nameScreenRoom;
    }

    public void setNameScreenRoom(String nameScreenRoom) {
        this.nameScreenRoom = nameScreenRoom;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }
}
