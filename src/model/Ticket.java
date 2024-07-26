package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Ticket {
    private int idTicket;
    private int idShowtime;
    private int idMovie;
    private int idScreenRoom;
    private double price;
    private String typeTicket;
    private String numberSeat;
    private LocalDateTime startTime;
    private StatusTicket status;
    private Set<String> promotions;

    public Ticket(int idTicket, int idShowtime, int idMovie, int idScreenRoom, double price, String typeTicket,
                  String numberSeat, LocalDateTime startTime, StatusTicket status, Set<String> promotions) {
        this.idTicket = idTicket;
        this.idShowtime = idShowtime;
        this.idMovie = idMovie;
        this.idScreenRoom = idScreenRoom;
        this.price = price;
        this.typeTicket = typeTicket;
        this.numberSeat = numberSeat;
        this.startTime = startTime;
        this.status = status;
        this.promotions = promotions;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(int idShowtime) {
        this.idShowtime = idShowtime;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getIdScreenRoom() {
        return idScreenRoom;
    }

    public void setIdScreenRoom(int idScreenRoom) {
        this.idScreenRoom = idScreenRoom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(String typeTicket) {
        this.typeTicket = typeTicket;
    }

    public String getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(String numberSeat) {
        this.numberSeat = numberSeat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public Set<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<String> promotions) {
        this.promotions = promotions;
    }
}
