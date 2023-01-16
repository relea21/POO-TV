package utils;

import input.Input;
import input.UserInput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import input.MovieInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pages.Page;


public class Database {
    private static Database dataBase = null;
    private Database() { }

    /**
     * @return Singleton instance to get utils.Database
     */
    public static Database getDataBase() {
         if (dataBase == null) {
             dataBase = new Database();
         }
         return  dataBase;
    }

    private HashMap<String, User> usersHashMap;
    private ArrayList<Movie> movies;
    private ObjectMapper objectMapper;
    private ArrayNode output;
    private HashMap<String, ArrayList<Subscriber>> subscribeHashMap;
    private LinkedList<Page> pagesHistory = new LinkedList<>();

    public static final int MAX_RATE = 5;
    public static final int MOVIE_PRICE = 2;
    public static final int PREMIUM_ACCOUNT_PRICE = 10;

    /**
     * @param input input from the fille to initialize all users in database
     */
    public void initializeUserHashMap(final Input input) {
        usersHashMap = new HashMap<>();
        ArrayList<UserInput> usersInput = input.getUsers();
        for (UserInput userInput : usersInput) {
            User newUser = new User(userInput.getCredentials());
            usersHashMap.put(newUser.getCredentials().getName(), newUser);
        }
    }

    /**
     * @param input input from the fille to initialize all movies in database
     */
    public void initializeMovies(final Input input) {
        movies = new ArrayList<>();
        for (MovieInput movieInput : input.getMovies()) {
            Movie newMovie = new Movie(movieInput.getName(), movieInput.getYear(),
                    movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(),
                    movieInput.getCountriesBanned());
            movies.add(newMovie);
        }
    }

    public void initializeSubscribeHashMap() {
        subscribeHashMap = new HashMap<>();
    }

    //check if the movie exists in an ArrayList of movies
    //return position of movie in list if exists
    //return -1 if movie doesn't exist
    public int checkIfTheMovieExist(String movieName, ArrayList<Movie> movies) {
        for(int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getName().equals(movieName)) {
                return i;
            }
        }
        return -1;
    }

    public void addMovie(MovieInput movieInput) {

        if(checkIfTheMovieExist(movieInput.getName(), movies) != -1){
            OutputPrinter.printError();
            return;
        }

        Movie newMovie = new Movie(movieInput.getName(), movieInput.getYear(),
                movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(),
                movieInput.getCountriesBanned());

        movies.add(newMovie);
        // this list contains all the subscribers that have been notificated about this movie
        ArrayList<Subscriber> notificatedSubcribers = new ArrayList<>();

        for(String genre : newMovie.getGenres()) {
            if(subscribeHashMap.containsKey(genre)) {
                ArrayList<Subscriber> subscribers = subscribeHashMap.get(genre);
                for(Subscriber subscriber : subscribers) {
                    if(!subscriber.isHasBeenNoticated()) {
                        subscriber.notificationAddMovie(newMovie.getName());
                        subscriber.setHasBeenNoticated(true);
                        notificatedSubcribers.add(subscriber);
                    }
                }
            }
        }

        for(Subscriber subscriber : notificatedSubcribers)
            subscriber.setHasBeenNoticated(false);
    }

    public void deleteMovie(String deletedMovie) {
        ArrayList<User> users = new ArrayList<>(usersHashMap.values());
        int indexInDatabase = checkIfTheMovieExist(deletedMovie, movies);
        if(indexInDatabase == -1) {
            OutputPrinter.printError();
            return;
        }

        movies.remove(indexInDatabase);

        for(User user : users) {
            // index is used to check position of deleted movie
            // in purchased movies , watched movies, liked movies, rated movies
            int index = checkIfTheMovieExist(deletedMovie,
                                            user.getPurchasedMovies());
            if(index != -1) {
                user.getPurchasedMovies().remove(index);
                user.notificationDeleteMovie(deletedMovie);
                if(user.getCredentials().getAccountType().equals("premium")) {
                    int numFreeMovie = user.getNumFreePremiumMovies();
                    user.setNumFreePremiumMovies(numFreeMovie + 1);
                } else {
                    int tokensCount = user.getTokensCount();
                    user.setTokensCount(tokensCount + MOVIE_PRICE);
                }
            }

            index = checkIfTheMovieExist(deletedMovie,
                                    user.getWatchedMovies());
            if(index != -1) {
                user.getWatchedMovies().remove(index);
            }

            index = checkIfTheMovieExist(deletedMovie,
                                    user.getLikedMovies());
            if(index != -1) {
                user.getLikedMovies().remove(index);
            }

            index = checkIfTheMovieExist(deletedMovie,
                                    user.getRatedMovies());
            if(index != -1) {
                user.getRatedMovies().remove(index);
            }
        }
    }

    public HashMap<String, User> getUsersHashMap() {
        return usersHashMap;
    }

    public void setUsersHashMap(final HashMap<String, User> usersHashMap) {
        this.usersHashMap = usersHashMap;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public HashMap<String, ArrayList<Subscriber>> getSubscribeHashMap() {
        return subscribeHashMap;
    }

    public void setSubscribeHashMap(HashMap<String, ArrayList<Subscriber>> subscribeHashMap) {
        this.subscribeHashMap = subscribeHashMap;
    }

    public LinkedList<Page> getPagesHistory() {
        return pagesHistory;
    }

    public void setPagesHistory(LinkedList<Page> pagesHistory) {
        this.pagesHistory = pagesHistory;
    }
}
