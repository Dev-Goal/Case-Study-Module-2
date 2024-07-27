package controller.movie;

import model.Movie;
import service.CinemeService;
import view.CinemaView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MovieController {
    private CinemaView cinemaView = new CinemaView();
    private CinemeService cinemeService = new CinemeService();
    private Map<String, Movie> movieData = new HashMap<>();
    private static final String MOVIE_FILE_PATH = "src/controller/movie/movie.csv";

    public MovieController() {
        this.movieData = MovieCSVUtil.readMovieFromCSV(MOVIE_FILE_PATH);
    }

    public void showMovieList() {
        cinemaView.showMessage("Danh sách phim");
        movieData.values().stream().filter(Objects::nonNull).forEach(movie -> cinemaView.showDetailMovie(movie));
    }

    public void addMovie() {
        cinemaView.showMessage("Thêm phim");
        String idMovie = cinemeService.checkValidatedInput("ID phim: ",
                input -> !input.trim().isEmpty(),
                id -> movieData.values().stream().anyMatch(movie -> movie.getIdMovie().equalsIgnoreCase(id)),
                "ID phim đã tồn tại. Nhập ID khác");
        String nameMovie = cinemeService.checkValidatedInput("Tên phim: ",
                input -> !input.trim().isEmpty(),
                name -> movieData.values().stream().anyMatch(movie -> movie.getNameMovie().equalsIgnoreCase(name)),
                "Tên phim đã tồn tại. Nhập tên phim khác");
        String genreMovie = cinemeService.checkValidatedInput("Thể loại: ",
                input -> !input.trim().isEmpty(), null, "Thể loại phim không được để trống");
        int duration = Integer.parseInt(cinemeService.checkValidatedInput(
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
        String imageMovie = cinemeService.checkValidatedInput("Hình ảnh: ",
                input -> !input.trim().isEmpty(),
                null,
                "Link hình ảnh không được để trống");
        String trailerMovie = cinemeService.checkValidatedInput("Trailer: ",
                input -> !input.trim().isEmpty(),
                null,
                "Link trailer không được để trống");
        String descMovie = cinemeService.checkValidatedInput("Nội dung: ",
                input -> !input.trim().isEmpty(),
                null,
                "Nội dung không được để trống");
        Movie movie = new Movie(idMovie, nameMovie, genreMovie, duration, imageMovie, trailerMovie, descMovie);
        movieData.put(idMovie, movie);
        MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
        cinemaView.showMessage("Thêm phim thành công");
    }

    public void editMovie() {
        cinemaView.showMessage("Chỉnh sửa thông tin phim");
        String idMovie = cinemaView.getInput("ID phim: ");
        Movie movie = movieData.get(idMovie);
        if (movie == null) {
            cinemaView.showMessage("Không có phim thuộc ID này");
            return;
        }
        String nameMovie = cinemeService.checkValidatedInput("Tên phim (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                name -> !name.trim().isEmpty() && movieData.values().stream().anyMatch(newMovie -> newMovie.getNameMovie().equalsIgnoreCase(name) && !newMovie.getIdMovie().equals(idMovie)),
                "Tên phim đã tồn tại"
        );
        if (!nameMovie.trim().isEmpty()) {
            movie.setNameMovie(nameMovie);
        }
        String genreMovie = cinemeService.checkValidatedInput(
                "Tthể loại phim (có thể bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Thể loại phim không hợp lệ!"
        );
        if (!genreMovie.trim().isEmpty()) {
            movie.setGenreMovie(genreMovie);
        }
        String duration = cinemeService.checkValidatedInput(
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
        String imageMovie = cinemeService.checkValidatedInput(
                "Nhập đường dẫn hình ảnh mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Đường dẫn hình ảnh không hợp lệ!"
        );
        if (!imageMovie.trim().isEmpty()) {
            movie.setImage(imageMovie);
        }
        String trailerMovie = cinemeService.checkValidatedInput(
                "Nhập đường dẫn trailer mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Đường dẫn trailer không hợp lệ!"
        );
        if (!trailerMovie.trim().isEmpty()) {
            movie.setTrailer(trailerMovie);
        }
        String descMovie = cinemeService.checkValidatedInput(
                "Nhập mô tả nội dung phim mới (bỏ qua nếu không thay đổi): ",
                input -> true,
                null,
                "Mô tả nội dung phim không hợp lệ!"
        );
        if (!descMovie.trim().isEmpty()) {
            movie.setDesc(descMovie);
        }
        MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
        cinemaView.showMessage("Chỉnh sửa thông tin phim thành công");
    }

    public void deleteMovie() {
        cinemaView.showMessage("Xóa phim");
        String idMovie = cinemaView.getInput("ID phim: ");
        Movie movie = movieData.get(idMovie);
        if (movie == null) {
            cinemaView.showMessage("Không có phim này");
            return;
        }
        String message = cinemaView.getInput("Có chắc chắn xóa phim này: ");
        if (message.equalsIgnoreCase("Có")) {
            movieData.remove(idMovie);
            MovieCSVUtil.writeMovieToCSV(movieData, MOVIE_FILE_PATH);
            cinemaView.showMessage("Xóa phim thành công");
        } else {
            cinemaView.showMessage("Hủy xóa phim");
        }
    }
}
