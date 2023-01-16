package pages;

import input.ActionInput;
import input.FilterInput;

import java.util.ArrayList;
import java.util.Comparator;
import utils.*;
public class Movies extends Page {
    public Movies() {
    }

    /**
     * change the status of page
     */
    @Override
    public void changeStatus() {
        Monitor.getMonitor().setMoviePage(true);
        createMoviesList();
        OutputPrinter.printAction();

    }

    /**
     * create movie list for pages.Movies page
     */
    private void createMoviesList() {
        ArrayList<Movie> movies = new ArrayList<>();
        User currentUser = Monitor.getMonitor().getCurrentUser();
        for (Movie movie : Database.getDataBase().getMovies()) {
            if (!movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry())) {
                movies.add(movie);
            }
        }
        Monitor.getMonitor().setCurrentMovies(movies);
    }

    /**
     * @param startWith string for searching movies
     */
    private void searchAction(final String startWith) {
        createMoviesList();
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (!movie.getName().startsWith(startWith)) {
                movies.remove(movie);
                i--;
            }
        }
        OutputPrinter.printAction();
    }

    /**
     * @param filter used for filter action
     */
    private void filterAction(final FilterInput filter) {
        createMoviesList();
        if (filter.getSort() != null) {
            filterActionSort(filter);
        }
        if (filter.getContains() != null) {
            filterActionContains(filter);
        }
        OutputPrinter.printAction();
    }

    private void sortRating(final boolean ratingOrder) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        if (ratingOrder) {
            //increasing order
            movies.sort((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()));
        } else {
            //decreasing order
            movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
        }
    }

    private void sortDuration(final boolean durationOrder) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        if (durationOrder) {
            movies.sort((o1, o2) -> o1.getDuration() - o2.getDuration());
        } else {
            movies.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
        }
    }

    private void sortRatingAndDuration(final boolean ratingOrder, final boolean durationOrder) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                if (o1.getDuration() == o2.getDuration()) {
                    if (ratingOrder) {
                        return Double.compare(o1.getRating(), o2.getRating());
                    } else {
                        return -Double.compare(o1.getRating(), o2.getRating());
                    }
                } else {
                    if (durationOrder) {
                        return o1.getDuration() - o2.getDuration();
                    } else {
                        return o2.getDuration() - o1.getDuration();
                    }
                }
            }
        });
    }

    private void filterActionSort(final FilterInput filter) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        if (filter.getSort().getDuration() != null && filter.getSort().getRating() == null) {
            boolean durationOrder;
            if (filter.getSort().getDuration().equals("decreasing")) {
                durationOrder =  false;
            } else {
                durationOrder = true;
            }
            sortDuration(durationOrder);
        }
        if (filter.getSort().getRating() != null && filter.getSort().getDuration() == null) {
            boolean ratingOrder;
            if (filter.getSort().getRating().equals("decreasing")) {
                ratingOrder = false;
            } else {
                ratingOrder = true;
            }
            sortRating(ratingOrder);
        }
        if (filter.getSort().getDuration() != null && filter.getSort().getRating() != null) {
            boolean durationOrder;
            boolean ratingOrder;
            if (filter.getSort().getDuration().equals("decreasing")) {
                durationOrder = false;
            } else {
                durationOrder = true;
            }
            if (filter.getSort().getRating().equals("decreasing")) {
                ratingOrder = false;
            } else {
                ratingOrder = true;
            }
            sortRatingAndDuration(ratingOrder, durationOrder);
        }
    }

    private void filterActionContains(final FilterInput filter) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        if (filter.getContains().getActors() != null) {
            for (String actor : filter.getContains().getActors()) {
                for (int i = 0; i < movies.size(); i++) {
                    if (!movies.get(i).getActors().contains(actor)) {
                        movies.remove(i);
                        i--;
                    }
                }
            }
        }

        if (filter.getContains().getGenre() != null) {
            for (String genre : filter.getContains().getGenre()) {
                for (int i = 0; i < movies.size(); i++) {
                    if (!movies.get(i).getGenres().contains(genre)) {
                        movies.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    /**
     * @param action to be done on this page
     */
    @Override
    public void actionOnPage(final ActionInput action) {
        switch (action.getFeature()) {
            case "search":
                searchAction(action.getStartsWith());
                break;
            case "filter":
                filterAction(action.getFilters());
                break;
            default:
                OutputPrinter.printError();
        }
    }

    /**
     * @return if it can move on this page
     */
    @Override
    public boolean checkMoveOn() {
        return Monitor.getMonitor().isAutentificated();
    }
}
