package controller;

import model.*;
import view.CinemaView;

import java.util.*;

public class HomeController {
    private Map<String, User> userData = new HashMap<>();

    private CinemaView cinemaView = new CinemaView();
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
        if (userData.containsKey(username) && userData.containsValue(Role.ROLE_USER)) {
            cinemaView.showMessage("Tên đăng nhập đã được sử dụng");
            return;
        }
        String password = cinemaView.getInput("Mật khẩu: ");
        String fullName = cinemaView.getInput("Họ và tên: ");
        String email = cinemaView.getInput("Email: ");
        String phoneNumber = cinemaView.getInput("Số điện thoại: ");

        User newUser = new User(username, password, fullName, email, phoneNumber);
        userData.put(username, newUser);
        cinemaView.showMessage("Đăng ký khách hàng thành công");
    }

    private void signUpEmployee() {
        String username = cinemaView.getInput("Tên đăng nhập: ");
        if (userData.containsKey(username) && userData.containsValue(Role.ROLE_EMPLOYEE)) {
            cinemaView.showMessage("Tên đăng nhập đã được sử dụng");
            return;
        }
        String password = cinemaView.getInput("Mật khẩu: ");
        String fullName = cinemaView.getInput("Họ và tên: ");
        String email = cinemaView.getInput("Email: ");
        String phoneNumber = cinemaView.getInput("Số điện thoại: ");
        String citizen = cinemaView.getInput("CCCD: ");
        User newUser = new User(username, password, fullName, email, phoneNumber, citizen);
        userData.put(username, newUser);
        cinemaView.showMessage("Đăng ký nhân viên thành công");
    }

    private void signIn() {
        String username = cinemaView.getInput("Tên đăng nhập: ");
        String password = cinemaView.getInput("Mật khẩu: ");
        User user = userData.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            cinemaView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        cinemaView.showMessage("Đăng nhập thành công");
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            showOptionalAdmin();
        }
    }

    public void showOptionalAdmin() {
        do {
            cinemaView.showMessage("1. Khách hàng");
            cinemaView.showMessage("2. Nhân viên");
            cinemaView.showMessage("3. Phim");
            cinemaView.showMessage("4. Phòng chiếu");
            cinemaView.showMessage("5. Rạp chiếu");
            cinemaView.showMessage("6. Suất chiếu");
            cinemaView.showMessage("7. Khuyến mãi");
            cinemaView.showMessage("8. Đặt vé");
            cinemaView.showMessage("10. Đăng xuất");

            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Khách hàng");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách khách hàng");
            cinemaView.showMessage("2. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> showCustomerList();
                case "2" -> {return;}
            }
        }while (true);
    }

    private void showOptionalEmployee() {
        cinemaView.showMessage("Nhân viên/Quản lý");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách nhân viên");
            cinemaView.showMessage("2. Thăng cấp cho nhân viên");
            cinemaView.showMessage("3. Giáng chức quản lý");
            cinemaView.showMessage("4. Xóa nhân viên");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Phim");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách phim");
            cinemaView.showMessage("2. Thêm phim");
            cinemaView.showMessage("3. Chỉnh sửa phim");
            cinemaView.showMessage("4. Xóa phim");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Phòng chiếu");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách phòng chiếu");
            cinemaView.showMessage("2. Thêm phòng chiếu");
            cinemaView.showMessage("3. Chỉnh sửa phòng chiếu");
            cinemaView.showMessage("4. Xóa phòng chiếu");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Rạp chiếu");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách rạp chiếu");
            cinemaView.showMessage("2. Thêm rạp chiếu");
            cinemaView.showMessage("3. Chỉnh sửa rạp chiếu");
            cinemaView.showMessage("4. Xóa rạp chiếu");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Suất chiếu");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách suất chiếu");
            cinemaView.showMessage("2. Thêm suất chiếu");
            cinemaView.showMessage("3. Chỉnh sửa suất chiếu");
            cinemaView.showMessage("4. Xóa suất chiếu");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Khuyến mãi");
        do {
            cinemaView.showMessage("1. Hiển thị danh sách khuyến mãi");
            cinemaView.showMessage("2. Thêm khuyến mãi");
            cinemaView.showMessage("3. Chỉnh sửa khuyến mãi");
            cinemaView.showMessage("4. Xóa khuyến mãi");
            cinemaView.showMessage("5. Quay lại");
            String choice = cinemaView.getInput("Chọn: ");
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
        cinemaView.showMessage("Danh sách khách hàng");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_USER) || user.getRoles().contains(Role.ROLE_CUSTOMER)) {
                cinemaView.showDetailRoleOfUser(user);
            }
        }
    }

    private void showEmployeeList() {
        cinemaView.showMessage("Danh sách nhân viên");
        for (User user : userData.values()) {
            if (user.getRoles().contains(Role.ROLE_EMPLOYEE) || user.getRoles().contains(Role.ROLE_MANAGER)) {
                cinemaView.showDetailRoleOfUser(user);
            }
        }
    }

    private void promoteEmployee() {
        cinemaView.showMessage("Thăng cấp nhân viên");
        String username = cinemaView.getInput("Tên đăng nhập nhân viên bạn muốn thăng cấp: ");
        User user = userData.get(username);
        if (user == null || !user.getRoles().contains(Role.ROLE_EMPLOYEE)) {
            cinemaView.showMessage("Đây không phải là nhân viên");
            return;
        }
        user.removeRole(Role.ROLE_EMPLOYEE);
        user.addRole(Role.ROLE_MANAGER);
        cinemaView.showMessage("Thăng cấp nhân viên thành công");
    }

    private void demoteEmployee() {
        cinemaView.showMessage("Giáng chức quản lý");
        String username = cinemaView.getInput("Tên đăng nhập quản lý bạn muốn giáng chức: ");
        User user = userData.get(username);
        if (user == null || !user.getRoles().contains(Role.ROLE_MANAGER)) {
            cinemaView.showMessage("Đây không phải là quản lý");
            return;
        }
        user.removeRole(Role.ROLE_MANAGER);
        user.addRole(Role.ROLE_EMPLOYEE);
        cinemaView.showMessage("Giáng chức quản lý thành công");
    }

    private void deleteEmployee() {
        cinemaView.showMessage("Xóa nhân viên");
        String username = cinemaView.getInput("Tên đăng nhập nhân viên/quản lý bạn muốn xóa: ");
        User user = userData.get(username);
        if (user == null) {
            cinemaView.showMessage("Không có nhân viên này");
            return;
        } else if (!user.getRoles().contains(Role.ROLE_EMPLOYEE) || !user.getRoles().contains(Role.ROLE_MANAGER)) {
            cinemaView.showMessage("Đây không phải là nhân viên hoặc quản lý");
            return;
        } else if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            cinemaView.showMessage("Đây là tài khoản Admin, không thể xóa");
            return;
        }
        String message = cinemaView.getInput("Có chắc chắn xóa nhân viên này: ");
        if (message.equalsIgnoreCase("Có")) {
            userData.remove(username);
            cinemaView.showMessage("Xóa nhân viên/quản lý thành công");
        } else {
            cinemaView.showMessage("Xóa nhân viên/quản lý không thành công");
        }
    }















}
