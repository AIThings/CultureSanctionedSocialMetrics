package yaes.cssm.scenarios.giveWayScenario;

import java.io.File;

import yaes.cssm.actions.ParseActions;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.world.physical.PhysicalWorld;
import yaes.world.physical.map.SimpleField;

/**
 * Context Class For initializing scenario context
 * @author Taranjeet
 *
 */
public class GiveWayContext extends SocialCalculusContext{

	private static final long serialVersionUID = -1683388436968542300L;

	
	
	@Override
	public void initialize(SimulationInput sip, SimulationOutput sop) {
		// TODO Auto-generated method stub
		super.initialize(sip, sop);
		String directoryName = sip.getParameterString(DIR_HISTORY);
		dirCheckpoints = new File(directoryName);
		scenarioSet = new ScenarioSet();
		initScenarioSet();
		if (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
			preplannedActions = ParseActions.getActionList(
					sip.getParameterString(ACTION_FILE_READ), scenarioSet);
		}
		
	}



	/**
	 * Initialize the scenario set
	 */
	@Override
	protected void initScenarioSet() {
		
		SocialAgent agentAmerican = new SocialAgent("Mike");
		agents.put(agentAmerican.getName(), agentAmerican);
		
		SocialAgent agentIndian = new SocialAgent("Radha");
		agents.put(agentIndian.getName(), agentIndian);
		
		
		SocialAgent agentCrowd = new SocialAgent("CrowdOfOnlookers");
		agents.put(agentCrowd.getName(), agentCrowd);
		
		//Create a new scenario, 
		//Assign the Agents to Actors 
		//Initialize the scenario
		//Add scenario to ScenarioSet
		
		Scenario scenario = GiveWayHelper.initScenario("one", agentAmerican, agentIndian, agentCrowd);
		scenarioSet.addScenario(scenario);
		
		
	}

}
