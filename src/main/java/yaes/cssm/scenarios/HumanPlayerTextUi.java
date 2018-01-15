package yaes.cssm.scenarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionType;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.ui.text.TextUi;

/**
 * Text based actions for a generic human player stuff
 * 
 * @author Lotzi Boloni, Taranjeet Singh
 * 
 */
public class HumanPlayerTextUi {

	/**
	 * Allows a human user to choose an action
	 */
	public static Action chooseAction(ScenarioSet scenarioSet) {
		//
		//   Choosing the scenario
		//
		TextUi.println("Select scenario:");
		List<String> scenarioItems = new ArrayList<>();
		for (String scenario : scenarioSet.getScenarios().keySet()) {
			if (scenarioSet.isScenarioFinished(scenarioSet.getScenarios().get(
					scenario))) {

			} else {
				scenarioItems.add(scenario);
			}
		}
		int scenarioId = TextUi.menu(scenarioItems, 0, "Choice: ");
		String scenarioName = scenarioItems.get(scenarioId);
		Scenario scenario = scenarioSet.getScenarios().get(scenarioName);
		//
		//   Choosing the actor
		//
		TextUi.println("Current state:" + scenario.getCurrentProgressState());
		Set<String> actors = scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActorsWhoCanAct();
		List<String> actorItems = new ArrayList<>();
		for (String actor : actors) {
			actorItems.add(actor);
		}
		TextUi.println("Select the actor:");
		int actorId = TextUi.menu(actorItems, 0, "Choice: ");
		String actor = actorItems.get(actorId);
		//
		//  Choosing the action 
		//
		TextUi.println("Your are " + actor + " \n choose your next action");
		List<String> choices = new ArrayList<>();
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));
		Collections.sort(choices);
		List<String> menuItems = new ArrayList<>();
		for (String action : choices) {
			menuItems.add(action
					+ " - "
					+ scenario.getActionTypes().getActionType(action)
							.getDescription());
		}
		int actionId = TextUi.menu(menuItems, 0, "Choice: ");
		String action = choices.get(actionId);
		ActionType sas = scenario.getActionTypes().getActionType(action);
		List<Double> detailValues = new ArrayList<>();
		for (int i = 0; i != sas.getParameters().size(); i++) {
			double value = TextUi.inputDouble(sas.getParameters().get(i), sas
					.getDefaultValues().get(i));
			detailValues.add(value);
		}

		Action sa = new Action(scenarioName, actor, sas, detailValues);
		return sa;
	}

}
