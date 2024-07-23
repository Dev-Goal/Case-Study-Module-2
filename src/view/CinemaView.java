package view;

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
                4. Thoát""");
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

}
