package utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Subscriber {
    @JsonIgnore
    private boolean hasBeenNoticated = false;

    abstract void notificationAddMovie(String movieName);

    abstract void notificationDeleteMovie(String movieName);

    public boolean isHasBeenNoticated() {
        return hasBeenNoticated;
    }

    public void setHasBeenNoticated(boolean hasBeenNoticated) {
        this.hasBeenNoticated = hasBeenNoticated;
    }
}
