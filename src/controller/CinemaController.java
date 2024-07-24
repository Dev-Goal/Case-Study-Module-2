package controller;

import model.*;
import view.CinemaView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CinemaController {
    private final Map<String, User> userData = new HashMap<>();
    private final Map<String, Movie> movieData = new HashMap<>();
    private final Map<String, Cinema> cinemaData = new HashMap<>();
    private final Map<String, ScreenRoom> screenRoomData = new HashMap<>();
    private final Map<String, Showtime> showtimeData = new HashMap<>();
    private final Map<String, Ticket> ticketData = new HashMap<>();
    private final Map<String, Promotion> promotionData = new HashMap<>();
    private final CinemaView cinemaView = new CinemaView();

    public CinemaController() {
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
        if (userData.containsKey(username) && userData.containsValue(Role.ROLE_CUSTOMER)) {
            cinemaView.showMessage("Tên đăng nhập đã tồn tại");
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
            cinemaView.showMessage("Tên đăng nhập đã tồn tại");
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
            cinemaView.showMessage("3. Thăng cấp cho nhân viên");
            cinemaView.showMessage("4. Giáng chức quản lý");
            cinemaView.showMessage("5. Xóa nhân viên");
            cinemaView.showMessage("6. Hiển thị danh sách phim");
            cinemaView.showMessage("7. Thêm phim");
            cinemaView.showMessage("8. Sửa phim");
            cinemaView.showMessage("9. Xóa phim");
            cinemaView.showMessage("10. Hiển thị danh sách phòng chiếu");
            cinemaView.showMessage("11. Thêm phòng chiếu");
            cinemaView.showMessage("12. Sửa phòng chiếu");
            cinemaView.showMessage("13. Xóa phòng chiếu");
            cinemaView.showMessage("14. Hiển thị danh sách rạp chiếu");
            cinemaView.showMessage("15. Thêm rạp chiếu");
            cinemaView.showMessage("16. Sửa rạp chiếu");
            cinemaView.showMessage("17. Xóa rạp chiếu");
            cinemaView.showMessage("18. Hiển thị danh sách suất chiếu");
            cinemaView.showMessage("19. Thêm suất chiếu");
            cinemaView.showMessage("20. Sửa suất chiếu");
            cinemaView.showMessage("21. Xóa suất chiếu");
            cinemaView.showMessage("18. Hiển thị danh sách vé");
            cinemaView.showMessage("19. Thêm vé");
            cinemaView.showMessage("20. Sửa vé");
            cinemaView.showMessage("21. Xóa vé");
            cinemaView.showMessage("18. Hiển thị danh sách khuyến mãi");
            cinemaView.showMessage("19. Thêm khuyến mãi");
            cinemaView.showMessage("20. Sửa khuyến mãi");
            cinemaView.showMessage("21. Xóa khuyến mãi");
            cinemaView.showMessage("50. Đăng xuất");

            String choice = cinemaView.getInput("Sự lựa chọn của bạn là: ");
            switch (choice) {
                case "1"  -> showCustomerList();
                case "2"  -> showEmployeeList();
                case "3"  -> promoteEmployee();
                case "4"  -> demoteEmployee();
                case "5"  -> deleteEmployee();
                case "6"  -> showMovieList();
                case "7"  -> addMovie();
                case "8"  -> editMovie();
                case "9"  -> deleteMovie();
                case "10"  -> showScreenRoomList();
                case "11" -> addScreenRoom();
                case "12" -> editScreenRoom();
                case "13" -> deleteScreenRoom();
                case "14"  -> showCinemaList();
                case "15" -> addCinema();
                case "16" -> editCinema();
                case "17" -> deleteCinema();
                case "18"  -> showListShowTimes();
                case "19" -> addShowTimes();
                case "20" -> editShowTimes();
                case "21" -> deleteShowTimes();
                case "22"  -> showTicketList();
                case "23" -> addTicket();
                case "24" -> editTicket();
                case "25" -> deleteTicket();
                case "26"  -> showPromotionList();
                case "27" -> addPromotion();
                case "28" -> editPromotion();
                case "29" -> deletePromotion();
                case "50" -> {return;}
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
        String username = cinemaView.getInput("Nhập vào tên đăng nhập nhân viên bạn muốn thăng cấp: ");
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
        String username = cinemaView.getInput("Nhập vào tên đăng nhập quản lý bạn muốn giáng chức: ");
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
        String username = cinemaView.getInput("Nhập vào tên đăng nhập nhân viên bạn muốn xóa: ");
        User user = userData.get(username);
        if (user == null) {
            cinemaView.showMessage("Nhân viên không tồn tại");
            return;
        }
        if (!user.getRoles().contains(Role.ROLE_EMPLOYEE) && !user.getRoles().contains(Role.ROLE_MANAGER)) {
            cinemaView.showMessage("Đây không phải là nhân viên hoặc quản lý");
            return;
        }
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            cinemaView.showMessage("Bạn không thế xóa tài khoản Admin");
            return;
        }
        String message = cinemaView.getInput("Bạn có chắc chắn xóa nhân viên này: ");
        if (message.equalsIgnoreCase("Có")) {
            userData.remove(username);
        }
        cinemaView.showMessage("Xóa nhân viên thành công");
    }

    private void showMovieList() {
        cinemaView.showMessage("Danh sách phim");
        for (Movie movie : movieData.values()) {
            if (movie != null) {
                cinemaView.showDetailMovie(movie);
            }
        }
        cinemaView.showMessage("Chưa có phim");
    }

    private void addMovie() {
        cinemaView.showMessage("Thêm phim");
        String nameMovie = cinemaView.getInput("Tên phim: ");
        String genreMovie = cinemaView.getInput("Thể loại: ");
        int duration = Integer.parseInt(cinemaView.getInput("Thời gian: "));
        String imageMovie = cinemaView.getInput("Hình ảnh (URL): ");
        String trailer = cinemaView.getInput("Trailer (URL): ");
        String desc = cinemaView.getInput("Mô tả: ");
        Movie movie = new Movie(nameMovie, genreMovie, duration, imageMovie, trailer, desc);
        movieData.put(nameMovie, movie);
        cinemaView.showMessage("Thêm phim thành công");
    }

    private void editMovie() {
        cinemaView.showMessage("Chỉnh sửa thông tin phim");
        String nameMovie = cinemaView.getInput("Nhập vào tên phim bạn muốn sửa: ");
        Movie movie = movieData.get(nameMovie);
        if (movie == null) {
            cinemaView.showMessage("Phim không tồn tại");
            return;
        }
        String genreMovie = cinemaView.getInput("Thể loại: ");
        int duration = Integer.parseInt(cinemaView.getInput("Thời gian: "));
        String imageMovie = cinemaView.getInput("Hình ảnh (URL): ");
        String trailer = cinemaView.getInput("Trailer (URL): ");
        String desc = cinemaView.getInput("Mô tả: ");
        movie.setGenreMovie(genreMovie);
        movie.setDuration(duration);
        movie.setImage(imageMovie);
        movie.setTrailer(trailer);
        movie.setDesc(desc);
        cinemaView.showMessage("Chỉnh sửa thông tin phim thành công");
    }

    private void deleteMovie() {
        cinemaView.showMessage("Xóa phim");
        String nameMovie = cinemaView.getInput("Nhập vào tên phim bạn muốn xóa: ");
        Movie movie = movieData.get(nameMovie);
        if (movie == null) {
            cinemaView.showMessage("Phim không tồn tại");
            return;
        }
        movieData.remove(nameMovie);
        cinemaView.showMessage("Xóa phim thành công");
    }

    private void showScreenRoomList() {
        cinemaView.showMessage("Danh sách phòng chiếu");
        for (ScreenRoom screenRoom : screenRoomData.values()) {
            cinemaView.showDetailScreenRoom(screenRoom);
        }
    }

    private void addScreenRoom() {
        cinemaView.showMessage("Thêm phòng chiếu");
        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
        if (screenRoomData.containsKey(nameScreenRoom)) {
            cinemaView.showMessage("Tên phòng chiếu đã tồn tại");
            return;
        }
        int numberOfSeats = Integer.parseInt(cinemaView.getInput("Số ghế: "));
        ScreenRoom screenRoom = new ScreenRoom(nameScreenRoom, numberOfSeats);
        screenRoomData.put(nameScreenRoom, screenRoom);
        cinemaView.showMessage("Thêm phòng chiếu thành công");
    }

    private void editScreenRoom() {
        cinemaView.showMessage("Chỉnh sửa phòng chiếu");
        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
        ScreenRoom screenRoom = screenRoomData.get(nameScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
        int numberOfSeats = Integer.parseInt(cinemaView.getInput("Số ghế: "));
        screenRoom.setNumberOfSeats(numberOfSeats);
        cinemaView.showMessage("Chỉnh sửa phòng chiếu thành công");
    }

    private void deleteScreenRoom() {
        cinemaView.showMessage("Xóa phòng chiếu");
        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
        ScreenRoom screenRoom = screenRoomData.get(nameScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
        String message =  cinemaView.getInput("Bạn chắc chắn muốn xóa phòng chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            screenRoomData.remove(nameScreenRoom);
        }
        cinemaView.showMessage("Xóa phòng chiếu thành công");
    }

    private void showCinemaList() {
        cinemaView.showMessage("Danh sách rạp chiếu phim");
        for (Cinema cinema : cinemaData.values()) {
            cinemaView.showDetailCinema(cinema);
        }
    }

    private void addCinema() {
        cinemaView.showMessage("Thêm rạp chiếu");
        String nameCinema = cinemaView.getInput("Tên rạp chiếu: ");
        if (cinemaData.containsKey(nameCinema)) {
            cinemaView.showMessage("Tên rạp chiếu đã tồn tại");
            return;
        }
        int numberOfScreenRoom = Integer.parseInt(cinemaView.getInput("Số phòng chiếu: "));
        Cinema cinema = new Cinema(nameCinema, numberOfScreenRoom);
        cinemaData.put(nameCinema, cinema);
        cinemaView.showMessage("Thêm rạp chiếu thành công");
    }

    private void editCinema() {
        cinemaView.showMessage("Chỉnh sửa rạp chiếu chiếu");
        String nameCinema = cinemaView.getInput("Tên rạp chiếu: ");
        Cinema cinema = cinemaData.get(nameCinema);
        if (cinema == null) {
            cinemaView.showMessage("Rạp chiếu không tồn tại");
            return;
        }
        int numberOfScreenRoom = Integer.parseInt(cinemaView.getInput("Số phòng: "));
        cinema.setNumberOfScreenRoom(numberOfScreenRoom);
        cinemaView.showMessage("Chỉnh sửa phòng chiếu thành công");
    }

    private void deleteCinema() {
        cinemaView.showMessage("Xóa rạp chiếu phim");
        String nameCinema = cinemaView.getInput("Nhập tên rạp chiếu phim bạn muốn xóa: ");
        Cinema cinema = cinemaData.get(nameCinema);
        if (cinema == null) {
            cinemaView.showMessage("Rạp chiếu phim không tồn tại");
            return;
        }
        String message = cinemaView.getInput("Bạn có chắc chắn muốn xóa rạp chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            cinemaData.remove(nameCinema);
        }
        cinemaView.showMessage("Xóa thành công");
    }

    private void showListShowTimes() {
        cinemaView.showMessage("Danh sách suất chiếu");
        for (Showtime showtime : showtimeData.values()) {
            cinemaView.showDetailShowTime(showtime);
        }
    }

    private void addShowTimes() {
        cinemaView.showMessage("Thêm suất chiếu");
        String nameMovie = cinemaView.getInput("Tên phim: ");
        Movie movie = movieData.get(nameMovie);
        if (movie == null) {
            cinemaView.showMessage("Phim không tồn tại");
            return;
        }
        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu: ");
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
        LocalDateTime endTine = startTime.plusMinutes(movie.getDuration());
        String nameCinema = cinemaView.getInput("Tên rạp chiếu: ");
        Cinema cinema = cinemaData.get(nameCinema);
        if (cinema == null) {
            cinemaView.showMessage("Rạp chiếu không tồn tại");
            return;
        }
        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
        ScreenRoom screenRoom = screenRoomData.get(nameScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
        Showtime showtime = new Showtime(UUID.randomUUID().toString(), nameMovie, movie.getDuration(),
                startTime, endTine, nameCinema, nameScreenRoom, screenRoom.getNumberOfSeats());
        showtimeData.put(showtime.getIdShowtime(), showtime);
        cinemaView.showMessage("Thêm suất chiếu thành công");
    }

    private void editShowTimes() {
        cinemaView.showMessage("Chỉnh sửa suất chiếu");
        String idShowtime = cinemaView.getInput("Nhập id suất chiếu cần sửa: ");
        Showtime showtime = showtimeData.get(idShowtime);
        if (showtime == null) {
            cinemaView.showMessage("Suất chiếu không tồn tại");
            return;
        }
        String nameMovie = cinemaView.getInput("Tên phim: ");
        Movie movie = movieData.get(nameMovie);
        if (movie == null) {
            cinemaView.showMessage("Tên phim không tồn tại");
            return;
        }
        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu: ");
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
        LocalDateTime endTime = startTime.plusMinutes(showtime.getDuration());
        String nameCinema = cinemaView.getInput("Tên rạp: ");
        Cinema cinema = cinemaData.get(nameCinema);
        if (cinema == null) {
            cinemaView.showMessage("Rạp chiếu không tồn tại");
            return;
        }
        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
        ScreenRoom screenRoom = screenRoomData.get(nameScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
        showtime.setNameMovie(Set.of(nameMovie));
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        showtime.setNameCinema(Set.of(nameCinema));
        showtime.setNameScreenRoom(Set.of(nameScreenRoom));
        cinemaView.showMessage("Chỉnh sửa suất chiếu thành công");
    }

    private void deleteShowTimes() {
        cinemaView.showMessage("Xóa suất chiếu");
        String idShowtime = cinemaView.getInput("Nhập id suất chiếu muốn xóa: ");
        Showtime showtime = showtimeData.get(idShowtime);
        if (showtime == null) {
            cinemaView.showMessage("Suất chiếu không tồn tại");
            return;
        }
        String message = cinemaView.getInput("Bạn có chắc chắn muốn xóa suất chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            showtimeData.remove(idShowtime);
        }
        cinemaView.showMessage("Xóa suất chiếu thành công");
    }

    private void showTicketList() {
    }

    private void addTicket() {
        cinemaView.showMessage("Thêm vé mới");
        String idShowtime = cinemaView.getInput("Id suất chiếu: ");
        Showtime showtime = showtimeData.get(idShowtime);
        if (showtime == null) {
            cinemaView.showMessage("Suất chiếu không tồn tại");
            return;
        }
        double price = Double.parseDouble(cinemaView.getInput("Nhập giá vé: " ));
        String typeTicket = cinemaView.getInput("Nhập loại vé (Người lớn, trẻ em, sinh viên, hội viên,...): ");
        String numberSeat = cinemaView.getInput("Nhập số ghế: ");
        String status = cinemaView.getInput("Trạng thái: ");
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), showtime, price, typeTicket
                , numberSeat, showtime.getStartTime(), status);
        ticketData.put(ticket.getIdTicket(), ticket);
        cinemaView.showMessage("Thêm vé thành công");
    }

    private void editTicket() {
    }

    private void deleteTicket() {
    }

    private void showPromotionList() {
    }

    private void addPromotion() {
    }

    private void editPromotion() {
    }

    private void deletePromotion() {
    }

}
