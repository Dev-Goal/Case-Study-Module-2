package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Ticket {
    private String idTicket;
    private Showtime showtime;
    private double price;
    private String typeTicket;
    private String numberSeat;
    private LocalDateTime startTime;
    private String status;
    private Set<Promotion> promotions;

    public Ticket(String idTicket, Showtime showtime, double price, String typeTicket,
                  String numberSeat, LocalDateTime startTime, String status) {
        this.idTicket = idTicket;
        this.showtime = showtime;
        this.price = price;
        this.typeTicket = typeTicket;
        this.numberSeat = numberSeat;
        this.startTime = startTime;
        this.status = status;
        this.promotions = new HashSet<>();
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Promotion> getPromotionals() {
        return promotions;
    }

    public void setPromotionals(Set<Promotion> promotions) {
        this.promotions = promotions;
    }
}
