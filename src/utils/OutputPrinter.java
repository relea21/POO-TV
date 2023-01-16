package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pages.Monitor;

import java.util.ArrayList;

public class OutputPrinter {
    /**
     * command used to print an error
     */
    public static void printError() {
        String error = "Error";
        ArrayList<Movie> currentMoviesList = new ArrayList<>();
        User currentUser = null;
        printMessage(error, currentMoviesList, currentUser);
    }

    /**
     * command used to print an action ended with success;
     */
    public static void printAction() {
        String error = null;
        ArrayList<Movie> currentMoviesList = Monitor.getMonitor().getCurrentMovies();
        User currentUser = Monitor.getMonitor().getCurrentUser();
        printMessage(error, currentMoviesList, currentUser);
    }

    public static void printRecommendation() {
        String error = null;
        ArrayList<Movie> currentMoviesList = null;
        User currentUser = Monitor.getMonitor().getCurrentUser();
        printMessage(error, currentMoviesList, currentUser);
    }
    private static void printMessage(final String error, final ArrayList<Movie> currentMoviesList,
                                     final User currentUser) {
        ObjectMapper objectMapper = Database.getDataBase().getObjectMapper();
        ArrayNode output = Database.getDataBase().getOutput();
        ObjectNode printer = objectMapper.createObjectNode();


        printer.put("error", error);

        if(currentMoviesList != null) {
            ArrayList<Movie> copyCurrentMoviesList = new ArrayList<>();

            for (Movie movie : currentMoviesList) {
                Movie copyMovie = new Movie(movie);
                copyCurrentMoviesList.add(copyMovie);
            }
            printer.putPOJO("currentMoviesList", copyCurrentMoviesList);
        } else {
            printer.putPOJO("currentMoviesList", null);
        }

        if (currentUser != null)
            printer.putPOJO("currentUser", new User(currentUser));
        else
            printer.putPOJO("currentUser", null);
        output.add(printer);
    }
}
