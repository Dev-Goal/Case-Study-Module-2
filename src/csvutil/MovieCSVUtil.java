package csvutil;

import model.Movie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MovieCSVUtil {
    public static Map<String, Movie> readMovieFromCSV(String filePath) {
        Map<String, Movie> movieData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) {
                    String idMovie = values[0];
                    String nameMovie = values[1];
                    String genreMovie = values[2];
                    int duration = Integer.parseInt(values[3]);
                    String imageMovie = values[4];
                    String trailerMovie = values[5];
                    String descMovie = values[6];
                    Movie movie = new Movie(idMovie, nameMovie, genreMovie, duration, imageMovie, trailerMovie, descMovie);
                    movieData.put(idMovie, movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieData;
    }

    public static void writeMovieToCSV(Map<String, Movie> movieData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Movie movie : movieData.values()) {
                bufferedWriter.write(String.join(",",
                        movie.getIdMovie(),
                        movie.getNameMovie(),
                        movie.getGenreMovie(),
                        String.valueOf(movie.getDuration()),
                        movie.getImage(),
                        movie.getTrailer(),
                        movie.getDesc()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
