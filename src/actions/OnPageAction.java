package actions;

import input.ActionInput;
import pages.Monitor;

public class OnPageAction implements Action{
    private ActionInput action;

    public OnPageAction(ActionInput action) {
        this.action = action;
    }

    @Override
    public void execute() {
        Monitor.getMonitor().getCurrentPage().actionOnPage(action);
    }

    @Override
    public boolean isChangePageAction() {
        return false;
    }
}
