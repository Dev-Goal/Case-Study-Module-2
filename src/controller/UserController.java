package controller;

import model.Role;
import model.User;
import view.CinemaView;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private final Map<String, User> userData = new HashMap<>();
    private final CinemaView cinemaView = new CinemaView();

    public UserController() {
        createAccountAdmin();
    }

    private void createAccountAdmin() {
        if (!userData.containsKey("admin")) {
            User admin = new User("admin", "admin123");
            userData.put("admin", admin);
        }
    }

    public void start() {
        do {
            cinemaView.showMenuHome();
            String choice = cinemaView.getInput("Sự lựa chọn của bạn là: ");
            switch (choice) {
                case "1" -> signIn();
                case "2" -> signUpEmployee();
                case "3" -> signUpCustomer();
                case "4" -> System.exit(0);
            }
        }while (true);
    }

    private void signUpCustomer() {
        String username = cinemaView.getInput("Tên đăng nhập: ");
        String password = cinemaView.getInput("Mật khẩu: ");
        String fullName = cinemaView.getInput("Họ và tên: ");
        String email = cinemaView.getInput("Email: ");
        String phoneNumber = cinemaView.getInput("Số điện thoại: ");
        if (userData.containsKey(username)) {
            cinemaView.showMessage("Tên đăng nhập đã tồn tại");
            return;
        }
        User newUser = new User(username, password, fullName, email, phoneNumber);
        userData.put(username, newUser);
        cinemaView.showMessage("Đăng ký khách hàng thành công");
    }

    private void signUpEmployee() {
        String username = cinemaView.getInput("Tên đăng nhập: ");
        String password = cinemaView.getInput("Mật khẩu: ");
        String fullName = cinemaView.getInput("Họ và tên: ");
        String email = cinemaView.getInput("Email: ");
        String phoneNumber = cinemaView.getInput("Số điện thoại: ");
        String citizen = cinemaView.getInput("CCCD: ");
        if (userData.containsKey(username)) {
            cinemaView.showMessage("Tên đăng nhập đã tồn tại");
            return;
        }
        User newUser = new User(username, password, fullName, email, phoneNumber, citizen);
        userData.put(username, newUser);
        cinemaView.showMessage("Đăng ký nhân viên thành công");
    }

    private void signIn() {
        String username = cinemaView.getInput("Tên đăng nhập: ");
        String password = cinemaView.getInput("Mật khẩu: ");
        User admin = userData.get(username);
        if (admin == null || !admin.getPassword().equals(password)) {
            cinemaView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
        }
        cinemaView.showMessage("Đăng nhập thành công");
        if (admin.getRoles().contains(Role.ROLE_ADMIN)) {
            showOptionalAdmin();
        }
    }

    private void showOptionalAdmin() {
        do {
            cinemaView.showMessage("1. Hiển thị danh sách khách hàng");
            cinemaView.showMessage("2. Hiển thị danh sách nhân viên");
            cinemaView.showMessage("3. Hiển thị danh sách phim");
            cinemaView.showMessage("4. Đăng xuất");

            String choice = cinemaView.getInput("Sự lựa chọn của bạn là: ");
            switch (choice) {
                case "1" -> showCustomerList();
                case "2" -> showEmployeeList();
                case "4" -> System.exit(0);
            }
        }while (true);
    }

    private void showCustomerList() {
        cinemaView.showMessage("Danh sách khách hàng: ");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_USER) || user.getRoles().contains(Role.ROLE_CUSTOMER)) {
                cinemaView.showDetailRoleOfUser(user);
            }
        }
    }

    private void showEmployeeList() {
        cinemaView.showMessage("Danh sách nhân viên: ");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_EMPLOYEE) || user.getRoles().contains(Role.ROLE_MANAGER)) {
                cinemaView.showDetailRoleOfUser(user);
            }
        }
    }

}
