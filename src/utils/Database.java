package utils;

import input.Input;
import input.UserInput;
import java.util.ArrayList;
import java.util.HashMap;
import input.MovieInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Database {
    private static Database dataBase = null;
    private Database() { };

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

    public static final int MAX_RATE = 5;

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

}
