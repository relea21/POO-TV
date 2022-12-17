package utils;

import input.Input;
import input.ActionInput;
import pages.*;

public class Helper {

    /**
     * @param action to be done
     */
    public static void executeActionOnPage(final ActionInput action) {
        switch (action.getFeature()) {
            case "login":
                if (Monitor.getMonitor().isLogin()) {
                    Login loginPage = (Login) Monitor.getMonitor().getCurrentPage();
                    loginPage.loginAction(action.getCredentials());
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "register":
                if (Monitor.getMonitor().isRegister()) {
                    Register registerPage = (Register) Monitor.getMonitor().getCurrentPage();
                    registerPage.registerAction(action.getCredentials());
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "search":
                if (Monitor.getMonitor().isMoviePage()) {
                    Movies moviesPage = (Movies) Monitor.getMonitor().getCurrentPage();
                    moviesPage.searchAction(action.getStartsWith());
                 } else {
                    OutputPrinter.printError();
                }
                break;
            case "filter":
                if (Monitor.getMonitor().isMoviePage()) {
                    Movies moviesPage = (Movies) Monitor.getMonitor().getCurrentPage();
                    moviesPage.filterAction(action.getFilters());
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "buy tokens":
                if (Monitor.getMonitor().isUpgradePage()) {
                    Upgrades upgradePage = (Upgrades) Monitor.getMonitor().getCurrentPage();
                    upgradePage.buyTokensAction(action);
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "buy premium account":
                if (Monitor.getMonitor().isUpgradePage()) {
                    Upgrades upgradePage = (Upgrades) Monitor.getMonitor().getCurrentPage();
                    upgradePage.buyPremiumAccount();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "purchase":
                if (Monitor.getMonitor().isSeeDetailsMovie()) {
                    SeeDetails seeDetailsPage = (SeeDetails) Monitor.getMonitor().getCurrentPage();
                    seeDetailsPage.purchaseAction();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "watch":
                if (Monitor.getMonitor().isSeeDetailsMovie()) {
                    SeeDetails seeDetailsPage = (SeeDetails) Monitor.getMonitor().getCurrentPage();
                    seeDetailsPage.watchAction();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "like":
                if (Monitor.getMonitor().isSeeDetailsMovie()) {
                    SeeDetails seeDetailsPage = (SeeDetails) Monitor.getMonitor().getCurrentPage();
                    seeDetailsPage.likeAction();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "rate":
                if (Monitor.getMonitor().isSeeDetailsMovie()) {
                    SeeDetails seeDetailsPage = (SeeDetails) Monitor.getMonitor().getCurrentPage();
                    seeDetailsPage.rateAction(action.getRate());
                } else {
                    OutputPrinter.printError();
                }
                break;
            default:
                System.out.println("action invalid");
        }
    }

    /**
     * @param action to be done
     */
    public static void executeActionChangePage(final ActionInput action) {
        Page newPage = null;

        switch (action.getPage()) {
            case "login":
                if (!Monitor.getMonitor().isAutentificated()) {
                    newPage = new Login();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "register":
                if (!Monitor.getMonitor().isAutentificated()) {
                    newPage = new Register();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "logout":
                if (Monitor.getMonitor().isAutentificated()) {
                    newPage = new HomePageUnauthenticated();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "movies":
                if (Monitor.getMonitor().isAutentificated()) {
                    newPage = new Movies();
                } else {
                    OutputPrinter.printError();
                }
                break;
            case "see details":
                boolean sw = false;
                if (Monitor.getMonitor().isMoviePage()) {
                    for (Movie movie : Monitor.getMonitor().getCurrentMovies()) {
                        if (movie.getName().equals(action.getMovie())) {
                            newPage = new SeeDetails(action.getMovie());
                            sw = true;
                        }
                    }
                }
                if (!sw) {
                    OutputPrinter.printError();
                }
                break;
            case "upgrades":
                if (Monitor.getMonitor().isAutentificated()) {
                    newPage = new Upgrades();
                } else {
                    OutputPrinter.printError();
                }
                break;
            default:
                System.out.println(action.getPage());
                System.out.println("Nu exista pagina asta!!!");
        }

        if (newPage != null) {
            newPage.changeStatus();
            Monitor.getMonitor().setCurrentPage(newPage);
        }
    }


    /**
     * @param inputData action to be done
     */
    public static void  executeAction(final Input inputData) {
        for (ActionInput actionInput : inputData.getActions()) {
            if (actionInput.getType().equals("change page")) {
                executeActionChangePage(actionInput);
            }
            if (actionInput.getType().equals("on page")) {
                executeActionOnPage(actionInput);
            }
        }
    }
}
