package pages;

import java.util.ArrayList;
import utils.*;
public class HomePageAutentificated extends Page {
    public HomePageAutentificated() {
        super();
    }

    /**
     * change status of page
     */
    @Override
    public void changeStatus() {

        ArrayList<Movie> movies = new ArrayList<>();
        Monitor.getMonitor().setCurrentMovies(movies);

        Monitor.getMonitor().setAutentificated(true);
        Monitor.getMonitor().setMoviePage(false);
        Monitor.getMonitor().setUpgradePage(false);
        Monitor.getMonitor().setSeeDetailsMovie(false);
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(false);
    }
}
