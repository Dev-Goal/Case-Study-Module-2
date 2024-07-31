package controller;

import csvutil.CinemaCSVUtil;
import csvutil.ScreenRoomCSVUtil;
import model.Cinema;
import model.ScreenRoom;
import service.CinemeService;
import view.CinemaView;

import java.util.Map;
import java.util.Objects;

public class ScreenRoomController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
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
        cinemaView.showMessage("Danh sách phòng chiếu");
        screenRoomData.values().stream().filter(Objects::nonNull).forEach(screenRoom -> {
            String cinemaName = "Không có rạp chiếu này";
            Cinema cinema = cinemaData.get(screenRoom.getIdCinema());
            if (cinema != null) {
                cinemaName = cinema.getNameCinema();
            }
            cinemaView.showDetailScreenRoom(screenRoom, cinemaName);
            cinemaView.showMessage("-----------------------------------------------------------------------");
        });
    }

    public void addScreenRoom() {
        loadData();
        cinemaView.showMessage("Thêm phòng chiếu");
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này. Nhập ID khác");
        String nameScreenRoom = cinemeService.checkValidatedInput("Tên phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                name -> screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getNameScreenRoom().equalsIgnoreCase(name)),
                "Tên phòng chiếu đã có trong rạp. Nhập tên phòng chiếu khác");
        int totalSeats = Integer.parseInt(cinemeService.checkValidatedInput(
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
        String idCinema = cinemeService.checkValidatedInput("ID rạp chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu phim thuộc ID này");
        Cinema cinema = cinemaData.get(idScreenRoom);
        ScreenRoom screenRoom = new ScreenRoom(idScreenRoom, nameScreenRoom, totalSeats, idCinema);
        screenRoomData.put(idScreenRoom, screenRoom);
        cinema.addScreenRoom(screenRoom);
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        cinemaView.showMessage("Thêm phòng chiếu thành công");
    }

    public void editScreenRoom() {
        loadData();
        cinemaView.showMessage("Chỉnh sửa phòng chiếu");
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        String nameScreenRoom = cinemeService.checkValidatedInput("Tên phòng chiếu (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && screenRoomData.values().stream()
                        .anyMatch(newScreenRoom -> newScreenRoom.getNameScreenRoom().equalsIgnoreCase(name)
                                && !newScreenRoom.getIdScreenRoom().equals(idScreenRoom)),
                "Tên phòng chiếu đã có");
        if (!nameScreenRoom.trim().isEmpty()) {
            screenRoom.setNameScreenRoom(nameScreenRoom);
        }
        String totalSeats = cinemeService.checkValidatedInput(
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
        String idCinema = cinemeService.checkValidatedInput("ID rạp chiếu (bỏ qua nếu không thay đổi: ",
                input -> true,
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu thuộc ID này");
        if (!idCinema.trim().isEmpty()) {
            screenRoom.setIdCinema(idCinema);
        }
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        cinemaView.showMessage("Chỉnh sửa phòng chiếu thành công");
    }

    public void deleteScreenRoom() {
        loadData();
        cinemaView.showMessage("Xóa phòng chiếu");
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "Không có phòng chiếu thuộc ID này");
        String message =  cinemaView.getInput("Có chắc chắn xóa phòng chiếu này: ");
        if (message.equalsIgnoreCase("Có")) {
            screenRoomData.remove(idScreenRoom);
            ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
            cinemaView.showMessage("Xóa phòng chiếu thành công");
        } else {
            cinemaView.showMessage("Hủy xóa phòng chiếu");
        }
    }
}
