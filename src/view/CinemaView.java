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
        showMessage("ID phim: " + movie.getIdMovie() + " - Tên phim: " + movie.getNameMovie());
        showMessage("Thể loại: " + movie.getGenreMovie());
        showMessage("Thời lượng: " + movie.getDuration() + " phút");
        showMessage("Hình ảnh: " + movie.getImage());
        showMessage("Trailer: " + movie.getTrailer());
        showMessage("Mô tả: " + movie.getDesc());
    }

    public void showDetailScreenRoom(ScreenRoom screenRoom) {
        showMessage("ID phòng chiếu: " + screenRoom.getIdScreenRoom()
                + " - Tên phòng chiếu: " + screenRoom.getNameScreenRoom()
                + " - Số lượng ghế: " + screenRoom.getTotalSeats());
    }

    public void showDetailCinema(Cinema cinema) {
        System.out.println("Tên rạp chiếu: " + cinema.getNameCinema() + " - Số lượng phòng: " + cinema.getNumberOfScreenRoom());
    }

    public void showDetailPromotion(Promotion promotion) {
        System.out.println("Mã khuyến mãi: " + promotion.getCodePromotion());
        System.out.println("Tên khuyến mãi: " + promotion.getNamePromotion());
        System.out.println("Mô tả: " + promotion.getDesc());
        System.out.println("Giảm giá: " + promotion.getDiscountAmount());
        System.out.println("Số lượng: " + promotion.getAmount());
    }



}
