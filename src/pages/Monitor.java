package pages;

import java.util.ArrayList;
import utils.*;
public class Monitor {
    private static Monitor instance = null;
    private Monitor() {

    }
    public static Monitor getMonitor() {
        if (instance == null) {
            instance = new Monitor();
        }
        return instance;
    }

    private User currentUser;
    private Page currentPage;
    private ArrayList<Movie> currentMovies;

    private boolean autentificated, moviePage;

    public void startAplication() {
        currentPage = new HomePageUnauthenticated();
        currentPage.changeStatus();
        currentUser = null;
        currentMovies = new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    public void setCurrentMovies(final ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }

    public boolean isAutentificated() {
        return autentificated;
    }

    public void setAutentificated(final boolean autentificated) {
        this.autentificated = autentificated;
    }

    public boolean isMoviePage() {
        return moviePage;
    }

    public void setMoviePage(final boolean moviePage) {
        this.moviePage = moviePage;
    }

}
