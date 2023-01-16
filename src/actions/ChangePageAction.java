package actions;

import input.ActionInput;

import pages.Monitor;
import pages.Page;
import pages.HomePageUnauthenticated;
import pages.SeeDetails;
import pages.PageFactory;

import utils.Database;
import utils.Movie;
import utils.OutputPrinter;

public class ChangePageAction implements  Action {
    private ActionInput action;

    public ChangePageAction(final ActionInput action) {
        this.action = action;
    }

    /**
     * execute change page action
     */
    @Override
    public void execute() {
        Page newPage = null;
        Page currentPage = Monitor.getMonitor().getCurrentPage();
        if (action.getPage().equals("see details") || action.getPage().equals("logout")) {
            if (action.getPage().equals("logout")) {
                if (Monitor.getMonitor().isAutentificated()) {
                    newPage = new HomePageUnauthenticated();
                } else {
                    OutputPrinter.printError();
                }
            }

            if (action.getPage().equals("see details")) {
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
            }
            // can move on that page
            if (newPage != null) {
                if (Monitor.getMonitor().isAutentificated()) {
                    Database.getDataBase().getPagesHistory().push(currentPage);
                }
                newPage.changeStatus();
                Monitor.getMonitor().setCurrentPage(newPage);
            }
        }  else {
            newPage = PageFactory.getPageFactory().createNewPage(action.getPage());
            if (newPage.checkMoveOn()) {
                // can move on that page
                if (Monitor.getMonitor().isAutentificated()) {
                    Database.getDataBase().getPagesHistory().push(currentPage);
                }
                newPage.changeStatus();
                Monitor.getMonitor().setCurrentPage(newPage);
            } else {
                OutputPrinter.printError();
            }
        }
    }

}
