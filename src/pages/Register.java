package pages;

import input.ActionInput;
import input.Credentials;
import utils.*;
public class Register extends Page {
    public Register() {

    }
    /**
     * change status of page
     */
    @Override
    public void changeStatus() {
        Monitor.getMonitor().setAutentificated(false);
        Monitor.getMonitor().setMoviePage(false);
    }

    /**
     * @param credentials for a new user
     * register action
     */
    private void registerAction(final Credentials credentials) {
        String name = credentials.getName();
        Page newPage;
        if (Database.getDataBase().getUsersHashMap().containsKey(name)) {
            OutputPrinter.printError();
            newPage = new HomePageUnauthenticated();
        } else {
            User newUser = new User(credentials);
            Database.getDataBase().getUsersHashMap().put(name, newUser);
            newPage = new HomePageAutentificated();
            Monitor.getMonitor().setCurrentUser(newUser);
        }

        newPage.changeStatus();
        Monitor.getMonitor().setCurrentPage(newPage);
        if (Monitor.getMonitor().isAutentificated())
            OutputPrinter.printAction();
    }

    @Override
    public void actionOnPage(ActionInput action) {
        switch (action.getFeature()) {
            case "register":
                registerAction(action.getCredentials());
                break;
            default:
                OutputPrinter.printError();
        }
    }

    @Override
    public boolean checkMoveOn() {
        return !Monitor.getMonitor().isAutentificated();
    }
}
