package actions;

import pages.Monitor;
import pages.Page;
import utils.Database;
import utils.OutputPrinter;

public class BackAction implements  Action{
    @Override
    public void execute() {
        if (Database.getDataBase().getPagesHistory().size() == 0) {
            OutputPrinter.printError();
            return;
        }

        Page previousPage = Database.getDataBase().getPagesHistory().pop();
        previousPage.changeStatus();
        Monitor.getMonitor().setCurrentPage(previousPage);
    }

    @Override
    public boolean isChangePageAction() {
        return false;
    }
}