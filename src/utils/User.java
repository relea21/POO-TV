package utils;

import input.Credentials;

import java.util.ArrayList;

public class User {
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    public User() {

    }

    public User(final User user) {
        this.credentials = new Credentials(user.credentials);

        this.ratedMovies = new ArrayList<>();
        for (Movie movie : user.ratedMovies) {
            Movie copyMovie = new Movie(movie);
            this.ratedMovies.add(copyMovie);
        }
        this.watchedMovies = new ArrayList<>();
        for (Movie movie : user.watchedMovies) {
            Movie copyMovie = new Movie(movie);
            this.watchedMovies.add(copyMovie);
        }
        this.likedMovies = new ArrayList<>();
        for (Movie movie : user.likedMovies) {
            Movie copyMovie = new Movie(movie);
            this.likedMovies.add(copyMovie);
        }
        this.purchasedMovies = new ArrayList<>();
        for (Movie movie : user.purchasedMovies) {
            Movie copyMovie = new Movie(movie);
            this.purchasedMovies.add(copyMovie);
        }

        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
    }

    public User(final Credentials credentials) {
        this.credentials = new Credentials(credentials);
        tokensCount = 0;
        numFreePremiumMovies = 15;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    /**
     * @return credentials of user
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * @param credentials of user
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @return number of tokens
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * @param tokensCount of user
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * @return number of free premium movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * @param numFreePremiumMovies number of free premium movies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * @return movies purchased by the user
     */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * @param purchasedMovies list of movies purchased by the user
     */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * @return movies watched by the user
     */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * @param watchedMovies list of movies watched by the user
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * @return movies liked by the user
     */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    /**
     * @param likedMovies list of movies liked by the user
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * @return movies rated by the user
     */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * @param ratedMovies list of movies rated by the user
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }


}
