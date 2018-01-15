package cssm.actions;


import org.junit.Test;

import yaes.cssm.actions.ActionType;
import yaes.ui.text.TextUi;

/**
 * 
*  Tests the creation and printing of a single action type
*
 * @author Taranjeet
 *
 */

public class testActionType {

	@Test
	public void test() {
		ActionType action = new ActionType("A1","seller offers flowers");
		TextUi.println(action.toString());
		action = new ActionType("A7", "client declines(loudness, offensiveness)",
				"loudness", "offensiveness");
		TextUi.println(action.toString());
	}

}
