package actions;

import input.ActionInput;
import pages.Monitor;

public class OnPageAction implements Action {
    private ActionInput action;

    public OnPageAction(final ActionInput action) {
        this.action = action;
    }

    /**
     * execute on page action
     */
    @Override
    public void execute() {
        Monitor.getMonitor().getCurrentPage().actionOnPage(action);
    }

}
