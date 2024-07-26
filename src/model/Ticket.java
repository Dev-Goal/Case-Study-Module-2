package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Ticket {
    private String idTicket;
    private String idShowtime;
    private double price;
    private String typeTicket;
    private String numberSeat;
    private LocalDateTime startTime;
    private StatusTicket status;
    private Set<String> promotions;

    public Ticket(String idTicket, String idShowtime, double price, String typeTicket,
                  String numberSeat, LocalDateTime startTime, StatusTicket status, Set<String> promotions) {
        this.idTicket = idTicket;
        this.idShowtime = idShowtime;
        this.price = price;
        this.typeTicket = typeTicket;
        this.numberSeat = numberSeat;
        this.startTime = startTime;
        this.status = status;
        this.promotions = promotions;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(String idShowtime) {
        this.idShowtime = idShowtime;
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

    public Set<String> getPromotionals() {
        return promotions;
    }

    public void setPromotionals(Set<String> promotions) {
        this.promotions = promotions;
    }

}
