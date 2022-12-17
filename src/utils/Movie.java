package utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private Double rating;
    private int numRatings;
    @JsonIgnore
    private Double sumRating;

    public Movie(final String name, final int year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> actors,
                 final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = new ArrayList<>(genres);
        this.actors = new ArrayList<>(actors);
        this.countriesBanned = new ArrayList<>(countriesBanned);
        numLikes = 0;
        rating = 0.0;
        numRatings = 0;
        sumRating = 0.0;
    }

    public Movie(final Movie movie) {
        this.name = movie.name;
        this.year = movie.year;
        this.duration = movie.duration;
        this.genres = new ArrayList<>(movie.genres);
        this.actors = new ArrayList<>(movie.actors);
        this.countriesBanned = new ArrayList<>(movie.countriesBanned);
        numLikes = movie.numLikes;
        rating = movie.rating;
        numRatings = movie.numRatings;
        sumRating = movie.sumRating;
    }

    /**
     * @return get the name of movie
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of movie
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return year the film was released
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the film was released
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * @return duration of movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration of movie
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * @return genres of movie
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * @param genres of movie
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * @return actors who play in the film
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * @param actors playing in the film
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * @return countries where the movie is banned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * @param countriesBanned where the movie is banned
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * @return number of likes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * @param numLikes of the movie
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * @return rating of movie
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating of movie
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    /**
     * @return number of ratings
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * @param numRatings number of ratings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * @return sum of ratings
     */
    public Double getSumRating() {
        return sumRating;
    }

    /**
     * @param sumRating sum of ratings
     */
    public void setSumRating(final Double sumRating) {
        this.sumRating = sumRating;
    }
}
