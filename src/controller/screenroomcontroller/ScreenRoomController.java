package controller.screenroomcontroller;

import model.ScreenRoom;
import service.CinemeService;
import view.CinemaView;

import java.util.Map;
import java.util.Objects;

public class ScreenRoomController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, ScreenRoom> screenRoomData;
    private static final String SCREENROOM_FILE_PATH = "src/controller/screenroomcontroller/screenroom.csv";

    public ScreenRoomController() {
        this.screenRoomData = ScreenRoomCSVUtil.readScreenRoomFromCSV(SCREENROOM_FILE_PATH);
    }

    public void showScreenRoomList() {
        cinemaView.showMessage("Danh sách phòng chiếu");
        screenRoomData.values().stream().filter(Objects::nonNull).forEach(screenRoom -> cinemaView.showDetailScreenRoom(screenRoom));
    }

    public void addScreenRoom() {
        cinemaView.showMessage("Thêm phòng chiếu");
        String idScreenRoom = cinemeService.checkValidatedInput("ID phòng chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> screenRoomData.values().stream().anyMatch(screenRoom -> screenRoom.getIdScreenRoom().equalsIgnoreCase(id)),
                "ID phòng chiếu này đã tồn tại. Nhập ID khác");
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
        ScreenRoom screenRoom = new ScreenRoom(idScreenRoom, nameScreenRoom, totalSeats);
        screenRoomData.put(idScreenRoom, screenRoom);
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        cinemaView.showMessage("Thêm phòng chiếu thành công");
    }

    public void editScreenRoom() {
        cinemaView.showMessage("Chỉnh sửa phòng chiếu");
        String idScreenRoom = cinemaView.getInput("ID phòng chiếu bạn muốn sửa: ");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
        String nameScreenRoom = cinemeService.checkValidatedInput("Tên phòng chiếu (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && screenRoomData.values().stream()
                        .anyMatch(newScreenRoom -> newScreenRoom.getNameScreenRoom().equalsIgnoreCase(name)
                                && !newScreenRoom.getIdScreenRoom().equals(idScreenRoom)),
                "Tên phòng chiếu đã có trong rạp. Nhập tên phòng chiếu khác");
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
        ScreenRoomCSVUtil.writeScreenRoomToCSV(screenRoomData, SCREENROOM_FILE_PATH);
        cinemaView.showMessage("Chỉnh sửa phòng chiếu thành công");
    }

    public void deleteScreenRoom() {
        cinemaView.showMessage("Xóa phòng chiếu");
        String idScreenRoom = cinemaView.getInput("ID phòng chiếu: ");
        ScreenRoom screenRoom = screenRoomData.get(idScreenRoom);
        if (screenRoom == null) {
            cinemaView.showMessage("Phòng chiếu không tồn tại");
            return;
        }
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
