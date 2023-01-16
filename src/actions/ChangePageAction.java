package actions;

import input.ActionInput;
import pages.*;
import utils.Database;
import utils.Movie;
import utils.OutputPrinter;

public class ChangePageAction implements  Action {
    private ActionInput action;

    public ChangePageAction(ActionInput action) {
        this.action = action;
    }

    @Override
    public void execute() {
        Page newPage = null;
        Page currentPage = Monitor.getMonitor().getCurrentPage();
        if(action.getPage().equals("see details") || action.getPage().equals("logout")) {
            if(action.getPage().equals("logout")) {
                if (Monitor.getMonitor().isAutentificated()) {
                    newPage = new HomePageUnauthenticated();
                } else {
                    OutputPrinter.printError();
                }
            }

            if(action.getPage().equals("see details")) {
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

            if(newPage != null) {
                if(Monitor.getMonitor().isAutentificated())
                    Database.getDataBase().getPagesHistory().push(currentPage);
                newPage.changeStatus();
                Monitor.getMonitor().setCurrentPage(newPage);
            }
        }  else {
            newPage = PageFactory.getPageFactory().createNewPage(action.getPage());
            if(newPage.checkMoveOn()) {
                if(Monitor.getMonitor().isAutentificated())
                    Database.getDataBase().getPagesHistory().push(currentPage);
                newPage.changeStatus();
                Monitor.getMonitor().setCurrentPage(newPage);
            } else {
                OutputPrinter.printError();
            }
        }
    }

    @Override
    public boolean isChangePageAction() {
        return true;
    }
}
