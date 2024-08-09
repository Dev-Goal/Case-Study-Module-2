package controller;

import csvutil.*;
import model.*;
import service.CustomerService;
import service.HomeService;
import service.TicketService;
import view.HomeView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketController {
    private final HomeView homeView = new HomeView();
    private final HomeService homeService = new HomeService();
    private final CustomerService customerService = new CustomerService();
    private final MovieController movieController = new MovieController();
    private final TicketService ticketService = new TicketService();
    private static final String TICKET_FILE_PATH = "src/data/ticket.csv";
    private static final String MOVIE_FILE_PATH = "src/data/movie.csv";
    private static final String SCREENROOM_FILE_PATH = "src/data/screenroom.csv";
    private static final String SHOWTIME_FILE_PATH = "src/data/showtime.csv";
    private static final String PROMOTION_FILE_PATH = "src/data/promotion.csv";
    private static final String CUSTOMER_FILE_PATH = "src/data/customer.csv";
    private Map<String, Movie> movieData;
    private Map<String, ScreenRoom> screenRoomData;
    private Map<String, Showtime> showtimeData;
    private Map<String, Ticket> ticketData;
    private Map<String, Customer> customerData;
    private Map<String, Promotion> promotionData;

    private void loadData() {
        this.movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
        this.screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
        this.showtimeData = ShowtimeCSVUtil.readShowTimeFromCSV(SHOWTIME_FILE_PATH);
        this.ticketData = TicketCSVUtil.readTicketFromCSV(TICKET_FILE_PATH);
        this.promotionData = PromotionCSVUtil.readPromotionFromCSV(PROMOTION_FILE_PATH);
        this.customerData = CustomerCSVUtil.readCustomerFromCSV(CUSTOMER_FILE_PATH);
    }

    public void bookTicket() {
        loadData();
        homeView.showMessage("Đặt vé xem phim");
        movieController.showMovieList();
        String idTicket = String.valueOf((int) (Math.random() * 5000));
        String idMovie = homeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> !movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equals(id)),
                "Không có phim thuộc ID này");
        String idShowtime = homeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !showtimeData.values().stream().anyMatch(showtime -> showtime.getIdShowtime().equals(id)),
                "Không có suất chiếu thuộc ID này");
        Showtime showtime = showtimeData.get(idShowtime);
        if (showtime.getAvailableSeats() == 0) {
            homeView.showMessage("Suất chiếu đã hết vé");
            return;
        }
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equals(id)),
                "Không có phòng chiếu thuộc ID này");

        int numberOfSeats;
        while (true) {
            try {
                String input = homeView.getInput("Nhập số lượng ghế muốn đặt: ");
                numberOfSeats = Integer.parseInt(input);
                if (numberOfSeats <= 0) {
                    homeView.showMessage("Số lượng ghế phải lớn hơn 0.");
                    continue;
                }
                if (numberOfSeats > showtime.getAvailableSeats()) {
                    homeView.showMessage("Số lượng ghế vượt quá số ghế còn lại.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                homeView.showMessage("Không được để trống và Số lượng ghế là một số nguyên hợp lệ.");
            }
        }

        Set<String> bookedSeats = ticketData.values().stream()
                .filter(ticket -> ticket.getIdShowtime().equals(idShowtime))
                .flatMap(ticket -> ticket.getNumberSeats().stream())
                .collect(Collectors.toSet());

        homeView.showMessage("Các số ghế đã đặt cho suất chiếu " + idShowtime + ": " + bookedSeats.toString());

        Set<String> selectedSeats = new HashSet<>();
        for (int i = 0; i < numberOfSeats; i++) {
            String numberSeat;
            boolean seatAlreadyBooked;
            do {
                numberSeat = homeView.getInput("Số ghế " + (i + 1) + ": ");
                seatAlreadyBooked = bookedSeats.contains(numberSeat) || selectedSeats.contains(numberSeat);
                if (seatAlreadyBooked) {
                    homeView.showMessage("Ghế " + numberSeat + " đã được đặt, vui lòng chọn ghế khác.");
                }
            } while (seatAlreadyBooked);
            selectedSeats.add(numberSeat);
        }
        double price = 85.0;
        String typeTicked = homeView.getInput("Loại vé: ");
        StatusTicket statusTicket = StatusTicket.RESERVED;
        Set<String> codesPromotion = ticketService.getCodesPromotion();
        double totalDiscount = ticketService.calculateTotalDiscount(codesPromotion);
        double totalPrice = Math.max((price * numberOfSeats) - totalDiscount, 0);
        homeView.showMessage("Tổng số tiền: " + price * numberOfSeats + "00 VNĐ");
        homeView.showMessage("Tổng discount: " + totalDiscount + "00 VNĐ");
        homeView.showMessage("Tổng số tiền thanh toán: " + totalPrice + "00 VNĐ");

        String messagePay = homeView.getInput("Bạn có muốn thanh toán (Có/Không): ");
        if (messagePay.equalsIgnoreCase("Có")) {
            String username = homeView.getInput("Tên đăng nhập: ");
            Customer customer = customerData.get(username);
            customerService.updateUserRoleToCustomer(username);
            showtime.decreaseSeats(numberOfSeats);
            showtimeData.put(idShowtime, showtime);
            ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
            for (String code : codesPromotion) {
                Promotion promotion = promotionData.get(code);
                if (promotion != null) {
                    try {
                        promotion.decreaseAmountPromotion();
                    } catch (IllegalArgumentException e) {
                        homeView.showMessage(e.getMessage());
                    }
                }
            }
            PromotionCSVUtil.writePromotionToCSV(promotionData, PROMOTION_FILE_PATH);
            String buyerName = username;
            Ticket ticket = new Ticket(idTicket, idMovie, idShowtime, idScreenRoom, totalPrice, typeTicked, selectedSeats,
                    showtime.getStartTime(), statusTicket, buyerName);
            ticket.setStatus(StatusTicket.PAID);
            ticketData.put(ticket.getIdTicket(), ticket);
            TicketCSVUtil.writeTicketToCSV(ticketData, TICKET_FILE_PATH);
            homeView.showMessage("Đặt vé thành công");
            showTicketInfo(idTicket);
        } else {
            homeView.showMessage("Hủy đặt vé");
        }
    }

    private void showTicketInfo(String idTicket) {
        loadData();
        Ticket ticket = ticketData.get(idTicket);
        if (ticket == null) {
            homeView.showMessage("Vé không tồn tại.");
            return;
        }
        Showtime showtime = showtimeData.get(ticket.getIdShowtime());
        Movie movie = movieData.get(showtime.getIdMovie());
        String movieName = movie.getNameMovie();
        ScreenRoom screenRoom = screenRoomData.get(showtime.getIdScreenRoom());
        String screenRoomName = screenRoom.getNameScreenRoom();
        String numberSeats = String.join(", ", ticket.getNumberSeats());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        String startTime = showtime.getStartTime().format(formatter);
        homeView.showMessage("-------------------------------------");
        homeView.showMessage("|             Thông tin vé          |");
        homeView.showMessage("-------------------------------------");
        homeView.showMessage("|  Phim: " + movieName);
        homeView.showMessage("|  Phòng chiếu: " + screenRoomName);
        homeView.showMessage("|  Suất chiếu: " + startTime);
        homeView.showMessage("|  Số ghế: " + numberSeats);
        homeView.showMessage("-------------------------------------");
    }

    public void showTicket() {
        loadData();
        homeView.showMessage("Các vé đã đặt");
        if (ticketData.isEmpty()) {
            homeView.showMessage("Không có vé nào đã được đặt.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");

        for (Ticket ticket : ticketData.values()) {
            Showtime showtime = showtimeData.get(ticket.getIdShowtime());
            Movie movie = movieData.get(showtime.getIdMovie());
            String movieName = movie.getNameMovie();
            ScreenRoom screenRoom = screenRoomData.get(showtime.getIdScreenRoom());
            String screenRoomName = screenRoom.getNameScreenRoom();
            String numberSeats = String.join(", ", ticket.getNumberSeats());
            String startTime = showtime.getStartTime().format(formatter);
            String buyerName = ticket.getBuyerName();

            homeView.showMessage("-------------------------------------");
            homeView.showMessage("|             Thông tin vé          |");
            homeView.showMessage("-------------------------------------");
            homeView.showMessage("|  Phim: " + movieName);
            homeView.showMessage("|  Phòng chiếu: " + screenRoomName);
            homeView.showMessage("|  Suất chiếu: " + startTime);
            homeView.showMessage("|  Số ghế: " + numberSeats);
            homeView.showMessage("|  Giá vé: " + ticket.getPrice());
            homeView.showMessage("|  Trạng thái: " + ticket.getStatus());
            homeView.showMessage("|  Người mua: " + buyerName);
            homeView.showMessage("-------------------------------------");
        }
    }
}