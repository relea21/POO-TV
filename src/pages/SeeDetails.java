package pages;

import java.util.ArrayList;
import utils.*;


public class SeeDetails extends Page {
    private Movie movie;
    public SeeDetails() {
        super();
    }

    public SeeDetails(final String movieTitle) {
        for (Movie movie : Database.getDataBase().getMovies()) {
            if (movie.getName().equals(movieTitle)) {
                this.movie = movie;
            }
        }
    }

    /**
     * change status of page
     */
    @Override
    public void changeStatus() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        Monitor.getMonitor().setCurrentMovies(movies);

        Monitor.getMonitor().setMoviePage(false);
        Monitor.getMonitor().setUpgradePage(false);
        Monitor.getMonitor().setSeeDetailsMovie(true);
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(false);

        OutputPrinter.printAction();
    }

    /**
     * purchase action
     */
    public void purchaseAction() {
        User user = Monitor.getMonitor().getCurrentUser();
        int userTokens = user.getTokensCount();
        if (user.getCredentials().getAccountType().equals("premium")
                        && user.getNumFreePremiumMovies() > 0) {
            int userFreeMovies = user.getNumFreePremiumMovies();
            user.setNumFreePremiumMovies(userFreeMovies - 1);
        } else {
            if (userTokens < 2) {
                OutputPrinter.printError();
                return;
            }
            userTokens -= 2;
            user.setTokensCount(userTokens);
        }
        user.getPurchasedMovies().add(movie);
        OutputPrinter.printAction();
    }

    /**
     * watch action
     */
    public void watchAction() {
        User user = Monitor.getMonitor().getCurrentUser();
        if (user.getPurchasedMovies().contains(movie)) {
            if (!user.getWatchedMovies().contains(movie)) {
                user.getWatchedMovies().add(movie);
            }
            OutputPrinter.printAction();
        } else {
            OutputPrinter.printError();
        }
    }

    /**
     * like action
     */
    public void likeAction() {
        User user = Monitor.getMonitor().getCurrentUser();

        if (user.getWatchedMovies().contains(movie)) {
            if (!user.getLikedMovies().contains(movie)) {
                user.getLikedMovies().add(movie);
                int numLikes = movie.getNumLikes();
                movie.setNumLikes(numLikes + 1);
            }
            OutputPrinter.printAction();
        } else {
            OutputPrinter.printError();
        }
    }

    /**
     * @param rate for movie
     * rate action
     */
    public void rateAction(final int rate) {
        User user = Monitor.getMonitor().getCurrentUser();
        if (rate > Database.MAX_RATE) {
            OutputPrinter.printError();
            return;
        }

        if (user.getWatchedMovies().contains(movie)) {
            if (!user.getRatedMovies().contains(movie)) {
                user.getRatedMovies().add(movie);
                Double sumRating = movie.getSumRating() + rate;
                int numRating = movie.getNumRatings() + 1;
                movie.setNumRatings(numRating);
                movie.setSumRating(sumRating);
                movie.setRating(sumRating / numRating);
            }
            OutputPrinter.printAction();
        } else {
            OutputPrinter.printError();
        }
    }

    /**
     * @return movie in seeDetails page
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @param movie for seeDetails page
     */
    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
}
