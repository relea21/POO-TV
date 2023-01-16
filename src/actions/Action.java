package actions;

import input.ActionInput;

public interface Action {
    void execute();
    boolean isChangePageAction();
}
