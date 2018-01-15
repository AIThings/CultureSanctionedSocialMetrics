package cssm.actions;

import org.junit.Test;

import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;

/**
 * ActionCssmImpacts class test
 * 
 * @author Taranjeet
 * 
 */
public class testActionCssmImpacts implements Constants {
	
	@Test
	public void test() {
		Scenario scenario = TestScenarioHelper.createScenario();

		

		// CSSM cssmTime = new CSSM(CULTURE_WESTERN, TIME, agentJill.getName(),
		// agentJill.getName(), agentJill.getName(), 0, 40, 0);
		CSSM cssmTime = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		//
		
	}

}
