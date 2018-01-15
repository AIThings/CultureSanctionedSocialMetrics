package cssm.cssm;
import org.junit.Test;

import cssm.actions.TestScenarioHelper;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.ui.text.TextUi;

/**
 * 
 * Test for the creation of the concrete belief and its printing
 * 
 * @author Taranjeet
 * 
 */
public class testConcreteBelief implements Constants {

	@Test
	public void test() {
		Scenario scenario = TestScenarioHelper.createScenario();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, Q_GIFT, Actors.SELLER, Actors.CLIENT);
		TextUi.println(cb);
	}

}
