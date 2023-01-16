package actions;

import input.ActionInput;


/**
 * Factory pattern for actions
 */
public class ActionFactory {
    private static  ActionFactory instance = null;

    private ActionFactory() {

    }

    public static ActionFactory getActionFactory() {
        if(instance == null)
            instance = new ActionFactory();
        return instance;
    }

    public Action getAction(ActionInput action) {
        switch(action.getType()) {
            case "change page":
                return new ChangePageAction(action);
            case "on page":
                return new OnPageAction(action);
            case "database":
                return new DatabaseAction(action);
            case "back":
                return new BackAction();
            default:
                return null;
        }
    }
}
