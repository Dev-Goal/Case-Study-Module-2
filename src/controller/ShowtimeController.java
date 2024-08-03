package controller;

import csvutil.MovieCSVUtil;
import csvutil.ScreenRoomCSVUtil;
import csvutil.ShowtimeCSVUtil;
import model.Movie;
import model.ScreenRoom;
import model.Showtime;
import service.HomeService;
import view.HomeView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class ShowtimeController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private static final String MOVIE_FILE_PATH = "src/data/movie.csv";
    private static final String SCREENROOM_FILE_PATH = "src/data/screenroom.csv";
    private static final String SHOWTIME_FILE_PATH = "src/data/showtime.csv";
    private Map<String, Movie> movieData;
    private Map<String, ScreenRoom> screenRoomData;
    private Map<String, Showtime> showtimeData;

    private void loadData() {
        this.movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
        this.screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
        this.showtimeData = ShowtimeCSVUtil.readShowTimeFromCSV(SHOWTIME_FILE_PATH);
    }

    public void showListShowTimes() {
        loadData();
        homeView.showMessage("Danh sách suất chiếu");
        showtimeData.values().stream().filter(Objects::nonNull).forEach(showtime -> {
            String idShowtime = showtime.getIdShowtime();
            String idMovie = showtime.getIdMovie();
            String nameMovie = movieData.get(idMovie) != null ? movieData.get(idMovie).getNameMovie() : "Không có phim";
            int duration = showtime.getDuration();
            String startTime = showtime.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
            String endTime = showtime.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
            String idScreenRoom = showtime.getIdScreenRoom();
            String nameScreenRoom = screenRoomData.get(idScreenRoom) != null ? screenRoomData.get(idScreenRoom).getNameScreenRoom() : "Không có phòng chiếu";
            int availableSeats = showtime.getAvailableSeats();
            homeView.showMessage("ID suất chiếu: " + idShowtime + " - Tên phim: " + nameMovie);
            homeView.showMessage("Thời gian bắt đầu: " + startTime + " - Thời lượng: " + duration +  " - Thời gian kết thúc: " + endTime);
            homeView.showMessage("Phòng chiếu: " + nameScreenRoom + " - Số ghế còn lại: " + availableSeats);
            homeView.showMessage("-----------------------------------------------------------");
        });
    }

    public void addShowTimes() {
        loadData();
        homeView.showMessage("Thêm suất chiếu");
        String idShowtime = homeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> showtimeData.values().stream().anyMatch(showtime -> showtime.getIdShowtime().equalsIgnoreCase(id)),
                "ID suất chiếu này đã có. Nhập ID khác");
        String idMovie = homeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> !movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equalsIgnoreCase(id)),
                "Không có tên phim thuộc ID này");
        Movie movie = movieData.get(idMovie);
        System.out.println(movie);
        String startTimeStr = homeView.getInput("Thời gian bắt đầu (HH:mm dd:MM:yyyy): ");
        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
        } catch (Exception e) {
            homeView.showMessage("Thời gian không đúng form");
            return;
        }
        LocalDateTime endTime = startTime.plusMinutes(movie.getDuration());
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        Showtime showtime = new Showtime(idShowtime, idMovie, movie.getDuration(), startTime, endTime,
                idScreenRoom, screenRoom.getTotalSeats());
        showtimeData.put(idShowtime, showtime);
//        screenRoom.addShowtimes(showtime);
//        movie.addShowtimes(showtime);
        ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
        homeView.showMessage("Thêm suất chiếu thành công");
    }

    public void editShowTimes() {
        loadData();
        homeView.showMessage("Chỉnh sửa suất chiếu");
        String idShowtime = homeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !showtimeData.values().stream().anyMatch(showtime -> showtime.getIdShowtime().equalsIgnoreCase(id)),
                "Không có suất chiếu thuộc ID này");
        Showtime showtime = showtimeData.get(idShowtime);
        String startTimeStr = homeView.getInput("Thời gian bắt đầu (HH:mm dd:MM:yyyy): ");
        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
        } catch (Exception e) {
            homeView.showMessage("Thời gian không đúng form");
            return;
        }
        LocalDateTime endTime = startTime.plusMinutes(showtime.getDuration());
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> true,
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        if (!idScreenRoom.trim().isEmpty()) {
            showtime.setIdScreenRoom(idScreenRoom);
        }
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        showtime.setAvailableSeats(screenRoom.getTotalSeats());
        ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
        homeView.showMessage("Chỉnh sửa suất chiếu thành công");
    }

    public void deleteShowTimes() {
        loadData();
        homeView.showMessage("Xóa suất chiếu");
        String idShowtime = homeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !showtimeData.values().stream().anyMatch(showtime -> showtime.getIdShowtime().equalsIgnoreCase(id)),
                "Không có suất chiếu thuộc ID này");
        Showtime showtime = showtimeData.get(idShowtime);
        String message = homeView.getInput("Bạn có chắc chắn muốn xóa suất chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            showtimeData.remove(idShowtime);
            ScreenRoom screenRoom = screenRoomData.get(showtime.getIdScreenRoom());
            if (screenRoom != null) {
                screenRoom.getShowtimes().remove(showtime);
            }
            ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
            homeView.showMessage("Xóa suất chiếu thành công");
        } else {
            homeView.showMessage("Hủy xóa suất chiếu");
        }
    }
}
