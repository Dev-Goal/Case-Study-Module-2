package csvutil;

import model.Showtime;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ShowtimeCSVUtil {
    public static Map<String, Showtime> readShowTimeFromCSV(String filePath) {
        Map<String, Showtime> showtimeData = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) {
                    String idShowtime = values[0];
                    String idMovie = values[1];
                    int duration = Integer.parseInt(values[2]);
                    LocalDateTime startTime = LocalDateTime.parse(values[3], formatter);
                    LocalDateTime endTime = LocalDateTime.parse(values[4], formatter);
                    String idScreenRoom = values[5];
                    int availableSeats = Integer.parseInt(values[6]);
                    Showtime showtime = new Showtime(idShowtime, idMovie, duration, startTime, endTime, idScreenRoom, availableSeats);
                    showtimeData.put(idShowtime, showtime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return showtimeData;
    }

    public static void writeShowtimeToCSV(Map<String, Showtime> showtimeData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Showtime showtime : showtimeData.values()) {
                bufferedWriter.write(String.join(",",
                        showtime.getIdShowtime(),
                        showtime.getIdMovie(),
                        String.valueOf(showtime.getDuration()),
                        showtime.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy")),
                        showtime.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy")),
                        showtime.getIdScreenRoom(),
                        String.valueOf(showtime.getAvailableSeats())));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
