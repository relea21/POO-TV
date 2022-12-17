package pages;

import java.util.ArrayList;
import utils.*;
public class HomePageUnauthenticated extends Page {
    public HomePageUnauthenticated() {
        super();
    }

    /**
     * change status of page
     */
    @Override
    public void changeStatus() {

        ArrayList<Movie> movies = new ArrayList<>();

        Monitor.getMonitor().setCurrentUser(null);
        Monitor.getMonitor().setCurrentMovies(movies);

        Monitor.getMonitor().setAutentificated(false);
        Monitor.getMonitor().setMoviePage(false);
        Monitor.getMonitor().setUpgradePage(false);
        Monitor.getMonitor().setSeeDetailsMovie(false);
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(false);
    }
}
