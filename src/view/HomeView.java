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


    public void showDetailRoleOfCustomer(Customer customer) {
        showMessage("Tên đăng nhập: " + customer.getUsername() + " - Vai trò: " + customer.getRoles()
                + " - Họ và tên: " + customer.getFullName());
    }

    public void showDetailRoleOfEmployee(Employee employee) {
        showMessage("Tên đăng nhập: " + employee.getUsername() + " - Vai trò: " + employee.getRoles()
                + " - Họ và tên: " + employee.getFullName());
    }

    public void showDetailMovie(Movie movie) {
        showMessage("ID phim: " + movie.getIdMovie() + " - Tên phim: " + movie.getNameMovie()
                + " - Thời lượng: " + movie.getDuration() + " phút");
        showMessage("Thể loại: " + movie.getGenreMovie());
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
//        showMessage("Các phòng chiếu: ");
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

    public void showCustomerInfo(Customer customer) {
        showMessage("Thông tin cá nhân");
        showMessage("Họ và tên: " + customer.getFullName());
        showMessage("Email: " + customer.getEmail());
        showMessage("Số điện thoại: " + customer.getPhoneNumber());
    }

    public void showEmployeeInfo(Employee employee) {
        showMessage("Thông tin cá nhân");
        showMessage("Họ và tên: " + employee.getFullName());
        showMessage("Email: " + employee.getEmail());
        showMessage("Số điện thoại: " + employee.getPhoneNumber());
        showMessage("Căn cước công dân: " + employee.getCitizen());
    }

}
