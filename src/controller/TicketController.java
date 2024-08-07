package controller;

import csvutil.*;
import model.*;
import service.CustomerService;
import service.HomeService;
import service.TicketService;
import view.HomeView;

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
        try {
            numberOfSeats = Integer.parseInt(homeView.getInput("Nhập số lượng ghế muốn đặt: "));
        } catch (IllegalArgumentException e) {
            homeView.showMessage(e.getMessage());
            return;
        }
        double price = 85.0;
        String typeTicked = homeView.getInput("Loại vé: ");
        String numberSeat = homeView.getInput("Số ghế: ");
        Set<String> bookedSeats = ticketData.values().stream()
                .filter(ticket -> ticket.getIdShowtime().equals(idShowtime))
                .map(Ticket::getNumberSeat)
                .collect(Collectors.toSet());

        if (bookedSeats.contains(numberSeat)) {
            homeView.showMessage("Ghế đã được đặt");
            return;
        }
        StatusTicket statusTicket = StatusTicket.RESERVED;
        Set<String> codesPromotion = ticketService.getcodesPromotion();
        double totalDiscount = ticketService.calculateTotalDiscount(codesPromotion);
        double totalPrice = Math.max((price * numberOfSeats) - totalDiscount, 0);
        homeView.showMessage("Tổng số tiền thanh toán: " + totalPrice);
        Ticket ticket = new Ticket(idTicket, idMovie, idShowtime, idScreenRoom, totalPrice, typeTicked, numberSeat,
                showtime.getStartTime(), statusTicket, codesPromotion);
        String messagePay = homeView.getInput("Bạn có muốn thanh toán: ");
        if (messagePay.equalsIgnoreCase("Có")) {
            String username = homeView.getInput("Tên đăng nhập: ");
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
            ticket.setStatus(StatusTicket.PAID);
            ticketData.put(ticket.getIdTicket(), ticket);
            TicketCSVUtil.writeTicketToCSV(ticketData, TICKET_FILE_PATH);
            homeView.showMessage("Đặt vé thành công");
        } else {
            homeView.showMessage("Hủy đặt vé");
        }
    }
}
