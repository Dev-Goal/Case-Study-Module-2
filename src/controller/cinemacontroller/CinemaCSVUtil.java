package controller.cinemacontroller;

import model.Cinema;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CinemaCSVUtil {
    public static Map<String, Cinema> readCinemaFromCSV(String filePath) {
        Map<String, Cinema> cinemaData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String idCinema = values[0];
                    String nameCinema = values[1];
                    int numberOfScreenRoom = Integer.parseInt(values[2]);
                    Cinema cinema = new Cinema(idCinema, nameCinema, numberOfScreenRoom);
                    cinemaData.put(idCinema, cinema);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cinemaData;
    }

    public static void writeCinemaToCSV(Map<String, Cinema> cinemaData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for (Cinema cinema : cinemaData.values()) {
                bufferedWriter.write(String.join(",",
                        cinema.getIdCinema(),
                        cinema.getNameCinema(),
                        String.valueOf(cinema.getNumberOfScreenRoom())));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
