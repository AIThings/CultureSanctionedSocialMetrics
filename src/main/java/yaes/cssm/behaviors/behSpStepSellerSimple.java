package yaes.cssm.behaviors;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionType;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.ui.text.TextUi;

/**
 * Simple Behavior class for Seller
 * @author Taranjeet
 *
 */
public class behSpStepSellerSimple extends AbstractBehavior implements
		Constants {

	/**
	 * If true the selector will ask for a confirmation even if there is a
	 * single choice.
	 */
	private boolean runDefaultIfNoChoice = false;
	private double waitTime = 30;
	private int acceptableMinPoliteness = 25;
	private int acceptableMinDignity = 25;

	public behSpStepSellerSimple(Scenario scenario, SocialAgent socialAgent,
			String actor, boolean runDefaultIfNoChoice) {
		super(scenario, socialAgent, actor);
		this.runDefaultIfNoChoice = runDefaultIfNoChoice;
	}

	public behSpStepSellerSimple(Scenario scenario, SocialAgent socialAgent,
			String actor, boolean runDefaultIfNoChoice, int waitTime,
			int minPoliteness, int minDignity) {
		super(scenario, socialAgent, actor);
		this.runDefaultIfNoChoice = runDefaultIfNoChoice;
		this.waitTime = waitTime;
		this.acceptableMinPoliteness = minPoliteness;
		this.acceptableMinDignity = minDignity;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4825215038288774642L;

	@Override
	public Action getAction() {

		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		
		List<String> choices = new ArrayList<String>();
		//Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepSellerSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepSellerSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		}

		// If current progress state S3, then seller can do following
		// alpha-5 = offers gift
		// alpha-6 = forces gift
		// alpha-15 = gives up
		if (scenario.getCurrentProgressState() == "S3") {
			if (scenario.isPastAction("alpha-4")) {
				action = "alpha-5";
			}
			if (scenario.isPastAction("alpha-5")
					&& scenario.isPastAction("alpha-8")) {
				action = "alpha-6";
			}
			if (scenario.isPastAction("alpha-6")
					&& scenario.isPastAction("alpha-10")
					&& (scenario.getActorPlayedBy(actor).getCSSMValue(
							CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
									Actors.SELLER, Actors.SELLER, Actors.SELLER)).getValue() < acceptableMinDignity)) {
				action = "alpha-15";

			}

		}

		// If current progress state is S6, then seller can do following
		// "alpha-12" = accepts return,
		// "alpha-11" = declines return

		if (scenario.getCurrentProgressState() == "S6") {
			SocialAgent agent = scenario.getActorPlayedBy(actor);
			double value = agent.getCSSMValue(CSSM.createCSSM(scenario,
					CULTURE_WESTERN, DIGNITY, Actors.SELLER, Actors.CROWD,
					Actors.SELLER)).getValue();
			if (value < acceptableMinDignity) {
				action = "alpha-12";

			} else {
				action = "alpha-11";
			}
		}

		// If current progress state is S7, then seller can do following
		// "alpha-13"= waits
		// "alpha-14"= requests payment
		// "alpha-16"= concedes gift
		if (scenario.getCurrentProgressState() == "S7") {
			if (scenario.isPastAction("alpha-7")) {
				action = "alpha-13";
			}
			if (scenario.isPastAction("alpha-13")) {
				action = "alpha-14";
			}
			if ((scenario.getActorPlayedBy(actor).getCSSMValue(
					CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
							Actors.SELLER, Actors.CROWD, Actors.SELLER)).getValue() < acceptableMinDignity)
					&& (scenario.getActorPlayedBy(actor).getCSSMValue(
							CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
									Actors.SELLER, Actors.SELLER, Actors.SELLER)).getValue() < acceptableMinDignity)
					&& (scenario.isPastAction("alpha-6"))) {
				action = "alpha-16";
			}
		}

		// If current progress state is S9, then seller can do following
		// alpha-11 = declines return
		// alpha-12 = accepts return
		if (scenario.getCurrentProgressState() == "S9") {
			if (scenario.getActorPlayedBy(actor).getCSSMValue(
					CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
							Actors.SELLER, Actors.CROWD, Actors.SELLER)).getValue() < acceptableMinDignity) {
				action = "alpha-12";
			} else {
				action = "alpha-11";
			}

		}
		if (action == "alpha-13") {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					waitTime);

		} else {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			List<Double> detailValues = new ArrayList<>();
			for (int i = 0; i != sas.getParameters().size(); i++) {
				double value = TextUi.inputDouble(sas.getParameters().get(i),
						sas.getDefaultValues().get(i));
				detailValues.add(value);
			}
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					detailValues);
		}

		// Save this action in the list of past actions
		scenario.addPerformedAction(action);

		//Print the action taken by actor
		TextUi.println("----Response of " + socialAgent.getName()
				+ " in state " + scenario.getCurrentProgressState());
		TextUi.println("----" + action + " : "
				+ sa.getActionType().getDescription());

		return sa;

	}
}
