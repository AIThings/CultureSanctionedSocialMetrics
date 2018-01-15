package cssm.actions;


import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ParseActions;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.ui.text.TextUi;
/**
 * ParseActions class test
 * @author Taranjeet
 *
 */
public class testParseActions {

	/**
	 * Tests the creation of h
	 */
	@Test
	public void testParseActionsSingleScenario() {
        ScenarioSet scenarioSet = new ScenarioSet();
        // create the first instance of the scenario 
		Scenario scenarioOne = TestScenarioHelper.createScenario();
		scenarioOne.setScenarioInstance("one");
        scenarioSet.addScenario(scenarioOne);
        // create the second instance of the scenario 
		Scenario scenarioTwo = TestScenarioHelper.createScenario();
		scenarioTwo.setScenarioInstance("two");
        scenarioSet.addScenario(scenarioTwo);
        // perform the parsing

        List<Action> actionList = ParseActions.getActionList("/scenarios/spanishsteps/ParseActionTest.sspc", scenarioSet);
        // print the list of actions loaded
		for(Action instant: actionList)
			TextUi.println(instant.toString());
	}

}
