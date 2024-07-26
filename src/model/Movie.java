package model;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    private int idMovie;
    private String nameMovie;
    private String genreMovie;
    private int duration;
    private String image;
    private String trailer;
    private String desc;
    private Set<Showtime> showtimes;

    public Movie(int idMovie, String nameMovie, String genreMovie, int duration, String image, String trailer, String desc) {
        this.idMovie = idMovie;
        this.nameMovie = nameMovie;
        this.genreMovie = genreMovie;
        this.duration = duration;
        this.image = image;
        this.trailer = trailer;
        this.desc = desc;
        this.showtimes = new HashSet<>();
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(String genreMovie) {
        this.genreMovie = genreMovie;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void addShowtimes(Showtime showtime) {
        this.showtimes.add(showtime);
    }
}
