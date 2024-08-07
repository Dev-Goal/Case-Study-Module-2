package model;

import java.time.LocalDateTime;

public class Showtime {
    private String idShowtime;
    private String idMovie;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String idScreenRoom;
    private int availableSeats;

    public Showtime(String idShowtime, String idMovie, int duration, LocalDateTime startTime,
                    LocalDateTime endTime, String idScreenRoom, int availableSeats) {
        this.idShowtime = idShowtime;
        this.idMovie = idMovie;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(duration);
        this.idScreenRoom = idScreenRoom;
        this.availableSeats = availableSeats;
    }

    public String getIdShowtime() {
        return idShowtime;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
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

    public String getIdScreenRoom() {
        return idScreenRoom;
    }

    public void setIdScreenRoom(String idScreenRoom) {
        this.idScreenRoom = idScreenRoom;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void decreaseSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Vui lòng chọn số lượng ghế");
        }
        if (numberOfSeats > availableSeats) {
            throw new IllegalArgumentException("Số lượng ghế yêu cầu vượt quá số ghế còn lại.");
        }
        this.availableSeats -= numberOfSeats;
    }

}
