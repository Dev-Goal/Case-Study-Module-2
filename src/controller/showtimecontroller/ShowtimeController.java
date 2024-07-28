package controller.showtimecontroller;

import model.Movie;
import model.ScreenRoom;
import model.Showtime;
import service.CinemeService;
import view.CinemaView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ShowtimeController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, Movie> movieData;
    private Map<String, ScreenRoom> screenRoomData;
    private Map<String, Showtime> showtimeData;

    public void showListShowTimes() {
        cinemaView.showMessage("Danh sách suất chiếu");
        for (Showtime showtime : showtimeData.values()) {
            cinemaView.showDetailShowTime(showtime);
        }
    }

    public void addShowTimes() {
//        cinemaView.showMessage("Thêm suất chiếu");
//        int randomIdShowtime = (int) (Math.random() * 20);
//        int idMovie = Integer.parseInt(cinemaView.getInput("ID phim: "));
//        Movie movie = movieData.get(idMovie);
//        if (movie == null) {
//            cinemaView.showMessage("Phim không tồn tại");
//            return;
//        }
//        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu: ");
//        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
//        LocalDateTime endTine = startTime.plusMinutes(movie.getDuration());
//        String idScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
//        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
//        if (screenRoom == null) {
//            cinemaView.showMessage("Phòng chiếu không tồn tại");
//            return;
//        }
//        Showtime showtime = new Showtime(randomIdShowtime, idMovie, movie.getDuration(), startTime, endTine,
//                idScreenRoom, screenRoom.getTotalSeats());
//        showtimeData.put(showtime.getIdShowtime(), showtime);
//        screenRoom.addShowtime(showtime);
//        cinemaView.showMessage("Thêm suất chiếu thành công");
    }

    public void editShowTimes() {
//        cinemaView.showMessage("Chỉnh sửa suất chiếu");
//        int idShowtime = Integer.parseInt(cinemaView.getInput("ID suất chiếu bạn muốn sửa: "));
//        Showtime showtime = showtimeData.get(idShowtime);
//        if (showtime == null) {
//            cinemaView.showMessage("Suất chiếu không tồn tại");
//            return;
//        }
//        String startTimeStr = cinemaView.getInput("Thời gian bắt đầu: ");
//        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
//        LocalDateTime endTime = startTime.plusMinutes(showtime.getDuration());
//        String nameScreenRoom = cinemaView.getInput("Tên phòng chiếu: ");
//        ScreenRoom screenRoom = screenRoomData.get(nameScreenRoom);
//        if (screenRoom == null) {
//            cinemaView.showMessage("Phòng chiếu không tồn tại");
//            return;
//        }
//        showtime.setStartTime(startTime);
//        showtime.setEndTime(endTime);
//        showtime.setNameScreenRoom(screenRoom.setNameScreenRoom(nameScreenRoom));
//        cinemaView.showMessage("Chỉnh sửa suất chiếu thành công");
    }

    public void deleteShowTimes() {
//        cinemaView.showMessage("Xóa suất chiếu");
//        int idShowtime = Integer.parseInt(cinemaView.getInput("ID suất chiếu bạn muốn xóa: "));
//        Showtime showtime = showtimeData.get(idShowtime);
//        if (showtime == null) {
//            cinemaView.showMessage("Suất chiếu không tồn tại");
//            return;
//        }
//        String message = cinemaView.getInput("Bạn có chắc chắn muốn xóa suất chiếu này: ");
//        if (message.equalsIgnoreCase("Có")) {
//            showtimeData.remove(idShowtime);
//        }
//        cinemaView.showMessage("Xóa suất chiếu thành công");
    }
}
