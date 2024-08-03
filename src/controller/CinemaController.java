package controller;

import csvutil.CinemaCSVUtil;
import model.Cinema;
import service.HomeService;
import view.HomeView;

import java.util.Map;
import java.util.Objects;

public class CinemaController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private static final String CINEMA_FILE_PATH = "src/data/cinema.csv";
    private Map<String, Cinema> cinemaData;

    private void loadData() {
        this.cinemaData = CinemaCSVUtil.readCinemaFromCSV(CINEMA_FILE_PATH);
    }

    public void showCinemaList() {
        loadData();
        homeView.showMessage("Danh sách rạp chiếu phim");
        cinemaData.values().stream().filter(Objects::nonNull).forEach(cinema -> {
            homeView.showDetailCinema(cinema);
            homeView.showMessage("-----------------------------------------------------------------------");
        });
    }

    public void addCinema() {
        loadData();
        homeView.showMessage("Thêm rạp chiếu phim");
        String idCinema = homeService.checkValidatedInput("ID rạp chiếu phim: ",
                input -> !input.trim().isEmpty(),
                id -> cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "ID rạp chiếu phim đã có");
        String nameCinema = homeService.checkValidatedInput("Tên rạp chiếu phim: ",
                input -> !input.trim().isEmpty(),
                name -> cinemaData.values().stream().anyMatch(cinema -> cinema.getNameCinema().equalsIgnoreCase(name)),
                "Tên rạp chiếu phim đã có. Nhập tên rạp chiếu phim khác");
        int numberOfScreenRoom = Integer.parseInt(homeService.checkValidatedInput("Số lương phòng chiếu: ",
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
        homeView.showMessage("Thêm rạp chiếu phim thành công");
    }

    public void editCinema() {
        loadData();
        homeView.showMessage("Chỉnh sửa rạp chiếu phim");
        String idCinema = homeService.checkValidatedInput("ID rạp chiếu: ",
                input -> !input.trim().isEmpty(),
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu phim thuộc ID này");
        Cinema cinema = cinemaData.get(idCinema);
        String nameCinema = homeService.checkValidatedInput("Tên rạp chiếu phim (bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && cinemaData.values().stream()
                        .anyMatch(newCinema -> newCinema.getNameCinema().equalsIgnoreCase(name)
                                && !newCinema.getIdCinema().equals(idCinema)),
                "Tên rạp chiếu phim này đã có"
        );
        if (!nameCinema.trim().isEmpty()) {
            cinema.setNameCinema(nameCinema);
        }
        String numberOfScreenRoom = homeService.checkValidatedInput("Số lượng phòng chiếu (bỏ qua nếu không thay đổi): ",
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
        homeView.showMessage("Chỉnh sửa rạp chiếu phim thành công");
    }

    public void deleteCinema() {
        loadData();
        homeView.showMessage("Xóa rạp chiếu phim");
        String idCinema = homeService.checkValidatedInput("ID rạp chiếu phim: ",
                input -> !input.trim().isEmpty(),
                id -> !cinemaData.values().stream().anyMatch(cinema -> cinema.getIdCinema().equalsIgnoreCase(id)),
                "Không có rạp chiếu phim thuộc ID này");
        String message = homeView.getInput("Có chắc chắn muốn xóa rạp chiếu phim này: ");
        if (message.equalsIgnoreCase("Có")) {
            cinemaData.remove(idCinema);
            CinemaCSVUtil.writeCinemaToCSV(cinemaData, CINEMA_FILE_PATH);
            homeView.showMessage("Xóa rạp chiếu phim thành công");
        }
        homeView.showMessage("Hủy xóa rạp chiếu");
    }
}
