package cssm.actions;


import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.spanishsteps.DefineScenario;


/**
 * Helper functions for creating scenarios on top of which the tests can then
 * be created
 * 
 * @author Lotzi
 *
 */
public class TestScenarioHelper {

	/**
	 * Create a sample spanish steps scenario
	 * 
	 * @return
	 */
	public static Scenario createScenario() {		
		SocialAgent agentHassan = new SocialAgent("Hassan");
		SocialAgent agentJill = new SocialAgent("Jill");
		SocialAgent agentCrowd = new SocialAgent("CrowdOfOnlookers");
		SocialAgent agentAli = new SocialAgent("Ali");
		Scenario scenario = DefineScenario.initializeScenario("default",
				agentHassan, agentJill, agentAli, agentCrowd);
		return scenario;
	}

	
}
