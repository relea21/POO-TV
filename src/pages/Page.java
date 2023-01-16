package pages;

import input.ActionInput;

/**
 * This is the receiver for OnPageAction command
 */
public abstract class Page {
    /**
     * change status of application
     */
    public abstract void changeStatus();

    /**
     * @param action to be done on the page
     */
    public abstract void actionOnPage(ActionInput action);

    /**
     * @return if this page can be the next one
     */
    public abstract boolean checkMoveOn();
}
