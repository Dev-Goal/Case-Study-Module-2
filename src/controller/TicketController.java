package controller;

public class TicketController {
//    private HomeView cinemaView = new HomeView();
//    private HomeService cinemeService = new HomeService();
//    private Map<String, Ticket> ticketData = new HashMap<>();
//
//    private void bookTicket() {
//        cinemaView.showMessage("Đặt vé xem phim");
//        int idTicket = (int) (Math.random() * 500);
//        int idShowtime = Integer.parseInt(cinemaView.getInput("ID suất chiếu: "));
//        Showtime showtime = showtimeData.get(idShowtime);
//        if (showtime == null) {
//            cinemaView.showMessage("Suất chiếu không tồn tại");
//            return;
//        }
//        int idMovie = Integer.parseInt(cinemaView.getInput("ID phim: "));
//        Movie movie = movieData.get(idMovie);
//        if (movie == null) {
//            cinemaView.showMessage("Phim không tồn tại");
//            return;
//        }
//        int idScreenRoom = Integer.parseInt(cinemaView.getInput("ID phòng chiếu: "));
//        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
//        if (screenRoom == null) {
//            cinemaView.showMessage("Phòng chiếu không tồn tại");
//            return;
//        }
//        if (showtime.getAvailableSeats() <= 0) {
//            cinemaView.showMessage("Suất chiếu đã hết vé");
//            return;
//        }
//        int numberOfSeats = Integer.parseInt(cinemaView.getInput("Nhập số ghế muốn đặt: "));
//        try {
//            showtime.decreaseSeats(numberOfSeats);
//        } catch (IllegalArgumentException e) {
//            cinemaView.showMessage(e.getMessage());
//            return;
//        }
//        double price = 85.0;
//        String typeTicked = cinemaView.getInput("Loại vé: ");
//        String numberSeat = cinemaView.getInput("Số ghế: ");
//        if (ticketData.containsKey(numberSeat)) {
//            cinemaView.showMessage("Ghế đã được đặt");
//            return;
//        }
//        StatusTicket statusTicket = StatusTicket.RESERVED;
//        Set<String> promotions = new HashSet<>();
//        Set<String> codesPromotion = getcodesPromotion();
//        double totalDiscount = calculateTotalDiscount(codesPromotion);
//        double totalPrice = Math.max(price - totalDiscount, 0);
//        cinemaView.showMessage("Tổng số tiền thanh toán: " + totalPrice);
//        Ticket ticket = new Ticket(idTicket, idScreenRoom, idMovie, idScreenRoom, totalPrice, typeTicked, numberSeat,
//                showtime.getStartTime(), statusTicket, promotions);
//        String messagePay = cinemaView.getInput("Bạn có muốn thanh toán: ");
//        if (messagePay.equalsIgnoreCase("Có")) {
//            ticket.setStatus(StatusTicket.PAID);
//        }
//        ticketData.put(ticket.getIdTicket(), ticket);
//        cinemaView.showMessage("Đặt vé thành công");
//    }
//
//    private Set<String> getcodesPromotion() {
//        Set<String> codesPromotion = new HashSet<>();
//        String addMorePromotions = cinemaView.getInput("Có muốn thêm mã khuyến mãi không: ");
//        while (addMorePromotions.equalsIgnoreCase("Có")) {
//            String codePromotion = cinemaView.getInput("Nhập mã khuyến mãi: ");
//            if (promotionData.containsKey(codePromotion) && promotionData.get(codePromotion).getAmount() > 0) {
//                codesPromotion.add(codePromotion);
//                promotionData.get(codePromotion).decreaseAmountPromotion();
//                cinemaView.showMessage("Thêm mã khuyến mãi thành công.");
//            } else {
//                cinemaView.showMessage("Mã khuyến mãi không hợp lệ hoặc số lượng đã hết");
//            }
//            addMorePromotions = cinemaView.getInput("Có muốn thêm mã khuyến mãi nữa không: ");
//        }
//        return codesPromotion;
//    }
//
//    private double calculateTotalDiscount(Set<String> codesPromotion) {
//        double totalDiscount = 0.0;
//        for (String code : codesPromotion) {
//            Promotion promotion = promotionData.get(code);
//            if (promotion != null) {
//                totalDiscount += promotion.getDiscountAmount();
//            }
//        }
//        return totalDiscount;
//    }
}
