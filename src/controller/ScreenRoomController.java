package controller;

import csvutil.CinemaCSVUtil;
import csvutil.ScreenRoomCSVUtil;
import model.Cinema;
import model.ScreenRoom;
import service.HomeService;
import view.HomeView;

import java.util.Map;
import java.util.Objects;

public class ScreenRoomController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private static final String SCREENROOM_FILE_PATH = "src/data/screenroom.csv";
    private static final String CINEMA_FILE_PATH = "src/data/cinema.csv";
    private Map<String, ScreenRoom> screenRoomData ;
    private Map<String, Cinema> cinemaData;

    private void loadData() {
        this.screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
        this.cinemaData = CinemaCSVUtil.readCinemaFromCSV(CINEMA_FILE_PATH);
    }

    public void showScreenRoomList() {
        loadData();
        homeView.showMessage("Danh sách phòng chiếu");
        screenRoomData.values().stream().filter(Objects::nonNull).forEach(screenRoom -> {
            String cinemaName = "Không có rạp chiếu này";
            Cinema cinema = cinemaData.get(screenRoom.getIdCinema());
            if (cinema != null) {
                cinemaName = cinema.getNameCinema();
            }
            homeView.showDetailScreenRoom(screenRoom, cinemaName);
            homeView.showMessage("-----------------------------------------------------------------------");
        });
    }

    public void addScreenRoom() {
        loadData();
        homeView.showMessage("Thêm phòng chiếu");
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "ID phòng chiếu này đã có");
        String nameScreenRoom = homeService.checkValidatedInput("Tên phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                name -> screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getNameScreenRoom().equalsIgnoreCase(name)),
                "Tên phòng chiếu đã có trong rạp. Nhập tên phòng chiếu khác");
        int totalSeats = Integer.parseInt(homeService.checkValidatedInput(
                "Số lượng ghế trong phòng chiếu: ",
                input -> {
                    try {
                        int value = Integer.parseInt(input);
                        return value > 0;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }, null, "Số lượng không hợp lệ"
        ));
        String idCinema = homeService.checkValidatedInput("ID rạp chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu phim thuộc ID này");
        ScreenRoom screenRoom = new ScreenRoom(idScreenRoom, nameScreenRoom, totalSeats, idCinema);
        screenRoomData.put(idScreenRoom, screenRoom);
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        homeView.showMessage("Thêm phòng chiếu thành công");
    }

    public void editScreenRoom() {
        loadData();
        homeView.showMessage("Chỉnh sửa phòng chiếu");
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        String nameScreenRoom = homeService.checkValidatedInput("Tên phòng chiếu (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && screenRoomData.values().stream()
                        .anyMatch(newScreenRoom -> newScreenRoom.getNameScreenRoom().equalsIgnoreCase(name)
                                && !newScreenRoom.getIdScreenRoom().equals(idScreenRoom)),
                "Tên phòng chiếu đã có");
        if (!nameScreenRoom.trim().isEmpty()) {
            screenRoom.setNameScreenRoom(nameScreenRoom);
        }
        String totalSeats = homeService.checkValidatedInput(
                "Số lượng ghế trong phòng chiếu: ",
                input -> {
                    if (input.trim().isEmpty()) {
                        return true;
                    }
                    try {
                        int value = Integer.parseInt(input);
                        return value > 0;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }, null, "Số lượng ghế trong phòng không hợp lệ"
        );
        if (!totalSeats.trim().isEmpty()) {
            screenRoom.setTotalSeats(Integer.parseInt(totalSeats));
        }
        String idCinema = homeService.checkValidatedInput("ID rạp chiếu (bỏ qua nếu không thay đổi: ",
                input -> true,
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu thuộc ID này");
        if (!idCinema.trim().isEmpty()) {
            screenRoom.setIdCinema(idCinema);
        }
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        homeView.showMessage("Chỉnh sửa phòng chiếu thành công");
    }

    public void deleteScreenRoom() {
        loadData();
        homeView.showMessage("Xóa phòng chiếu");
        String idScreenRoom = homeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        String message =  homeView.getInput("Có chắc chắn xóa phòng chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            screenRoomData.remove(idScreenRoom);
            ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
            homeView.showMessage("Xóa phòng chiếu thành công");
        } else {
            homeView.showMessage("Hủy xóa phòng chiếu");
        }
    }
}
