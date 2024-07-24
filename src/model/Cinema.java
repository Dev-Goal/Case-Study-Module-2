package model;

public class Cinema {
    private String nameCinema;
    private int numberOfScreenRoom;

    public Cinema(String nameCinema, int numberOfScreenRoom) {
        this.nameCinema = nameCinema;
        this.numberOfScreenRoom = numberOfScreenRoom;
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
}
