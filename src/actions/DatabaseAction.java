package actions;

import input.ActionInput;
import utils.Database;

public class DatabaseAction implements Action {

    private ActionInput action;

    public DatabaseAction(ActionInput action) {
        this.action = action;
    }

    @Override
    public void execute() {
        switch (action.getFeature()) {
            case "add":
                Database.getDataBase().addMovie(action.getAddedMovie());
                break;
            case "delete":
                Database.getDataBase().deleteMovie(action.getDeletedMovie());
                break;
            default:
                System.out.println("Invalid command");
        }
    }

}
