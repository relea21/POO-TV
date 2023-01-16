package pages;

import java.util.ArrayList;
import java.util.LinkedList;

import input.ActionInput;
import utils.*;
public class HomePageUnauthenticated extends Page {
    public HomePageUnauthenticated() {
    }

    /**
     * change status of page
     */
    @Override
    public void changeStatus() {

        ArrayList<Movie> movies = new ArrayList<>();

        Monitor.getMonitor().setCurrentUser(null);
        Monitor.getMonitor().setCurrentMovies(movies);

        LinkedList<Page> pageHistory= new LinkedList<>();
        Database.getDataBase().setPagesHistory(pageHistory);

        Monitor.getMonitor().setAutentificated(false);
        Monitor.getMonitor().setMoviePage(false);
    }

    @Override
    public void actionOnPage(ActionInput action) {
        OutputPrinter.printError();
    }

    @Override
    public boolean checkMoveOn() {
        return false;
    }
}
