package controller.cinemacontroller;

import model.Cinema;
import model.ScreenRoom;
import service.CinemeService;
import view.CinemaView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CinemaController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, Cinema> cinemaData;
    public static String CINEMA_FILE_PATH = "src/controller/cinemacontroller/cinema.csv";

    public CinemaController() {
        this.cinemaData = CinemaCSVUtil.readCinemaFromCSV(CINEMA_FILE_PATH);
    }

    public void showCinemaList() {
        cinemaView.showMessage("Danh sách rạp chiếu phim");
        cinemaData.values().stream().filter(Objects::nonNull).forEach(cinema -> cinemaView.showDetailCinema(cinema));
    }

    public void addCinema() {
        cinemaView.showMessage("Thêm rạp chiếu phim");
        String idCinema = cinemeService.checkValidatedInput("ID rạp chiếu phim: ",
                input -> !input.trim().isEmpty(),
                id -> cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "ID rạp chiếu phim đã tồn tại. Nhập ID khác");
        String nameCinema = cinemeService.checkValidatedInput("Tên rạp chiếu phim: ",
                input -> !input.trim().isEmpty(),
                name -> cinemaData.values().stream().anyMatch(cinema -> cinema.getNameCinema().equalsIgnoreCase(name)),
                "Tên rạp chiếu phim đã tồn tại. Nhập tên rạp chiếu phim khác");
        int numberOfScreenRoom = Integer.parseInt(cinemeService.checkValidatedInput("Số lương phòng chiếu: ",
                input -> {
                    try {
                        int value = Integer.parseInt(input);
                        return value > 0;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }, null, "Số lượng không hợp lệ"
        ));
        Cinema cinema = new Cinema(idCinema, nameCinema, numberOfScreenRoom);
        cinemaData.put(idCinema, cinema);
        CinemaCSVUtil.writeCinemaToCSV(cinemaData, CINEMA_FILE_PATH);
        cinemaView.showMessage("Thêm rạp chiếu phim thành công");
    }

    public void editCinema() {
        cinemaView.showMessage("Chỉnh sửa rạp chiếu phim");
        String idCinema = cinemaView.getInput("ID rạp chiếu phim: ");
        Cinema cinema = cinemaData.get(idCinema);
        if (cinema == null) {
            cinemaView.showMessage("Không có rạp chiếu phim thuộc ID này");
            return;
        }
        String nameCinema = cinemeService.checkValidatedInput("Tên rạp chiếu phim(có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && cinemaData.values().stream().anyMatch(newCinema -> newCinema.getNameCinema().equalsIgnoreCase(name) && !newCinema.getIdCinema().equals(idCinema)),
                "Tên rạp chiếu phim đã tồn tại"
        );
        if (!nameCinema.trim().isEmpty()) {
            cinema.setNameCinema(nameCinema);
        }
        String numberOfScreenRoom = cinemeService.checkValidatedInput("Số lượng phòng chiếu (có thể bỏ qua nếu không thay đổi): ",
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
                }, null, "Số lượng không hợp lệ");
        if (!numberOfScreenRoom.trim().isEmpty()) {
            cinema.setNumberOfScreenRoom(Integer.parseInt(numberOfScreenRoom));
        }
        CinemaCSVUtil.writeCinemaToCSV(cinemaData, CINEMA_FILE_PATH);
        cinemaView.showMessage("Chỉnh sửa rạp chiếu phim thành công");
    }

    public void deleteCinema() {
        cinemaView.showMessage("Xóa rạp chiếu phim");
        String idCinema = cinemaView.getInput("ID rạp chiếu phim: ");
        Cinema cinema = cinemaData.get(idCinema);
        if (cinema == null) {
            cinemaView.showMessage("Không có rạp chiếu phim này");
            return;
        }
        String message = cinemaView.getInput("Có chắc chắn muốn xóa rạp chiếu phim này: ");
        if (message.equalsIgnoreCase("Có")) {
            cinemaData.remove(idCinema);
            CinemaCSVUtil.writeCinemaToCSV(cinemaData, CINEMA_FILE_PATH);
            cinemaView.showMessage("Xóa rạp chiếu phim thành công");
        }
        cinemaView.showMessage("Hủy xóa rạp chiếu");
    }
}
