package yaes.cssm.cssm;

import java.io.Serializable;

/**
 * The class which accesses the state of the scenario.
 * 
 * This is a view class. In fact, the states are kept inside the agents.
 * 
 * However, the state view allows us to view it through the eye of a scenario.
 * 
 * @author Taranjeet
 * 
 */
public class ScenarioState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 415050929462592729L;
	private Scenario scenario;

	public ScenarioState(Scenario scenario) {
		this.setScenario(scenario);
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}
