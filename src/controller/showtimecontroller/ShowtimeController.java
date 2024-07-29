package controller.showtimecontroller;

import controller.moviecontroller.MovieCSVUtil;
import controller.screenroomcontroller.ScreenRoomCSVUtil;
import model.Movie;
import model.ScreenRoom;
import model.Showtime;
import service.CinemeService;
import view.CinemaView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class ShowtimeController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, Movie> movieData;
    private Map<String, ScreenRoom> screenRoomData;
    private Map<String, Showtime> showtimeData;
    private static final String MOVIE_FILE_PATH = "src/controller/moviecontroller/movie1.csv";
    private static final String SCREENROOM_FILE_PATH = "src/controller/screenroomcontroller/screenroom.csv";
    private static final String SHOWTIME_FILE_PATH = "src/controller/showtimecontroller/showtime.csv";

    public ShowtimeController() {
        movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
        screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
        showtimeData = ShowtimeCSVUtil.readShowTimeFromCSV(SHOWTIME_FILE_PATH);
    }

    public void showListShowTimes() {
        cinemaView.showMessage("Danh sách suất chiếu");
        showtimeData.values().stream().filter(Objects::nonNull).forEach(showtime -> {
            String idShowtime = showtime.getIdShowtime();
            String idMovie = showtime.getIdMovie();
            String nameMovie = movieData.get(idMovie) != null ? movieData.get(idMovie).getNameMovie() : "Không có phim";
            int duration = showtime.getDuration();
            String startTime = showtime.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
            String endTime = showtime.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
            String idScreeRoom = showtime.getIdScreenRoom();
            String nameScreeRoom = screenRoomData.get(idScreeRoom) != null ? screenRoomData.get(idScreeRoom).getNameScreenRoom() : "Không có phòng chiếu";
            int availableSeats = showtime.getAvailableSeats();
            cinemaView.showMessage("ID suất chiếu: " + idShowtime + " - Tên phim: " + nameMovie
                    + " - Thời lượng: " + duration + " - Thời gian bắt đầu: " + startTime + " - Phòng chiếu: " + nameScreeRoom
                    + " - Thời gian kết thúc: " + endTime  + " - Số ghế còn lại: " + availableSeats);
            cinemaView.showMessage("-------------------------------------------------------------------------------------------------");
        });
    }

    public void addShowTimes() {
        cinemaView.showMessage("Thêm suất chiếu");
        String idShowtime = cinemeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> showtimeData.values().stream().anyMatch(showtime -> showtime.getIdShowtime().equalsIgnoreCase(id)),
                "ID suất chiếu này đã tồn tại. Nhập ID khác");
        String idMovie = cinemeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> !movieData.containsKey(id),
                "Không có tên phim này");
        Movie movie = movieData.get(idMovie);
        System.out.println(movie);
        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu (HH:mm dd:MM:yyy): ");
        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyy"));
        } catch (Exception e) {
            cinemaView.showMessage("Thời gian không đúng form");
            return;
        }
        LocalDateTime endTime = startTime.plusMinutes(movie.getDuration());
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.containsKey(id),
                "Không có phòng chiếu này");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        Showtime showtime = new Showtime(idShowtime, idMovie, movie.getDuration(), startTime, endTime,
                idScreenRoom, screenRoom.getTotalSeats());
        showtimeData.put(idShowtime, showtime);
        screenRoom.addShowtimes(showtime);
        movie.addShowtimes(showtime);
        ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
        cinemaView.showMessage("Thêm suất chiếu thành công");
    }

    public void editShowTimes() {
        cinemaView.showMessage("Chỉnh sửa suất chiếu");
        String idShowtime = cinemeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !showtimeData.containsKey(id),
                "Không có suất chiếu thuộc ID này");
        Showtime showtime = showtimeData.get(idShowtime);
        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu (HH:mm dd:MM:yyy): ");
        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyy"));
        } catch (Exception e) {
            cinemaView.showMessage("Thời gian không đúng form");
            return;
        }
        LocalDateTime endTime = startTime.plusMinutes(showtime.getDuration());
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.containsKey(id),
                "Không có phòng chiếu này");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        showtime.setIdScreenRoom(idScreenRoom);
        showtime.setAvailableSeats(screenRoom.getTotalSeats());
        ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
        cinemaView.showMessage("Chỉnh sửa suất chiếu thành công");
    }

    public void deleteShowTimes() {
        cinemaView.showMessage("Xóa suất chiếu");
        String idShowtime = cinemeService.checkValidatedInput("ID suất chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !showtimeData.containsKey(id),
                "Không có suất chiếu thuộc ID này");
        Showtime showtime = showtimeData.get(idShowtime);
        String message = cinemaView.getInput("Bạn có chắc chắn muốn xóa suất chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            showtimeData.remove(idShowtime);
            ScreenRoom screenRoom = screenRoomData.get(showtime.getIdScreenRoom());
            if (screenRoom != null) {
                screenRoom.getShowtimes().remove(showtime);
            }
            ShowtimeCSVUtil.writeShowtimeToCSV(showtimeData, SHOWTIME_FILE_PATH);
            cinemaView.showMessage("Xóa suất chiếu thành công");
        } else {
            cinemaView.showMessage("Hủy xóa suất chiếu");
        }
    }
}
