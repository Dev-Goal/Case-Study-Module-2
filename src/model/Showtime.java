package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Showtime {
    private String idShowtime;
    private Set<String> nameMovie;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<String> nameCinema;
    private Set<String> nameScreenRoom;
    private int numberOfSeats;



    public Showtime(String idShowtime, String nameMovie, int duration, LocalDateTime startTime, LocalDateTime endTime,
                    String nameCinema, String nameScreenRoom, int numberOfSeats) {
        this.idShowtime = idShowtime;
        this.nameMovie = new HashSet<>();
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nameCinema = new HashSet<>();
        this.nameScreenRoom = new HashSet<>();
        this.numberOfSeats = numberOfSeats;
    }

    public String getIdShowtime() {
        return idShowtime;
    }

    public Set<String> getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(Set<String> nameMovie) {
        this.nameMovie = nameMovie;
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

    public Set<String> getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(Set<String> nameCinema) {
        this.nameCinema = nameCinema;
    }

    public Set<String> getNameScreenRoom() {
        return nameScreenRoom;
    }

    public void setNameScreenRoom(Set<String> nameScreenRoom) {
        this.nameScreenRoom = nameScreenRoom;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
