package csvutil;

import model.ScreenRoom;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ScreenRoomCSVUtil {
    public static Map<String, ScreenRoom> readScreenRoomFromCSV(String filePath) {
        Map<String, ScreenRoom> screenRoomData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    String idScreenRoom = values[0];
                    String nameScreenRoom = values[1];
                    int totalSeats = Integer.parseInt(values[2]);
                    String idCinema = values[3];
                    ScreenRoom screenRoom = new ScreenRoom(idScreenRoom, nameScreenRoom, totalSeats, idCinema);
                    screenRoomData.put(idScreenRoom, screenRoom);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return screenRoomData;
    }

    public static void writeScreenRoomToCSV(Map<String, ScreenRoom> screenRoomData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for (ScreenRoom screenRoom : screenRoomData.values()) {
                bufferedWriter.write(String.join(",",
                        screenRoom.getIdScreenRoom(),
                        screenRoom.getNameScreenRoom(),
                        String.valueOf(screenRoom.getTotalSeats()),
                        screenRoom.getIdCinema()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
