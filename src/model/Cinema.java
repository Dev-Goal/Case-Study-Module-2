package model;

import java.util.HashSet;
import java.util.Set;

public class Cinema {
    private String idCinema;
    private String nameCinema;
    private int numberOfScreenRoom;
    private Set<ScreenRoom> screenRooms;

    public Cinema(String idCinema, String nameCinema, int numberOfScreenRoom) {
        this.idCinema = idCinema;
        this.nameCinema = nameCinema;
        this.numberOfScreenRoom = numberOfScreenRoom;
        this.screenRooms = new HashSet<>();
    }

    public String getIdCinema() {
        return idCinema;
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

    public Set<ScreenRoom> getScreenRooms() {
        return screenRooms;
    }

    public void addScreenRoom(ScreenRoom screenRoom) {
        screenRooms.add(screenRoom);
    }
}
