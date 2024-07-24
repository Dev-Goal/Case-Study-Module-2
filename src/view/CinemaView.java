package view;

import model.Movie;
import model.ScreenRoom;
import model.User;

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
    }

    public void showDetailScreenRoom(ScreenRoom screenRoom) {
        System.out.println("Tên phòng chiếu: " + screenRoom.getNameScreenRoom());
        System.out.println("Số ghế: " + screenRoom.getNumberOfSeats());
    }

}
