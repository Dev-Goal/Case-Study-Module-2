package controller;

import csvutil.*;
import model.*;
import service.HomeService;
import view.HomeView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private MovieController movieController = new MovieController();
    private static final String TICKET_FILE_PATH = "src/data/ticket.csv";
    private static final String MOVIE_FILE_PATH = "src/data/movie.csv";
    private static final String SCREENROOM_FILE_PATH = "src/data/screenroom.csv";
    private static final String SHOWTIME_FILE_PATH = "src/data/showtime.csv";
    private static final String PROMOTION_FILE_PATH = "src/data/promotion.csv";
    private Map<String, Movie> movieData;
    private Map<String, ScreenRoom> screenRoomData;
    private Map<String, Showtime> showtimeData;
    private Map<String, Ticket> ticketData;
    private Map<String, Promotion> promotionData;


    private void loadData() {
        this.movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
        this.screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
        this.showtimeData = ShowtimeCSVUtil.readShowTimeFromCSV(SHOWTIME_FILE_PATH);
        this.ticketData = TicketCSVUtil.readTicketFromCSV(TICKET_FILE_PATH);
        this.promotionData = PromotionCSVUtil.readPromotionFromCSV(PROMOTION_FILE_PATH);
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

        int numberOfSeats = Integer.parseInt(homeView.getInput("Nhập số lượng ghế muốn đặt: "));
        try {
            showtime.decreaseSeats(numberOfSeats);
        } catch (IllegalArgumentException e) {
            homeView.showMessage(e.getMessage());
            return;
        }
        double price = 85.0;
        String typeTicked = homeView.getInput("Loại vé: ");
        String numberSeat = homeView.getInput("Số ghế: ");
        if (ticketData.containsKey(numberSeat)) {
            homeView.showMessage("Ghế đã được đặt");
            return;
        }
        StatusTicket statusTicket = StatusTicket.RESERVED;
        Set<String> promotions = new HashSet<>();
        Set<String> codesPromotion = getcodesPromotion();
        double totalDiscount = calculateTotalDiscount(codesPromotion);
        double totalPrice = Math.max(price - totalDiscount, 0);
        homeView.showMessage("Tổng số tiền thanh toán: " + totalPrice);
        Ticket ticket = new Ticket(idTicket, idMovie, idScreenRoom, idScreenRoom, totalPrice, typeTicked, numberSeat,
                showtime.getStartTime(), statusTicket, promotions);
        String messagePay = homeView.getInput("Bạn có muốn thanh toán: ");
        if (messagePay.equalsIgnoreCase("Có")) {
            ticket.setStatus(StatusTicket.PAID);
        }
        ticketData.put(ticket.getIdTicket(), ticket);
        homeView.showMessage("Đặt vé thành công");
    }

    private Set<String> getcodesPromotion() {
        loadData();
        Set<String> codesPromotion = new HashSet<>();
        String addMorePromotions = homeView.getInput("Có muốn thêm mã khuyến mãi không: ");
        while (addMorePromotions.equalsIgnoreCase("Có")) {
            String codePromotion = homeView.getInput("Nhập mã khuyến mãi: ");
            if (promotionData.containsKey(codePromotion) && promotionData.get(codePromotion).getAmount() > 0) {
                codesPromotion.add(codePromotion);
                promotionData.get(codePromotion).decreaseAmountPromotion();
                homeView.showMessage("Thêm mã khuyến mãi thành công.");
            } else {
                homeView.showMessage("Mã khuyến mãi không hợp lệ hoặc số lượng đã hết");
            }
            addMorePromotions = homeView.getInput("Có muốn thêm mã khuyến mãi nữa không: ");
        }
        return codesPromotion;
    }

    private double calculateTotalDiscount(Set<String> codesPromotion) {
        loadData();
        double totalDiscount = 0.0;
        for (String code : codesPromotion) {
            Promotion promotion = promotionData.get(code);
            if (promotion != null) {
                totalDiscount += promotion.getDiscountAmount();
            }
        }
        return totalDiscount;
    }
}
