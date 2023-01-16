package utils;

public class Notification {
    private String movieName;
    private String message;

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notification(final Notification notification) {
        this.message = notification.message;
        this.movieName = notification.movieName;
    }

    /**
     * @return name of the movie of notification
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @param movieName of notification
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * @return the message of notification
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message of the notification
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
