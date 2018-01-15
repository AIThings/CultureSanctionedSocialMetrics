package yaes.cssm.behaviors;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.ui.text.TextUi;

/**
 * Scenario selector which interactively asks for the agent
 * 
 * @author lboloni
 *
 */
public class sselInteractive implements IScenarioSelector {

	/**
	 *  If true the selector will ask for a confirmation even if there is a single choice.
	 */
	private boolean forceInteractionIfNoChoice = false;

	/**
	 * @param forceInteractionIfNoChoice
	 */
	public sselInteractive(boolean forceInteractionIfNoChoice) {
		super();
		this.forceInteractionIfNoChoice = forceInteractionIfNoChoice;
	}

	@Override
	public Scenario chooseScenario(ScenarioSet scenarioSet,
			ISocialCalculusContext cssmContext) {
		TextUi.println("Select scenario:");
		List<String> scenarioItems = new ArrayList<>();
		for (String scenario : scenarioSet.getScenarios().keySet()) {
			if (scenarioSet.isScenarioFinished(scenarioSet.getScenarios().get(
					scenario))) {
				// nothing here
			} else {
				scenarioItems.add(scenario);
			}
		}
		// no scenario, leave the program here
		if (scenarioItems.size() == 0) {
			TextUi.errorPrint("sselInteractive: no scenario to choose from");
			System.exit(1);
		}
		// there is only one scenario: unless forced, not be interactive
		if (scenarioItems.size() == 1 && !forceInteractionIfNoChoice) {
			String scenarioName = scenarioItems.get(0);
			Scenario scenario = scenarioSet.getScenarios().get(scenarioName);
			TextUi.println("Chosen scenario: " + scenarioName + " (only choice)");
			return scenario;
		}
		int scenarioId = TextUi.menu(scenarioItems, 0, "Choose the scenario: ");
		String scenarioName = scenarioItems.get(scenarioId);
		Scenario scenario = scenarioSet.getScenarios().get(scenarioName);
		return scenario;
	}
	
	
	
}
