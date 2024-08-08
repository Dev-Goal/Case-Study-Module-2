package csvutil;

import model.Promotion;
import model.StatusTicket;
import model.Ticket;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TicketCSVUtil {
    public static Map<String, Ticket> readTicketFromCSV(String filePath) {
        Map<String, Ticket> ticketData = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 9) {
                    String idTicket = values[0];
                    String idMovie = values[1];
                    String idShowtime = values[2];
                    String idScreenRoom = values[3];
                    double price = Double.parseDouble(values[4]);
                    String typeTicket = values[5];
                    String numberSeat = values[6];
                    LocalDateTime startTime = LocalDateTime.parse(values[7], formatter);
                    StatusTicket statusTicket = StatusTicket.valueOf(values[8]);
//                    Set<String> promotions = new HashSet<>();
//                    if (!values[9].isEmpty()) {
//                        String[] promotionArray = values[9].split(";");
//                        promotions.addAll(Arrays.asList(promotionArray));
//                    }
                    Ticket ticket = new Ticket(idTicket, idMovie, idShowtime, idScreenRoom, price,
                            typeTicket, numberSeat, startTime, statusTicket);
                    ticketData.put(idTicket, ticket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketData;
    }

    public static void writeTicketToCSV(Map<String, Ticket> ticketData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Ticket ticket : ticketData.values()) {
//                String promotions = String.join(";", ticket.getPromotions());
                bufferedWriter.write(String.join(",",
                        ticket.getIdTicket(),
                        ticket.getIdMovie(),
                        ticket.getIdShowtime(),
                        ticket.getIdScreenRoom(),
                        String.valueOf(ticket.getPrice()),
                        ticket.getTypeTicket(),
                        ticket.getNumberSeat(),
                        ticket.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy")),
                        ticket.getStatus().name()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}