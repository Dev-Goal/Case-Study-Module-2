package controller;

import model.*;
import view.HomeView;

import java.util.*;

public class HomeController {
    private final Map<String, Person> personData = new HashMap<>();
    private final HomeView homeView = new HomeView();
    private final CustomerController customerController = new CustomerController();
    private final EmployeeController employeeController = new EmployeeController();
    private final AdminController adminController = new AdminController();
    private final MovieController movieController = new MovieController();
    private final CinemaController cinemaController = new CinemaController();
    private final ScreenRoomController screenRoomController = new ScreenRoomController();
    private final ShowtimeController showtimeController = new ShowtimeController();
    private final PromotionController promotionController = new PromotionController();

    public HomeController() {
        createAccountAdmin();
    }

    private void createAccountAdmin() {
        if (!personData.containsKey("admin")) {
            Person admin = new Person("admin", "admin123");
            personData.put("admin", admin);
        }
    }

    public void start() {
        do {
            homeView.showMenuHome();
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> signIn();
                case "2" -> employeeController.signUpEmployee();
                case "3" -> customerController.signUpCustomer();
                case "4" -> System.exit(0);
            }
        }while (true);
    }

    private void signIn() {
        do {
            homeView.showMessage("1. Đăng nhập Admin");
            homeView.showMessage("2. Đăng nhập Khách hàng");
            homeView.showMessage("3. Đăng nhập Nhân viên");
            homeView.showMessage("4. Quay lại");
            String choice = homeView.getInput("Chọn: ");
            switch (choice) {
                case "1" -> signInAdmin();
                case "2" -> customerController.signInCustomer();
                case "3" -> employeeController.signInEmployee();
                case "4" -> {return;}
            }
        }while (true);
    }

    private void signInAdmin() {
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Person person = personData.get(username);
        if (person == null || !person.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        homeView.showMessage("Đăng nhập thành công");
        showMenuAdmin();
    }

    private void showMenuAdmin() {
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
                case "1" -> adminController.showCustomerList();
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
                case "1" -> adminController.showEmployeeList();
                case "2" -> adminController.promoteEmployee();
                case "3" -> adminController.demoteEmployee();
                case "4" -> adminController.deleteEmployee();
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



















}