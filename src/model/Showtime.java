package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Showtime {
    private int idShowtime;
    private Set<Integer> idMovie;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Integer> idScreenRoom;
    private int availableSeats;



    public Showtime(int idShowtime, Integer idMovie, int duration, LocalDateTime startTime, LocalDateTime endTime,
                    String idScreenRoom, int availableSeats) {
        this.idShowtime = idShowtime;
        this.idMovie = new HashSet<>();
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idScreenRoom = new HashSet<>();
        this.availableSeats = availableSeats;
    }

    public int getIdShowtime() {
        return idShowtime;
    }

    public Set<Integer> getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Set<Integer> idMovie) {
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

    public Set<Integer> getIdScreenRoom() {
        return idScreenRoom;
    }

    public void setIdScreenRoom(Set<Integer> idScreenRoom) {
        this.idScreenRoom = idScreenRoom;
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
