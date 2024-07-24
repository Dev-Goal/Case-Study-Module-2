package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Showtime {
    private String nameMovie;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String nameCinema;
    private String nameScreenRoom;
    private int numberOfSeats;

    public Showtime(String nameMovie, int duration, LocalDateTime startTime,
                    String nameCinema, String nameScreenRoom, int numberOfSeats) {
        this.nameMovie = nameMovie;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(duration);
        this.nameCinema = nameCinema;
        this.nameScreenRoom = nameScreenRoom;
        this.numberOfSeats = numberOfSeats;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
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
}
