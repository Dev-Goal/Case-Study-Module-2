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
                if (values.length == 10) {
                    String idTicket = values[0];
                    String idMovie = values[1];
                    String idShowtime = values[2];
                    String idScreenRoom = values[3];
                    double price = Double.parseDouble(values[4]);
                    String typeTicket = values[5];
                    Set<String> numberSeats = new HashSet<>(Arrays.asList(values[6].split(";")));
                    LocalDateTime startTime = LocalDateTime.parse(values[7], formatter);
                    StatusTicket statusTicket = StatusTicket.valueOf(values[8]);
//                    Set<String> promotions = new HashSet<>();
//                    if (!values[9].isEmpty()) {
//                        String[] promotionArray = values[9].split(";");
//                        promotions.addAll(Arrays.asList(promotionArray));
//                    }
                    String buyerName = values[9];
                    Ticket ticket = new Ticket(idTicket, idMovie, idShowtime, idScreenRoom, price,
                            typeTicket, numberSeats, startTime, statusTicket, buyerName);
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
                String numberSeats = String.join(";", ticket.getNumberSeats());
//                String promotions = String.join(";", ticket.getPromotions());
                bufferedWriter.write(String.join(",",
                        ticket.getIdTicket(),
                        ticket.getIdMovie(),
                        ticket.getIdShowtime(),
                        ticket.getIdScreenRoom(),
                        String.valueOf(ticket.getPrice()),
                        ticket.getTypeTicket(),
                        numberSeats,
                        ticket.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy")),
                        ticket.getStatus().name(),
                        ticket.getBuyerName()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}