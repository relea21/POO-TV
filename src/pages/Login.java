package pages;

import input.ActionInput;
import input.Credentials;
import utils.*;
public class Login extends Page {
    public Login() {
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
     * @param credentials of user that try to log in
     * pages.Login action
     */
    private void loginAction(final Credentials credentials) {
        String name = credentials.getName();

        Page newPage;
        if (Database.getDataBase().getUsersHashMap().containsKey(name)) {
            User user = Database.getDataBase().getUsersHashMap().get(name);
            if (user.getCredentials().getPassword().equals(credentials.getPassword())) {
                newPage = new HomePageAutentificated();
                Monitor.getMonitor().setCurrentUser(user);
            } else {
                OutputPrinter.printError();
                newPage = new HomePageUnauthenticated();
            }
        } else {
            OutputPrinter.printError();
            newPage = new HomePageUnauthenticated();
        }

        newPage.changeStatus();
        Monitor.getMonitor().setCurrentPage(newPage);
        if (Monitor.getMonitor().isAutentificated()) {
            OutputPrinter.printAction();
        }
    }

    /**
     * @param action to be done on this page
     */
    @Override
    public void actionOnPage(final ActionInput action) {
        switch (action.getFeature()) {
            case "login":
                loginAction(action.getCredentials());
                break;
            default:
                OutputPrinter.printError();
        }
    }

    /**
     * @return if it can move on this page
     */
    @Override
    public boolean checkMoveOn() {
        return !Monitor.getMonitor().isAutentificated();
    }
}
