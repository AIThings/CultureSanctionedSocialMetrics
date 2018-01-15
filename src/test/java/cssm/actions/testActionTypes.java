package cssm.actions;


import org.junit.Test;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.ui.format.Formatter;
import yaes.ui.text.TextUi;

/**
 * ActionSet class test
 * 
 * @author Taranjeet
 * 
 */
public class testActionTypes {


    /**
     *  Test for the creation and printing of an action set
     */
    @Test
    public void test() {
        ActionTypes actionTypes = new ActionTypes();
        ActionType action = new ActionType("a1", "seller offers flowers");
        actionTypes.addActionType(action, false);
        action = new ActionType("a2", "client accepts");
        actionTypes.addActionType(action, false);
        action = new ActionType("a3", "seller and client arrange payment");
        actionTypes.addActionType(action, true);
        action = new ActionType("a4", "client declines");
        actionTypes.addActionType(action, true);
        action = new ActionType("a5", "seller offers gift");
        actionTypes.addActionType(action, true);
        // printing
        Formatter fmt = new Formatter();
        fmt.add(actionTypes.toString());
        TextUi.println(fmt);
    }

}
