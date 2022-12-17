package pages;

import input.FilterInput;

import java.util.ArrayList;
import java.util.Comparator;
import utils.*;
public class Movies extends Page {
    public Movies() {
        super();
    }

    /**
     * change the status of page
     */
    @Override
    public void changeStatus() {
        Monitor.getMonitor().setMoviePage(true);
        Monitor.getMonitor().setUpgradePage(false);
        Monitor.getMonitor().setSeeDetailsMovie(false);
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(false);
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
    public void searchAction(final String startWith) {
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
    public void filterAction(final FilterInput filter) {
        createMoviesList();
        if (filter.getSort() != null) {
            filterActionSort(filter);
        }
        if (filter.getContains() != null) {
            filterActionContains(filter);
        }
        OutputPrinter.printAction();
    }

    private void filterActionSort(final FilterInput filter) {
        ArrayList<Movie> movies = Monitor.getMonitor().getCurrentMovies();
        if (filter.getSort().getDuration() != null && filter.getSort().getRating() == null) {
            if (filter.getSort().getDuration().equals("decreasing")) {
                movies.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
            } else {
                movies.sort((o1, o2) -> o1.getDuration() - o2.getDuration());
            }
        }
        if (filter.getSort().getRating() != null && filter.getSort().getDuration() == null) {
            if (filter.getSort().getRating().equals("decreasing")) {
                movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
            } else {
                movies.sort((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()));
            }
        }
        if (filter.getSort().getDuration() != null && filter.getSort().getRating() != null) {
            if (filter.getSort().getDuration().equals("decreasing")
                    && filter.getSort().getRating().equals("decreasing")) {
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (o1.getDuration() == o2.getDuration()) {
                            return -Double.compare(o1.getRating(), o2.getRating());
                        } else {
                            return o2.getDuration() - o1.getDuration();
                        }
                    }
                });
            }
            if (filter.getSort().getDuration().equals("increasing")
                    && filter.getSort().getRating().equals("decreasing")) {
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (o1.getDuration() == o2.getDuration()) {
                            return -Double.compare(o1.getRating(), o2.getRating());
                        } else {
                            return o1.getDuration() - o2.getDuration();
                        }
                    }
                });
            }
            if (filter.getSort().getDuration().equals("increasing")
                    && filter.getSort().getRating().equals("increasing")) {
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (o1.getDuration() == o2.getDuration()) {
                            return Double.compare(o1.getRating(), o2.getRating());
                        } else {
                            return o1.getDuration() - o2.getDuration();
                        }
                    }
                });
            }
            if (filter.getSort().getDuration().equals("decreasing")
                    && filter.getSort().getRating().equals("increasing")) {
                movies.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (o1.getDuration() == o2.getDuration()) {
                            return Double.compare(o1.getRating(), o2.getRating());
                        } else {
                            return o2.getDuration() - o1.getDuration();
                        }
                    }
                });
            }
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

}
