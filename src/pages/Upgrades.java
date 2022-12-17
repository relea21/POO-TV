package pages;

import input.ActionInput;

import java.util.ArrayList;

import  utils.*;
public class Upgrades extends Page {
    public Upgrades() {
        super();
    }

    /**
     * change status of page
     */
    @Override
    public void changeStatus() {
        ArrayList<Movie> movies = new ArrayList<>();
        Monitor.getMonitor().setCurrentMovies(movies);

        Monitor.getMonitor().setMoviePage(false);
        Monitor.getMonitor().setUpgradePage(true);
        Monitor.getMonitor().setSeeDetailsMovie(false);
        Monitor.getMonitor().setRegister(false);
        Monitor.getMonitor().setLogin(false);
    }

    /**
     * @param action to be done
     * buy tokens action
     */
    public void buyTokensAction(final ActionInput action) {
        User user = Monitor.getMonitor().getCurrentUser();
        int tokensToBuy = Integer.parseInt(action.getCount());
        int balance = Integer.parseInt(user.getCredentials().getBalance());
        if (tokensToBuy > balance) {
            OutputPrinter.printError();
        } else {
            int userTokens = user.getTokensCount();
            balance -= tokensToBuy;
            user.setTokensCount(userTokens + tokensToBuy);
            user.getCredentials().setBalance(Integer.toString(balance));
        }
    }

    /**
     * buy premium account action
     */
    public void buyPremiumAccount() {
        User user = Monitor.getMonitor().getCurrentUser();
        int userTokens = user.getTokensCount();
        if (userTokens < Database.PREMIUM_ACCOUNT_PRICE) {
            OutputPrinter.printError();
        } else {
            userTokens -= Database.PREMIUM_ACCOUNT_PRICE;
            user.setTokensCount(userTokens);
            user.getCredentials().setAccountType("premium");
        }
    }
}
