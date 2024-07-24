package model;

public class ScreenRoom {
    private String nameScreenRoom;
    private int numberOfSeats;

    public ScreenRoom(String nameScreenRoom, int numberOfSeats) {
        this.nameScreenRoom = nameScreenRoom;
        this.numberOfSeats = numberOfSeats;
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
