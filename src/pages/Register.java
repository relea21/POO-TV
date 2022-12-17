package pages;

import input.Credentials;
import utils.*;
public class Register extends Page {
    public Register() {
        super();
    }
    /**
     * change status of page
     */
    @Override
    public void changeStatus() {
        Monitor.getMonitor().setAutentificated(false);
        Monitor.getMonitor().setMoviePage(false);
        Monitor.getMonitor().setUpgradePage(false);
        Monitor.getMonitor().setSeeDetailsMovie(false);
        Monitor.getMonitor().setRegister(true);
        Monitor.getMonitor().setLogin(false);
    }

    /**
     * @param credentials for a new user
     * register action
     */
    public void registerAction(final Credentials credentials) {
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
}
