package utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * this class represent an observer
 * for Observer pattern
 */
public abstract class Subscriber {
    @JsonIgnore
    private boolean hasBeenNoticated = false;

    abstract void notificationAddMovie(String movieName);

    /**
     * @return if the user has been notificated
     */
    public boolean isHasBeenNoticated() {
        return hasBeenNoticated;
    }

    /**
     * @param hasBeenNoticated set if the user has been notificated
     */
    public void setHasBeenNoticated(final boolean hasBeenNoticated) {
        this.hasBeenNoticated = hasBeenNoticated;
    }
}
