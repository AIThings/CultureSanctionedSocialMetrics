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
 * Simple behavior class for Client
 * 
 * @author Taranjeet
 * 
 */
public class behSpStepClientSimple extends AbstractBehavior implements
		Constants {

	private static final long serialVersionUID = 7455240158176504114L;

	// Return the only action available
	private boolean runDefaultIfNoChoice = false;

	/*
	 * when the choice is the parameters of trying to give back: start with the
	 * start(loudness / offensiveness), and then at each new try, escalate by
	 * multiplying with the factors.
	 */

	private double escalationLoudness;
	private double escalationOffensiveness;
	private double startLoudness;
	private double startOffensiveness;

	/*
	 * when the choice is between paying up or not: based on acceptable minimum
	 * politeness or dignity
	 */
	private int acceptableMinPoliteness;
	private int acceptableMinDignity;
	private ClientType clientType;

	public behSpStepClientSimple(Scenario scenario, SocialAgent socialAgent,
			String actor, boolean runDefaultIfNoChoice,
			double escalationLoudness, double escalationOffensiveness,
			double startLoudness, double startOffensiveness,
			int acceptableMinPoliteness, int acceptableMinDignity) {
		super(scenario, socialAgent, actor);
		this.runDefaultIfNoChoice = runDefaultIfNoChoice;
		this.escalationLoudness = escalationLoudness;
		this.escalationOffensiveness = escalationOffensiveness;
		this.startLoudness = startLoudness;
		this.startOffensiveness = startOffensiveness;
		this.acceptableMinDignity = acceptableMinDignity;
		this.acceptableMinPoliteness = acceptableMinPoliteness;

	}

	public behSpStepClientSimple(Scenario scenario, SocialAgent socialAgent,
			String actor, ClientType clientType) {
		super(scenario, socialAgent, actor);
		this.runDefaultIfNoChoice = true;

		switch (clientType) {
		case EASY:
			this.clientType = ClientType.EASY;
			this.escalationLoudness = 0.1;
			this.escalationOffensiveness = 0.1;
			this.startLoudness = 0.1;
			this.startOffensiveness = 0.1;
			this.acceptableMinDignity = 20;
			this.acceptableMinPoliteness = 20;
			break;

		case ARROGANT:
			this.clientType = ClientType.ARROGANT;
			this.escalationLoudness = 0.3;
			this.escalationOffensiveness = 0.3;
			this.startLoudness = 0.1;
			this.startOffensiveness = 0.1;
			this.acceptableMinDignity = 10;
			this.acceptableMinPoliteness = 10;
			break;

		case BUSY:
			this.clientType = ClientType.BUSY;
			this.escalationLoudness = 0.2;
			this.escalationOffensiveness = 0.05;
			this.startLoudness = 0.1;
			this.startOffensiveness = 0.1;
			this.acceptableMinDignity = 35;
			this.acceptableMinPoliteness = 35;
			break;

		case SMART:
			this.clientType = ClientType.SMART;
			this.escalationLoudness = 0.5;
			this.escalationOffensiveness = 0.5;
			this.startLoudness = 0.1;
			this.startOffensiveness = 0.1;
			this.acceptableMinDignity = 40;
			this.acceptableMinPoliteness = 40;
			break;

		case WEALTHY:
			this.clientType = ClientType.WEALTHY;
			this.escalationLoudness = 0;
			this.escalationOffensiveness = 0;
			this.startLoudness = 0;
			this.startOffensiveness = 0;
			this.acceptableMinDignity = 50;
			this.acceptableMinPoliteness = 50;
			break;

		default:
			this.escalationLoudness = 0;
			this.escalationOffensiveness = 0;
			this.startLoudness = 0;
			this.startOffensiveness = 0;
			this.acceptableMinDignity = 0;
			this.acceptableMinPoliteness = 0;
			break;
		}
	}

	@Override
	public Action getAction() {
		switch (this.clientType) {
		case EASY:
			return getEasy();

		case ARROGANT:
			return getArrogant();

		case BUSY:
			return getBusy();

		case SMART:
			return getSmart();

		case WEALTHY:
			return getWealthy();

		default:
			return null;
		}

	}

	/**
	 * Wealthy client who love buying flowers
	 * 
	 * @return Action
	 */
	private Action getWealthy() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		List<String> choices = new ArrayList<>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepClientSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepClientSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		} else {

			// If current progress state S1, then client can do following
			// "alpha-2" => "S2" (accepts buying)
			// "alpha-4" => "S3" (declines buying)

			if (scenario.getCurrentProgressState() == "S1") {
				if (Math.random() > 0.5) {
					action = "alpha-2";
				} else
					action = "alpha-4";

			}
			if (scenario.getCurrentProgressState() == "S2") {
				action = "alpha-3";
			}

			if (scenario.getCurrentProgressState() == "S4") {
				if (Math.random() > 0.5) {
					action = "alpha-7";
				} else
					action = "alpha-8";
			}

			if (scenario.getCurrentProgressState() == "S5") {
				if (Math.random() > 0.5) {
					action = "alpha-7";
				} else
					action = "alpha-10";
			}

			if (scenario.getCurrentProgressState() == "S8") {
				if (Math.random() > 0.5) {
					action = "alpha-2";
				} else
					action = "alpha-10";
			}

		}

		if ((action == "alpha-8") || (action == "alpha-10")) {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					startLoudness, startOffensiveness);

			// Increase the loudness and offensiveness by escalation value
			startLoudness = startLoudness + escalationLoudness;
			if (startLoudness >= 1) {
				startLoudness = 1;
			}

			startOffensiveness = startOffensiveness + escalationOffensiveness;
			if (startOffensiveness >= 1) {
				startOffensiveness = 1;
			}

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

	/**
	 * Smart client who figure out that seller is a crook. He will not accept
	 * flower and try returning to seller.
	 * 
	 * @return
	 */
	private Action getSmart() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		List<String> choices = new ArrayList<>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepClientSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepClientSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		} else {

			// If current progress state S1, then client can do following
			// "alpha-2" => "S2" (accepts buying)
			// "alpha-4" => "S3" (declines buying)

			if (scenario.getCurrentProgressState() == "S1") {
				action = "alpha-4";
			}

			// If current progress state S4, then client can do following
			// "alpha-8" => "S3" (declines gift)
			// "alpha-7" => "S7" (accepts gift)
			if (scenario.getCurrentProgressState() == "S4") {

				action = "alpha-7";

			}

			if (scenario.getCurrentProgressState() == "S5") {
				action = "alpha-7";
			}

			// if current progress state S8, then client can do following
			// "alpha-10" => "S9" (attempts return)
			// "alpha-2" => "S10" ( accepts buying)
			if (scenario.getCurrentProgressState() == "S8") {
				action = "alpha-10";

			}

		}

		if ((action == "alpha-8") || (action == "alpha-10")) {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					startLoudness, startOffensiveness);

			// Increase the loudness and offensiveness by escalation value
			startLoudness = startLoudness + escalationLoudness;
			if (startLoudness >= 1) {
				startLoudness = 1;
			}

			startOffensiveness = startOffensiveness + escalationOffensiveness;
			if (startOffensiveness >= 1) {
				startOffensiveness = 1;
			}

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

	/**
	 * Busy don't want to buy, But if he is getting late, he will buy.
	 * 
	 * @return
	 */
	private Action getBusy() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		List<String> choices = new ArrayList<>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepClientSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepClientSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		} else {

			// If current progress state S1, then client can do following
			// "alpha-2" => "S2" (accepts buying)
			// "alpha-4" => "S3" (declines buying)

			if (scenario.getCurrentProgressState() == "S1") {
				action = "alpha-4";
			}

			// If current progress state S4, then client can do following
			// "alpha-8" => "S3" (declines gift)
			// "alpha-7" => "S7" (accepts gift)
			if (scenario.getCurrentProgressState() == "S4") {
				SocialAgent agent = scenario.getActorPlayedBy(actor);
				double value = agent.getCSSMValue(
						CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
								Actors.CLIENT, Actors.CROWD, Actors.CLIENT))
						.getValue();
				if (value < this.acceptableMinDignity) {
					action = "alpha-7";

				} else {
					action = "alpha-8";
				}

				value = agent.getCSSMValue(
						CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
								Actors.CLIENT, Actors.CLIENT, Actors.CLIENT))
						.getValue();
				if (value > 40) {
					action = "alpha-7";

				}
			}

			// If current progress state S5, then client can do following
			// "alpha-10" => "S6" (attempts return)
			// "alpha-7" => "S7" (accepts gift)
			// "alpha-9" => "TN1" (throws flower)
			if (scenario.getCurrentProgressState() == "S5") {
				SocialAgent agent = scenario.getActorPlayedBy(actor);
				double value = agent.getCSSMValue(
						CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
								Actors.CLIENT, Actors.CROWD, Actors.CLIENT))
						.getValue();
				if (value < this.acceptableMinDignity) {
					action = "alpha-7";

				} else {
					action = "alpha-10";
				}

				value = agent.getCSSMValue(
						CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
								Actors.CLIENT, Actors.CLIENT, Actors.CLIENT))
						.getValue();
				if (value > 60) {
					action = "alpha-7";

				}

			}

			// if current progress state S8, then client can do following
			// "alpha-10" => "S9" (attempts return)
			// "alpha-2" => "S10" ( accepts buying)
			if (scenario.getCurrentProgressState() == "S8") {

				// under the pressure of loosing dignity in public
				if (scenario.isPastAction("alpha-11")) {

					if ((scenario
							.getActorPlayedBy(actor)
							.getCSSMValue(
									CSSM.createCSSM(scenario, CULTURE_WESTERN,
											DIGNITY, Actors.CLIENT, Actors.CROWD,
											Actors.CLIENT)).getValue() < this.acceptableMinDignity)
							&& (scenario
									.getActorPlayedBy(actor)
									.getCSSMValue(
											CSSM.createCSSM(scenario,
													CULTURE_WESTERN,
													POLITENESS, Actors.CLIENT,
													Actors.CROWD, Actors.CLIENT))
									.getValue() < this.acceptableMinPoliteness)) {
						action = "alpha-2";

					} else {
						action = "alpha-10";
					}

					if (scenario
							.getActorPlayedBy(actor)
							.getCSSMValue(
									CSSM.createCSSM(scenario, CULTURE_WESTERN,
											TIME, Actors.CLIENT, Actors.CLIENT,
											Actors.CLIENT)).getValue() > 70) {
						action = "alpha-2";

					}
				}

			}

		}

		if ((action == "alpha-8") || (action == "alpha-10")) {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					startLoudness, startOffensiveness);

			// Increase the loudness and offensiveness by escalation value
			startLoudness = startLoudness + escalationLoudness;
			if (startLoudness >= 1) {
				startLoudness = 1;
			}

			startOffensiveness = startOffensiveness + escalationOffensiveness;
			if (startOffensiveness >= 1) {
				startOffensiveness = 1;
			}

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

	/**
	 * Arrogant customer will not buy flower. Refuse with loudness and highly
	 * offensive.
	 * 
	 * @return
	 */
	private Action getArrogant() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		List<String> choices = new ArrayList<>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepClientSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepClientSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		} else {

			// If current progress state S1, then client can do following
			// "alpha-2" => "S2" (accepts buying)
			// "alpha-4" => "S3" (declines buying)

			if (scenario.getCurrentProgressState() == "S1") {
				action = "alpha-4";
			}

			// If current progress state S4, then client can do following
			// "alpha-8" => "S3" (declines gift)
			// "alpha-7" => "S7" (accepts gift)
			if (scenario.getCurrentProgressState() == "S4") {

				action = "alpha-8";

			}

			// If current progress state S5, then client can do following
			// "alpha-10" => "S6" (attempts return)
			// "alpha-7" => "S7" (accepts gift)
			// "alpha-9" => "TN1" (throws flower)
			if (scenario.getCurrentProgressState() == "S5") {

				if (scenario
						.getActorPlayedBy(actor)
						.getCSSMValue(
								CSSM.createCSSM(scenario, CULTURE_WESTERN,
										DIGNITY, Actors.CLIENT, Actors.CLIENT,
										Actors.CLIENT)).getValue() < this.acceptableMinDignity) {
					action = "alpha-9";
				} else {
					action = "alpha-10";
				}

				if (scenario
						.getActorPlayedBy(actor)
						.getCSSMValue(
								CSSM.createCSSM(scenario, CULTURE_WESTERN,
										TIME, Actors.CLIENT, Actors.CLIENT,
										Actors.CLIENT)).getValue() > 90) {
					action = "alpha-9";
				}
			}

			// if current progress state S8, then client can do following
			// "alpha-10" => "S9" (attempts return)
			// "alpha-2" => "S10" ( accepts buying)
			if (scenario.getCurrentProgressState() == "S8") {

				action = "alpha-10";

			}

		}

		if ((action == "alpha-8") || (action == "alpha-10")) {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					startLoudness, startOffensiveness);

			// Increase the loudness and offensiveness by escalation value
			startLoudness = startLoudness + escalationLoudness;
			if (startLoudness >= 1) {
				startLoudness = 1;
			}

			startOffensiveness = startOffensiveness + escalationOffensiveness;
			if (startOffensiveness >= 1) {
				startOffensiveness = 1;
			}

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

	/**
	 * Easy customer avoid increasing voice and will be polite most of time.
	 * 
	 * @return
	 */
	private Action getEasy() {
		TextUi.println("Your are " + actor + " played by "
				+ socialAgent.getName() + " in state "
				+ scenario.getCurrentProgressState() + " in scenario "
				+ scenario.getScenarioInstance() + "/"
				+ scenario.getScenarioType());

		List<String> choices = new ArrayList<>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepClientSimple: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			TextUi.println("behSpStepClientSimple: actor " + actor
					+ " can take only " + action + " action in this state: "
					+ scenario.getCurrentProgressState());

		} else {

			// If current progress state S1, then client can do following
			// "alpha-2" => "S2" (accepts buying)
			// "alpha-4" => "S3" (declines buying)

			if (scenario.getCurrentProgressState() == "S1") {
				action = "alpha-4";
			}

			// If current progress state S4, then client can do following
			// "alpha-8" => "S3" (declines gift)
			// "alpha-7" => "S7" (accepts gift)
			if (scenario.getCurrentProgressState() == "S4") {

				action = "alpha-7";

			}

			// If current progress state S5, then client can do following
			// "alpha-10" => "S6" (attempts return)
			// "alpha-7" => "S7" (accepts gift)
			// "alpha-9" => "TN1" (throws flower)
			if (scenario.getCurrentProgressState() == "S5") {
				SocialAgent agent = scenario.getActorPlayedBy(actor);
				double value = agent.getCSSMValue(
						CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
								Actors.CLIENT, Actors.CROWD, Actors.CLIENT))
						.getValue();
				if (value < this.acceptableMinPoliteness) {
					action = "alpha-7";

				} else {
					action = "alpha-10";
				}

				if (scenario
						.getActorPlayedBy(actor)
						.getCSSMValue(
								CSSM.createCSSM(scenario, CULTURE_WESTERN,
										TIME, Actors.CLIENT, Actors.CLIENT,
										Actors.CLIENT)).getValue() > 70) {
					action = "alpha-7";
				}

			}

			// if current progress state S8, then client can do following
			// "alpha-10" => "S9" (attempts return)
			// "alpha-2" => "S10" ( accepts buying)
			if (scenario.getCurrentProgressState() == "S8") {

				// under the pressure of loosing dignity in public
				if (scenario.isPastAction("alpha-11")) {

					if ((scenario
							.getActorPlayedBy(actor)
							.getCSSMValue(
									CSSM.createCSSM(scenario, CULTURE_WESTERN,
											POLITENESS, Actors.CLIENT,
											Actors.CROWD, Actors.CLIENT))
							.getValue() < this.acceptableMinPoliteness)) {
						action = "alpha-2";

					} else {
						action = "alpha-10";
					}

				}

				// Under the pressure of loosing own dignity
				if (scenario.isPastAction("alpha-14")) {
					SocialAgent agent = scenario.getActorPlayedBy(actor);
					double value = agent.getCSSMValue(
							CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
									Actors.CLIENT, Actors.CLIENT, Actors.CLIENT))
							.getValue();
					if (value < this.acceptableMinDignity) {
						action = "alpha-2";

					} else {
						action = "alpha-10";
					}
				}

				if (scenario
						.getActorPlayedBy(actor)
						.getCSSMValue(
								CSSM.createCSSM(scenario, CULTURE_WESTERN,
										TIME, Actors.CLIENT, Actors.CLIENT,
										Actors.CLIENT)).getValue() > 70) {
					action = "alpha-2";
				}
			}

		}

		if ((action == "alpha-8") || (action == "alpha-10")) {
			ActionType sas = scenario.getActionTypes().getActionType(action);
			sa = new Action(scenario.getScenarioInstance(), actor, sas,
					startLoudness, startOffensiveness);

			// Increase the loudness and offensiveness by escalation value
			startLoudness = startLoudness + escalationLoudness;
			if (startLoudness >= 1) {
				startLoudness = 1;
			}

			startOffensiveness = startOffensiveness + escalationOffensiveness;
			if (startOffensiveness >= 1) {
				startOffensiveness = 1;
			}

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
