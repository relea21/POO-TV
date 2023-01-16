package pages;

import input.ActionInput;

/**
 * This is the receiver for OnPageAction command
 */
public abstract class Page {
    public abstract void changeStatus();

    public abstract void actionOnPage(ActionInput action);

    //check if this page can be the next one
    public abstract boolean checkMoveOn();
}
