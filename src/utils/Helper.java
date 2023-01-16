package utils;

import actions.BackAction;
import actions.ChangePageAction;
import actions.DatabaseAction;
import input.Input;
import input.ActionInput;
import pages.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    /**
     * @param action to be done
     */
    public static void executeActionOnPage(final ActionInput action) {
        Monitor.getMonitor().getCurrentPage().actionOnPage(action);
    }

    /**
     * @param action to be done
     */
    public static void executeActionChangePage(final ActionInput action) {
        ChangePageAction command = new ChangePageAction(action);
        command.execute();
    }


    public static void executeActionDatabase(final ActionInput action) {
//        switch (action.getFeature()) {
//            case "add":
//                Database.getDataBase().addMovie(action.getAddedMovie());
//                break;
//            case "delete":
//                Database.getDataBase().deleteMovie(action.getDeletedMovie());
//                break;
//            default:
//                System.out.println("Invalid command");
//        }
        DatabaseAction command = new DatabaseAction(action);
        command.execute();
    }

    public static void executeActionBack(final ActionInput action) {
        BackAction command = new BackAction();
        command.execute();
    }
    /**
     * @param inputData action to be done
     */
    public static void  executeActions(final Input inputData) {
        for (ActionInput actionInput : inputData.getActions()) {
            if (actionInput.getType().equals("change page")) {
                executeActionChangePage(actionInput);
            }
            if (actionInput.getType().equals("on page")) {
                executeActionOnPage(actionInput);
            }
            if (actionInput.getType().equals("database")) {
                executeActionDatabase(actionInput);
            }
            if (actionInput.getType().equals("back")) {
                executeActionBack(actionInput);
            }
        }
        User currUser = Monitor.getMonitor().getCurrentUser();
        if(Monitor.getMonitor().isAutentificated() &&
                Monitor.getMonitor()
                        .getCurrentUser()
                        .getCredentials()
                        .getAccountType()
                        .equals("premium")) {
            HashMap<String, Integer> likedGenres = new HashMap<>();
            for(Movie movie : currUser.getLikedMovies()) {
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
            while(likedGenres.size() > 0) {
                Integer max = -1;
                String maximGenre = "No gender";
                for (Map.Entry<String, Integer> entry : likedGenres.entrySet()) {
                    if(entry.getValue() > max) {
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

            if(sortedGenres.size() == 0) {
                currUser.notificationRecommendationMovie("No recommendation");
                OutputPrinter.printRecommendation();
                return;
            }

            ArrayList<Movie> sortedMovies = new ArrayList<>(Database.getDataBase().getMovies());
            sortedMovies.sort(Comparator.comparingInt(Movie::getNumLikes).reversed());

            for (String genre : sortedGenres) {
                for (Movie movie : sortedMovies) {
                    if (movie.getGenres().contains(genre) &&
                            Database.getDataBase()
                                    .checkIfTheMovieExist(movie.getName(), currUser.getWatchedMovies()) == -1) {
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
}
