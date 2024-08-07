package controller;


import csvutil.MovieCSVUtil;
import csvutil.ShowtimeCSVUtil;
import model.Movie;
import model.Showtime;
import service.HomeService;
import view.HomeView;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class MovieController {
    private HomeView homeView = new HomeView();
    private HomeService homeService = new HomeService();
    private static final String MOVIE_FILE_PATH = "src/data/movie.csv";
    private static final String SHOWTIME_FILE_PATH = "src/data/showtime.csv";
    private Map<String, Movie> movieData;
    private Map<String, Showtime> showtimeData;


    private void loadData() {
        this.movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
        this.showtimeData = ShowtimeCSVUtil.readShowTimeFromCSV(SHOWTIME_FILE_PATH);
    }

    public void showMovieList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        loadData();
        homeView.showMessage("Danh sách phim");
        movieData.values().stream().filter(Objects::nonNull).forEach(movie -> {
            homeView.showDetailMovie(movie);
            homeView.showMessage("Các suất chiếu:");
            showtimeData.values().stream()
                    .filter(showtime -> showtime.getIdMovie().equals(movie.getIdMovie()))
                    .forEach(showtime -> {
                        homeView.showMessage("Suất chiếu " + showtime.getIdShowtime()
                                + " - Thời gian bắt đầu: " + showtime.getStartTime().format(formatter)
                                + " - Phòng chiếu " + showtime.getIdScreenRoom()
                                + " - Số lượng ghế còn lại: " + showtime.getAvailableSeats());
                    });
            homeView.showMessage("-----------------------------------------------------------------------");
        });
    }

    public void addMovie() {
        loadData();
        homeView.showMessage("Thêm phim");
        String idMovie = homeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equalsIgnoreCase(id)),
                "ID phim đã có. Nhập ID khác");
        String nameMovie = homeService.checkValidatedInput("Tên phim: ",
                input -> !input.trim().isEmpty(),
                name -> movieData.values().stream().anyMatch(movie -> movie.getNameMovie().equalsIgnoreCase(name)),
                "Tên phim đã có. Nhập tên phim khác");
        String genreMovie = homeService.checkValidatedInput("Thể loại: ",
                input -> !input.trim().isEmpty(), null, "Thể loại phim không được để trống");
        int duration = Integer.parseInt(homeService.checkValidatedInput(
                "Thời lượng: ",
                input -> {
                    try {
                        int value = Integer.parseInt(input);
                        return value > 0;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }, null, "Thời lượng không hợp lệ"
        ));
        String imageMovie = homeService.checkValidatedInput("Hình ảnh: ",
                input -> !input.trim().isEmpty(),
                null,
                "Link hình ảnh không được để trống");
        String trailerMovie = homeService.checkValidatedInput("Trailer: ",
                input -> !input.trim().isEmpty(),
                null,
                "Link trailer không được để trống");
        String descMovie = homeService.checkValidatedInput("Nội dung: ",
                input -> !input.trim().isEmpty(),
                null,
                "Nội dung không được để trống");
        Movie movie = new Movie(idMovie, nameMovie, genreMovie, duration, imageMovie, trailerMovie, descMovie);
        movieData.put(idMovie, movie);
        MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
        homeView.showMessage("Thêm phim thành công");
    }

    public void editMovie() {
        loadData();
        homeView.showMessage("Chỉnh sửa thông tin phim");
        String idMovie = homeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> !movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equalsIgnoreCase(id)),
                "Không có phim thuộc ID này. Nhập ID khác");
        Movie movie = movieData.get(idMovie);
        String nameMovie = homeService.checkValidatedInput("Tên phim (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && movieData.values().stream()
                        .anyMatch(newMovie -> newMovie.getNameMovie().equalsIgnoreCase(name)
                                && !newMovie.getIdMovie().equals(idMovie)),
                "Tên phim này đã có"
        );
        if (!nameMovie.trim().isEmpty()) {
            movie.setNameMovie(nameMovie);
        }
        String genreMovie = homeService.checkValidatedInput(
                "Thể loại phim (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Thể loại phim không hợp lệ!"
        );
        if (!genreMovie.trim().isEmpty()) {
            movie.setGenreMovie(genreMovie);
        }
        String duration = homeService.checkValidatedInput(
                "Thời lượng (có thể bỏ qua nếu không thay đổi): ",
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
                },
                null,
                "Thời lượng không hợp lệ"
        );
        if (!duration.trim().isEmpty()) {
            movie.setDuration(Integer.parseInt(duration));
        }
        String imageMovie = homeService.checkValidatedInput(
                "Nhập đường dẫn hình ảnh mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Đường dẫn hình ảnh không hợp lệ!"
        );
        if (!imageMovie.trim().isEmpty()) {
            movie.setImage(imageMovie);
        }
        String trailerMovie = homeService.checkValidatedInput(
                "Nhập đường dẫn trailer mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Đường dẫn trailer không hợp lệ!"
        );
        if (!trailerMovie.trim().isEmpty()) {
            movie.setTrailer(trailerMovie);
        }
        String descMovie = homeService.checkValidatedInput(
                "Nhập mô tả nội dung phim mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Mô tả nội dung phim không hợp lệ!"
        );
        if (!descMovie.trim().isEmpty()) {
            movie.setDesc(descMovie);
        }
        MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
        homeView.showMessage("Chỉnh sửa thông tin phim thành công");
    }

    public void deleteMovie() {
        loadData();
        homeView.showMessage("Xóa phim");
        String idMovie = homeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> !movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equalsIgnoreCase(id)),
                "Không có phim thuộc ID này");
        String message = homeView.getInput("Có chắc chắn xóa phim này: ");
        if (message.equalsIgnoreCase("Có")) {
            movieData.remove(idMovie);
            MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
            homeView.showMessage("Xóa phim thành công");
        } else {
            homeView.showMessage("Hủy xóa phim");
        }
    }
}