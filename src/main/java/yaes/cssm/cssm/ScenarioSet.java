package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yaes.ui.format.Formatter;
import yaes.ui.text.TextUi;

/**
 * Represents a social situation where multiple scenarios happen at the same
 * time.
 * 
 * By convention, this situation terminates when all the scenarios are
 * terminated.
 * 
 * @author Taranjeet
 * 
 */
public class ScenarioSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6600498886437877575L;
	private Map<String, Scenario> scenarios = new HashMap<>();

	private List<Scenario> finishedScenarios = new ArrayList<>();

	/**
	 * Adds a new scenario to the scenario set
	 * 
	 * @param scenario
	 */
	public void addScenario(Scenario scenario) {
		scenarios.put(scenario.getScenarioInstance(), scenario);
	}

	/**
	 * remove scenario from finish scenario in order to rerun same scenario
	 */
	public void removeScenario(Scenario scenario) {
		finishedScenarios.remove(scenario);
	}

	/**
	 * Finds a scenario in the set
	 */
	public Scenario findScenario(String instanceName) {
		return scenarios.get(instanceName);
	}

	/**
	 * Is scenario finished
	 */
	public boolean isScenarioFinished(Scenario scenario) {
		if (this.finishedScenarios.contains(scenario)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return scenario Set
	 */
	public Map<String, Scenario> getScenarios() {
		return scenarios;
	}

	/**
	 * Performs a concrete action in one scenario
	 * 
	 * If the specific scenario returns true, it means it is terminated.
	 * 
	 * If all of them are terminated, the whole set is terminated
	 * 
	 * @param instanceName
	 * @param actionType
	 * @param actorName
	 * @param parameters
	 * @return
	 */
	public boolean performConcreteAction(String instanceName,
			String actionType, String actorName, double... parameters) {
		Scenario scenario = findScenario(instanceName);
		if (finishedScenarios.contains(scenario)) {
			TextUi.errorPrint("Trying to perform an action on finished scenario "
					+ instanceName);
			System.exit(1);
		}
		boolean retval = scenario.performProgress(actionType, actorName,
				parameters);
		if (retval) {
			finishedScenarios.add(scenario);
		}
		// if all the scenarios are finished, the scenario set is finished
		if (finishedScenarios.size() == scenarios.keySet().size()) {
			return true;
		}
		return false;
	}

	public String toStringDetailed(int maxDetail) {

		Formatter fmt = new Formatter();

		for (Scenario scenario : finishedScenarios) {
			fmt.add(scenario.toStringDetailed(maxDetail));
		}

		return fmt.toString();
	}

}
