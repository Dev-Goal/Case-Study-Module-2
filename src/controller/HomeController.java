package controller;

import model.*;
import view.HomeView;

import java.util.*;

public class HomeController {
    private Map<String, User> userData = new HashMap<>();

    private HomeView homeView = new HomeView();
    private MovieController movieController = new MovieController();
    private CinemaController cinemaController = new CinemaController();
    private ScreenRoomController screenRoomController = new ScreenRoomController();
    private ShowtimeController showtimeController = new ShowtimeController();
    private PromotionController promotionController = new PromotionController();

    public HomeController() {
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
            homeView.showMenuHome();
            String choice = homeView.getInput("Sự lựa chọn của bạn là: ");
            switch (choice) {
                case "1" -> signIn();
                case "2" -> signUpEmployee();
                case "3" -> signUpCustomer();
                case "4" -> System.exit(0);
            }
        }while (true);
    }

    private void signUpCustomer() {
        String username = homeView.getInput("Tên đăng nhập: ");
        if (userData.containsKey(username) && userData.containsValue(Role.ROLE_USER)) {
            homeView.showMessage("Tên đăng nhập đã được sử dụng");
            return;
        }
        String password = homeView.getInput("Mật khẩu: ");
        String fullName = homeView.getInput("Họ và tên: ");
        String email = homeView.getInput("Email: ");
        String phoneNumber = homeView.getInput("Số điện thoại: ");

        User newUser = new User(username, password, fullName, email, phoneNumber);
        userData.put(username, newUser);
        homeView.showMessage("Đăng ký khách hàng thành công");
    }

    private void signUpEmployee() {
        String username = homeView.getInput("Tên đăng nhập: ");
        if (userData.containsKey(username) && userData.containsValue(Role.ROLE_EMPLOYEE)) {
            homeView.showMessage("Tên đăng nhập đã được sử dụng");
            return;
        }
        String password = homeView.getInput("Mật khẩu: ");
        String fullName = homeView.getInput("Họ và tên: ");
        String email = homeView.getInput("Email: ");
        String phoneNumber = homeView.getInput("Số điện thoại: ");
        String citizen = homeView.getInput("CCCD: ");
        User newUser = new User(username, password, fullName, email, phoneNumber, citizen);
        userData.put(username, newUser);
        homeView.showMessage("Đăng ký nhân viên thành công");
    }

    private void signIn() {
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        User user = userData.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        homeView.showMessage("Đăng nhập thành công");
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            showOptionalAdmin();
        }
    }

    public void showOptionalAdmin() {
        do {
            homeView.showMessage("1. Khách hàng");
            homeView.showMessage("2. Nhân viên");
            homeView.showMessage("3. Phim");
            homeView.showMessage("4. Phòng chiếu");
            homeView.showMessage("5. Rạp chiếu");
            homeView.showMessage("6. Suất chiếu");
            homeView.showMessage("7. Khuyến mãi");
            homeView.showMessage("8. Đặt vé");
            homeView.showMessage("10. Đăng xuất");

            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> showOptionalCustomer();
                case "2" -> showOptionalEmployee();
                case "3" -> showOptionalMovie();
                case "4" -> showOptionalScreenRoom();
                case "5" -> showOptionalCinema();
                case "6" -> showOptionalShowtime();
                case "7" -> showOptionalPromotion();
//                case "8" -> bookTicket();
                case "10" -> {return;}
            }
        }while (true);
    }

    private void showOptionalCustomer() {
        homeView.showMessage("Khách hàng");
        do {
            homeView.showMessage("1. Hiển thị danh sách khách hàng");
            homeView.showMessage("2. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> showCustomerList();
                case "2" -> {return;}
            }
        }while (true);
    }

    private void showOptionalEmployee() {
        homeView.showMessage("Nhân viên/Quản lý");
        do {
            homeView.showMessage("1. Hiển thị danh sách nhân viên");
            homeView.showMessage("2. Thăng cấp cho nhân viên");
            homeView.showMessage("3. Giáng chức quản lý");
            homeView.showMessage("4. Xóa nhân viên");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> showEmployeeList();
                case "2" -> promoteEmployee();
                case "3" -> demoteEmployee();
                case "4" -> deleteEmployee();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showOptionalMovie() {
        homeView.showMessage("Phim");
        do {
            homeView.showMessage("1. Hiển thị danh sách phim");
            homeView.showMessage("2. Thêm phim");
            homeView.showMessage("3. Chỉnh sửa phim");
            homeView.showMessage("4. Xóa phim");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> movieController.showMovieList();
                case "2" -> movieController.addMovie();
                case "3" -> movieController.editMovie();
                case "4" -> movieController.deleteMovie();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showOptionalScreenRoom() {
        homeView.showMessage("Phòng chiếu");
        do {
            homeView.showMessage("1. Hiển thị danh sách phòng chiếu");
            homeView.showMessage("2. Thêm phòng chiếu");
            homeView.showMessage("3. Chỉnh sửa phòng chiếu");
            homeView.showMessage("4. Xóa phòng chiếu");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> screenRoomController.showScreenRoomList();
                case "2" -> screenRoomController.addScreenRoom();
                case "3" -> screenRoomController.editScreenRoom();
                case "4" -> screenRoomController.deleteScreenRoom();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showOptionalCinema() {
        homeView.showMessage("Rạp chiếu");
        do {
            homeView.showMessage("1. Hiển thị danh sách rạp chiếu");
            homeView.showMessage("2. Thêm rạp chiếu");
            homeView.showMessage("3. Chỉnh sửa rạp chiếu");
            homeView.showMessage("4. Xóa rạp chiếu");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> cinemaController.showCinemaList();
                case "2" -> cinemaController.addCinema();
                case "3" -> cinemaController.editCinema();
                case "4" -> cinemaController.deleteCinema();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showOptionalShowtime() {
        homeView.showMessage("Suất chiếu");
        do {
            homeView.showMessage("1. Hiển thị danh sách suất chiếu");
            homeView.showMessage("2. Thêm suất chiếu");
            homeView.showMessage("3. Chỉnh sửa suất chiếu");
            homeView.showMessage("4. Xóa suất chiếu");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> showtimeController.showListShowTimes();
                case "2" -> showtimeController.addShowTimes();
                case "3" -> showtimeController.editShowTimes();
                case "4" -> showtimeController.deleteShowTimes();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showOptionalPromotion() {
        homeView.showMessage("Khuyến mãi");
        do {
            homeView.showMessage("1. Hiển thị danh sách khuyến mãi");
            homeView.showMessage("2. Thêm khuyến mãi");
            homeView.showMessage("3. Chỉnh sửa khuyến mãi");
            homeView.showMessage("4. Xóa khuyến mãi");
            homeView.showMessage("5. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> promotionController.showPromotionList();
                case "2" -> promotionController.addPromotion();
                case "3" -> promotionController.editPromotion();
                case "4" -> promotionController.deletePromotion();
                case "5" -> {return;}
            }
        }while (true);
    }

    private void showCustomerList() {
        homeView.showMessage("Danh sách khách hàng");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_USER) || user.getRoles().contains(Role.ROLE_CUSTOMER)) {
                homeView.showDetailRoleOfUser(user);
            }
        }
    }

    private void showEmployeeList() {
        homeView.showMessage("Danh sách nhân viên");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_EMPLOYEE) || user.getRoles().contains(Role.ROLE_MANAGER)) {
                homeView.showDetailRoleOfUser(user);
            }
        }
    }

    private void promoteEmployee() {
        homeView.showMessage("Thăng cấp nhân viên");
        String username = homeView.getInput("Tên đăng nhập nhân viên bạn muốn thăng cấp: ");
        User user = userData.get(username);
        if (user == null || !user.getRoles().contains(Role.ROLE_EMPLOYEE)) {
            homeView.showMessage("Đây không phải là nhân viên");
            return;
        }
        user.removeRole(Role.ROLE_EMPLOYEE);
        user.addRole(Role.ROLE_MANAGER);
        homeView.showMessage("Thăng cấp nhân viên thành công");
    }

    private void demoteEmployee() {
        homeView.showMessage("Giáng chức quản lý");
        String username = homeView.getInput("Tên đăng nhập quản lý bạn muốn giáng chức: ");
        User user = userData.get(username);
        if (user == null || !user.getRoles().contains(Role.ROLE_MANAGER)) {
            homeView.showMessage("Đây không phải là quản lý");
            return;
        }
        user.removeRole(Role.ROLE_MANAGER);
        user.addRole(Role.ROLE_EMPLOYEE);
        homeView.showMessage("Giáng chức quản lý thành công");
    }

    private void deleteEmployee() {
        homeView.showMessage("Xóa nhân viên");
        String username = homeView.getInput("Tên đăng nhập nhân viên/quản lý bạn muốn xóa: ");
        User user = userData.get(username);
        if (user == null) {
            homeView.showMessage("Không có nhân viên này");
            return;
        } else if (!user.getRoles().contains(Role.ROLE_EMPLOYEE) || !user.getRoles().contains(Role.ROLE_MANAGER)) {
            homeView.showMessage("Đây không phải là nhân viên hoặc quản lý");
            return;
        } else if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            homeView.showMessage("Đây là tài khoản Admin, không thể xóa");
            return;
        }
        String message = homeView.getInput("Có chắc chắn xóa nhân viên này: ");
        if (message.equalsIgnoreCase("Có")) {
            userData.remove(username);
            homeView.showMessage("Xóa nhân viên/quản lý thành công");
        } else {
            homeView.showMessage("Xóa nhân viên/quản lý không thành công");
        }
    }















}
