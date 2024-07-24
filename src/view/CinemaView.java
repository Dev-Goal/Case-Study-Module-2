package view;

import model.*;

import java.util.Scanner;

public class CinemaView {
    private final Scanner scanner = new Scanner(System.in);

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

    public void showDetailRoleOfUser(User user) {
        System.out.println("Tên đăng nhập: " + user.getUsername());
        System.out.println("Vai trò: " + user.getRoles());
    }

    public void showMessage(String message) {
        System.out.println(message);
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
        System.out.println("Số ghế: " + screenRoom.getNumberOfSeats());
        System.out.println("Danh sách suất chiếu: " + screenRoom.getShowtimes());
    }

    public void showDetailCinema(Cinema cinema) {
        System.out.println("Tên rạp chiếu: " + cinema.getNameCinema());
        System.out.println("Số phòng: " + cinema.getNumberOfScreenRoom());
        System.out.println("Danh sách phòng chiếu: " + cinema.getNameScreenRoom());
        System.out.println("Danh sách suất chiếu" + cinema.getShowtimes());
    }

    public void showDetailShowTime(Showtime showtime) {
        System.out.println("Tên phim: " + showtime.getNameMovie());
        System.out.println("Tên rạp: " + showtime.getNameCinema());
        System.out.println("Tên phòng chiếu: " + showtime.getNameScreenRoom());
        System.out.println("Thời lươợng: " + showtime.getDuration());
        System.out.println("Bắt đầu lúc: " + showtime.getStartTime());
        System.out.println("Kết thức lúc: " + showtime.getEndTime());
        System.out.println("Số ghế: " + showtime.getNumberOfSeats());
    }
}
