package controller.movie;

import model.Movie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MovieCSVUtil {
    public static Map<String, Movie> readMovieFromCSV(String filePath) {
        Map<String, Movie> movies = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) {
                    String id = values[0];
                    String name = values[1];
                    String genre = values[2];
                    int duration = Integer.parseInt(values[3]);
                    String image = values[4];
                    String trailer = values[5];
                    String description = values[6];
                    Movie movie = new Movie(id, name, genre, duration, image, trailer, description);
                    movies.put(id, movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void writeMovieToCSV(Map<String, Movie> movies, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Movie movie : movies.values()) {
                bw.write(String.join(",",
                        movie.getIdMovie(),
                        movie.getNameMovie(),
                        movie.getGenreMovie(),
                        String.valueOf(movie.getDuration()),
                        movie.getImage(),
                        movie.getTrailer(),
                        movie.getDesc()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
