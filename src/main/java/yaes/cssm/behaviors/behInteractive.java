package yaes.cssm.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionType;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.ui.text.TextUi;

public class behInteractive extends AbstractBehavior {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7947660697054349270L;
	/**
	 * If true the selector will ask for a confirmation even if there is a
	 * single choice.
	 */
	private boolean forceInteractionIfNoChoice = false;

	public behInteractive(Scenario scenario, SocialAgent socialAgent,
			String actor, boolean forceInteractionIfNoChoice) {
		super(scenario, socialAgent, actor);
		this.forceInteractionIfNoChoice = forceInteractionIfNoChoice;
	}

	/**
	 * Interactively choose the action
	 */
	@Override
	public Action getAction() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());
		List<String> choices = new ArrayList<>();
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));
		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behInteractive: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}
		// there is only one choice
		String action = null;
		if (choices.size() == 1 && !forceInteractionIfNoChoice) {
			action = choices.get(0);
		} else {
			Collections.sort(choices);
			List<String> menuItems = new ArrayList<>();
			for (String actionx : choices) {
				menuItems.add(actionx
						+ " - "
						+ scenario.getActionTypes().getActionType(actionx)
								.getDescription());
			}
			int actionId = TextUi.menu(menuItems, 0, "Choice: ");
			action = choices.get(actionId);
		}
		ActionType sas = scenario.getActionTypes().getActionType(action);
		List<Double> detailValues = new ArrayList<>();
		for (int i = 0; i != sas.getParameters().size(); i++) {
			double value = TextUi.inputDouble(sas.getParameters().get(i), sas
					.getDefaultValues().get(i));
			detailValues.add(value);
		}
		Action sa = new Action(scenario.getScenarioInstance(), actor, sas,
				detailValues);

		// Save this action in the list of past actions
		scenario.addPerformedAction(action);

		// Print the action taken by actor
		TextUi.println("----Response of " + socialAgent.getName()
				+ " in state " + scenario.getCurrentProgressState());
		TextUi.println("----" + action + " : "
				+ sa.getActionType().getDescription());
		for (int i = 0; i < sa.getParameters().length; i++) {
			TextUi.println(sa.getActionType().getParameters().get(i) + " : "
					+ sa.getParameters()[i]);
		}

		return sa;
	}
}
