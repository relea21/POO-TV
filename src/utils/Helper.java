package utils;

import actions.Action;
import actions.ActionFactory;
import input.Input;
import input.ActionInput;
import pages.Monitor;

import java.util.*;


class Invoker {

    // list of all commands that have been performed;
    private LinkedList<Action> commands;

    public Invoker() {
        commands = new LinkedList<>();
    }

    public void execute(final Action command) {
        commands.push(command);
        command.execute();
    }
}

public class Helper {

    private Invoker invoker;
    private Input inputData;

    public Helper(final Input inputData) {
        invoker = new Invoker();
        this.inputData = inputData;
    }

    /**
     * execute actions from input
     */
    public void  executeActions() {
        for (ActionInput actionInput : inputData.getActions()) {
            Action command = ActionFactory.getActionFactory().getAction(actionInput);
            invoker.execute(command);
        }
    }

    /**
     * show recommendation if is needed, after the commands are finished
     */
    public void showRecommendation() {
        User currUser = Monitor.getMonitor().getCurrentUser();
        if (Monitor.getMonitor().isAutentificated()
                && Monitor.getMonitor()
                        .getCurrentUser()
                        .getCredentials()
                        .getAccountType()
                        .equals("premium")) {

            ArrayList<String> sortedGenres = getMostLikedGenres();
            if (sortedGenres.size() == 0) {
                currUser.notificationRecommendationMovie("No recommendation");
                OutputPrinter.printRecommendation();
                return;
            }

            ArrayList<Movie> sortedMovies = new ArrayList<>(Database.getDataBase().getMovies());
            sortedMovies.sort(Comparator.comparingInt(Movie::getNumLikes).reversed());

            for (String genre : sortedGenres) {
                for (Movie movie : sortedMovies) {
                    if (movie.getGenres().contains(genre)
                            && Database.getDataBase()
                            .checkIfTheMovieExist(movie.getName(),
                                    currUser.getWatchedMovies()) == -1) {
                        currUser.notificationRecommendationMovie(movie.getName());
                        OutputPrinter.printRecommendation();
                        return;
                    }
                }
            }

            currUser.notificationRecommendationMovie("No recommendation");
            OutputPrinter.printRecommendation();
        }
    }

    private ArrayList<String> getMostLikedGenres() {
        User currUser = Monitor.getMonitor().getCurrentUser();
        HashMap<String, Integer> likedGenres = new HashMap<>();
        for (Movie movie : currUser.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                if (likedGenres.containsKey(genre)) {
                    int numLikes = likedGenres.get(genre);
                    numLikes++;
                    likedGenres.put(genre, numLikes);
                } else {
                    Integer numLikes = 0;
                    likedGenres.put(genre, numLikes);
                }
            }
        }
        ArrayList<String> sortedGenres = new ArrayList<>();
        while (likedGenres.size() > 0) {
            Integer max = -1;
            String maximGenre = "No gender";
            for (Map.Entry<String, Integer> entry : likedGenres.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    maximGenre = entry.getKey();
                } else {
                    if (entry.getValue() == max && entry.getKey().compareTo(maximGenre) < 0) {
                        max = entry.getValue();
                        maximGenre = entry.getKey();
                    }
                }
            }
            sortedGenres.add(maximGenre);
            likedGenres.remove(maximGenre);
        }
        return sortedGenres;
    }
}
