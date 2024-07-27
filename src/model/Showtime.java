package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Showtime {
    private String idShowtime;
    private Set<String> idMovie;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<String> nameScreenRoom;
    private int availableSeats;



    public Showtime(String idShowtime, String idMovie, int duration, LocalDateTime startTime, LocalDateTime endTime,
                    String nameScreenRoom, int availableSeats) {
        this.idShowtime = idShowtime;
        this.idMovie = new HashSet<>();
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nameScreenRoom = new HashSet<>();
        this.availableSeats = availableSeats;
    }

    public String getIdShowtime() {
        return idShowtime;
    }

    public Set<String> getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Set<String> idMovie) {
        this.idMovie = idMovie;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<String> getNameScreenRoom() {
        return nameScreenRoom;
    }

    public void setNameScreenRoom(Set<String> nameScreenRoom) {
        this.nameScreenRoom = nameScreenRoom;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void decreaseSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
        } else {
            throw new IllegalArgumentException("Đã hết ghế");
        }
    }

}
