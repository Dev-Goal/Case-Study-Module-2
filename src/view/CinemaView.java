package view;

import model.*;

import java.util.Scanner;

public class CinemaView {
    private Scanner scanner = new Scanner(System.in);

    public static void showMenuHome() {
        System.out.print("""
                Chào mừng đến với Cinema\s
                1. Đăng nhập\s
                2. Đăng ký nhân viên\s
                3. Đăng ký khách hàng\s
                4. Thoát
                """);
    }

    public String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showDetailRoleOfUser(User user) {
        System.out.println("Tên đăng nhập: " + user.getUsername() + " - Vai trò: " + user.getRoles()
                + " - Họ và tên: " + user.getFullName());
    }

    public void showDetailMovie(Movie movie) {
        System.out.println("Tên phim: " + movie.getNameMovie());
        System.out.println("Thể loại: " + movie.getGenreMovie());
        System.out.println("Thời lượng: " + movie.getDuration() + " phút");
        System.out.println("Hình ảnh: " + movie.getImage());
        System.out.println("Trailer: " + movie.getTrailer());
        System.out.println("Mô tả: " + movie.getDesc());
        System.out.println("Danh sách suất chiếu: " + movie.getShowtimes());
    }

    public void showDetailScreenRoom(ScreenRoom screenRoom) {
        System.out.println("Tên phòng chiếu: " + screenRoom.getNameScreenRoom());
        System.out.println("Số ghế: " + screenRoom.getTotalSeats());
        System.out.println("Danh sách suất chiếu: " + screenRoom.getShowtimes());
    }

    public void showDetailCinema(Cinema cinema) {
        System.out.println("Tên rạp chiếu: " + cinema.getNameCinema());
        System.out.println("Số phòng: " + cinema.getNumberOfScreenRoom());
        System.out.println("Danh sách phòng chiếu: " + cinema.getNameScreenRoom());
    }

    public void showDetailShowTime(Showtime showtime) {
        System.out.println("Tên phim: " + showtime.getIdMovie());
        System.out.println("Tên phòng chiếu: " + showtime.getIdScreenRoom());
        System.out.println("Thời lươợng: " + showtime.getDuration());
        System.out.println("Bắt đầu lúc: " + showtime.getStartTime());
        System.out.println("Kết thức lúc: " + showtime.getEndTime());
        System.out.println("Số ghế còn đặt được: " + showtime.getAvailableSeats());
    }


    public void showDetailPromotion(Promotion promotion) {
        System.out.println("Mã khuyến mãi: " + promotion.getCodePromotion());
        System.out.println("Tên khuyến mãi: " + promotion.getNamePromotion());
        System.out.println("Mô tả: " + promotion.getDesc());
        System.out.println("Giảm giá: " + promotion.getDiscountAmount());
        System.out.println("Số lượng: " + promotion.getAmount());
    }
}
