package cssm.actions;

import org.junit.Test;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionType;
import yaes.ui.text.TextUi;

/**
 * 
 * Test for the action class: it shows how it can be created using 
 * an action type
 * 
 * @author Taranjeet
 *
 */
public class testAction {

	
	/**
	 * Creates two action types and an instance of each of them. The action instance 
	 * is labeled with the name of the scenario
	 */
	@Test
	public void test() {
		String scenarioInstance = "SS1";
		// action with no parameters
		ActionType actionType = new ActionType("A3", "seller and client arrange payment");		
		Action actionInstance = new Action(scenarioInstance, "seller", actionType);
		TextUi.println(actionInstance);
		// action with two parameters
		actionType = new ActionType("A7", "client declines(loudness, offensiveness)",
				"loudness", "offensiveness");
		actionInstance = new Action(scenarioInstance, "client", actionType, 0.2, 0.4);
		TextUi.println(actionInstance);

	}

}
