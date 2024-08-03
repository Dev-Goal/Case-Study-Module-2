package view;

import model.*;

import java.util.Scanner;

public class HomeView {
    private Scanner scanner = new Scanner(System.in);

    public void showMenuHome() {
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
        showMessage("Tên đăng nhập: " + user.getUsername() + " - Vai trò: " + user.getRoles()
                + " - Họ và tên: " + user.getFullName());
    }

    public void showDetailMovie(Movie movie) {
        showMessage("ID phim: " + movie.getIdMovie() + " - Tên phim: " + movie.getNameMovie()
                + " - Thời lượng: " + movie.getDuration() + " phút");
        showMessage("Thể loại: " + movie.getGenreMovie());
        showMessage("Hình ảnh: " + movie.getImage());
        showMessage("Trailer: " + movie.getTrailer());
        showMessage("Mô tả: " + movie.getDesc());
    }

    public void showDetailScreenRoom(ScreenRoom screenRoom, String cinemaName) {
        showMessage("ID phòng chiếu: " + screenRoom.getIdScreenRoom()
                + " - Tên phòng chiếu: " + screenRoom.getNameScreenRoom()
                + " - Số lượng ghế: " + screenRoom.getTotalSeats()
                + " - Tên rạp chiếu: " + cinemaName);
    }

    public void showDetailCinema(Cinema cinema) {
        showMessage("ID rạp chiếu phim: " + cinema.getIdCinema() + " - Tên rạp chiếu phim: " + cinema.getNameCinema()
                + " - Số lượng phòng: " + cinema.getNumberOfScreenRoom());
        showMessage("Các phòng chiếu: ");
        for (ScreenRoom screenRoom : cinema.getScreenRooms()) {
            showMessage("ID phòng chiếu: " + screenRoom.getIdScreenRoom()
                    + " - Tên phòng chiếu: " + screenRoom.getNameScreenRoom());
        }
    }

    public void showDetailPromotion(Promotion promotion) {
        showMessage("Mã khuyến mãi: " + promotion.getCodePromotion() + " - Tên khuyến mãi: " + promotion.getNamePromotion()
                + " - Số lượng: " + promotion.getAmount());
        showMessage("Mô tả: " + promotion.getDesc() + " - Giảm giá: " + promotion.getDiscountAmount());
    }



}
