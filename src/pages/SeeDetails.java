package pages;

import java.util.ArrayList;

import input.ActionInput;
import utils.*;


public class SeeDetails extends Page {
    private Movie movie;
    public SeeDetails() {
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
        OutputPrinter.printAction();
    }



    /**
     * purchase action
     */
    private void purchaseAction() {
        User user = Monitor.getMonitor().getCurrentUser();
        int userTokens = user.getTokensCount();
        if (!user.getPurchasedMovies().contains(movie)) {
            if (user.getCredentials().getAccountType().equals("premium")
                    && user.getNumFreePremiumMovies() > 0) {
                int userFreeMovies = user.getNumFreePremiumMovies();
                user.setNumFreePremiumMovies(userFreeMovies - 1);
            } else {
                if (userTokens < Database.MOVIE_PRICE) {
                    OutputPrinter.printError();
                    return;
                }
                userTokens -= Database.MOVIE_PRICE;
                user.setTokensCount(userTokens);
            }
            user.getPurchasedMovies().add(movie);
            OutputPrinter.printAction();
        } else {
            OutputPrinter.printError();
        }
    }

    private void subscribeAction(String subscribedGenre) {
        User user = Monitor.getMonitor().getCurrentUser();

        if (!movie.getGenres().contains(subscribedGenre)) {
            OutputPrinter.printError();
            return;
        }

        if (Database.getDataBase().getSubscribeHashMap().containsKey(subscribedGenre)) {
            if (Database.getDataBase().getSubscribeHashMap().get(subscribedGenre).contains(user)) {
                OutputPrinter.printError();
            } else {
                Database.getDataBase().getSubscribeHashMap().get(subscribedGenre).add(user);
            }
        } else {
            ArrayList<Subscriber> subscribers = new ArrayList<>();
            subscribers.add(user);
            Database.getDataBase().getSubscribeHashMap().put(subscribedGenre, subscribers);
        }
    }

    /**
     * watch action
     */
    private void watchAction() {
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
    private void likeAction() {
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
    private void rateAction(final int rate) {
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
                user.getRateOfMovie().put(movie.getName(), rate);
            } else {
                Double sumRating = movie.getSumRating();
                int numRating = movie.getNumRatings();
                sumRating -= user.getRateOfMovie().get(movie.getName());
                sumRating += rate;
                movie.setSumRating(sumRating);
                movie.setRating(sumRating / numRating);
            }
            OutputPrinter.printAction();
        } else {
            OutputPrinter.printError();
        }
    }

    @Override
    public void actionOnPage(ActionInput action) {
        switch (action.getFeature()) {
            case "purchase":
                purchaseAction();
                break;
            case "watch":
                watchAction();
                break;
            case "like":
                likeAction();
                break;
            case "rate":
                rateAction(action.getRate());
                break;
            case "subscribe":
                subscribeAction(action.getSubscribedGenre());
                break;
            default:
                OutputPrinter.printError();
        }
    }

    @Override
    public boolean checkMoveOn() {
        return Monitor.getMonitor().isAutentificated();

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
