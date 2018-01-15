package yaes.cssm.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;

/**
 * Default scenario selector. It has a memory and it gives them the right to perform one by one.
 * @author lboloni
 *
 */
public class sselDefault implements IScenarioSelector {

	private static final long serialVersionUID = 7193123972097303850L;
	/**
	 * The scenario which had been selected last time
	 */
	private int lastScenarioIndex = -1;
	
	/**
	 * Returns in a circular order the next scenario which had not been finished.
	 * 
	 * It does not handle well situations where scenarios are added directly 
	 */
	@Override
	public Scenario chooseScenario(ScenarioSet scenarioSet,
			ISocialCalculusContext cssmContext) {
		List<String> scenarioNames = new ArrayList<>();
		scenarioNames.addAll(scenarioSet.getScenarios().keySet());
		Collections.sort(scenarioNames);
		int index;
		if (lastScenarioIndex == -1) {
			index = 0;
		} else {
			index = lastScenarioIndex;
		}
		while(true) {
			index = (index + 1) % scenarioNames.size();
			String name = scenarioNames.get(index);
			Scenario scenario = scenarioSet.findScenario(name);
			if (!scenarioSet.isScenarioFinished(scenario)) {
				lastScenarioIndex = index;
				return scenario;
			}
			// it was finished
			if (index == lastScenarioIndex) {
				// no more unfinished scenarios available
				return null;
			}
		}
	}

}
