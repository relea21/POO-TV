package pages;

import input.Credentials;
import utils.*;
public class Login extends Page {
    public Login() {
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
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(true);
    }

    /**
     * @param credentials of user that try to login
     * pages.Login action
     */
    public void loginAction(final Credentials credentials) {
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
}
