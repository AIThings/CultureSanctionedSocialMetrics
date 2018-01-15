package yaes.cssm.behaviors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionType;
import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.FeatureList;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.cssm.scenarios.spanishsteps.DefineAif;
import yaes.cssm.scenarios.spanishsteps.SpanishStepsContext;
import yaes.framework.simulation.SimulationInput;
import yaes.ui.text.TextUi;

/**
 * Simple Behavior class for Seller
 * 
 * @author Taranjeet
 * 
 */
public class behSpStepSellerReinforce extends AbstractBehavior implements
		Constants {

	/**
	 * If true the selector will ask for a confirmation even if there is a
	 * single choice.
	 */
	private boolean runDefaultIfNoChoice = false;
	private double waitTime = 10;

	SimulationInput sip;

	// alpha is learning rate
	private double alpha;

	// gamma is discount factor
	private double gamma = 0.8;

	public behSpStepSellerReinforce(SimulationInput sip, Scenario scenario,
			SocialAgent socialAgent, String actor,
			boolean runDefaultIfNoChoice, File dirWeights) {
		super(scenario, socialAgent, actor);
		this.runDefaultIfNoChoice = runDefaultIfNoChoice;
		this.sip = sip;
		initializeWeights(socialAgent, dirWeights);
	}

	/**
	 * TODO: Taran: this must have the current iteration, and read in the
	 * weights from iteration-1 or initialize them to the initial value
	 * 
	 * @param socialAgent
	 * @param dirWeights
	 */
	public void initializeWeights(SocialAgent socialAgent, File dirWeights) {
		File file = new File(dirWeights, socialAgent.getName());
		readWeights(file.toString());

	}

	/**
	 * Reads the weights from a file. If the file is not found, the weights are initialized
	 * to random values. 
	 * 
	 * @param filePath
	 */
	public void readWeights(String filePath) {

		String currentLine = "";
		Map<String, Double> weights = new HashMap<String, Double>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));

			try {
				// TextUi.println("~~~~~~~~~~~~~~~~~Weights are reading from file~~~~~~~~~~~~~~~~~");
				while ((currentLine = br.readLine()) != null) {
					String st = currentLine.split(":")[0];
					double val = Double.parseDouble(currentLine.split(":")[1]);
					weights.put(st, val);

				}
				for (CSSM cssm : socialAgent.getCSSMs()) {
					socialAgent.setWeights(cssm,
							weights.get(cssm.getPseudoName()));
					socialAgent.setFeaturesOld(cssm,
							socialAgent.getCSSMDefaultValue(cssm));
				}
				for (ConcreteBelief cb : socialAgent.getCBs()) {
					socialAgent.setWeights(cb, weights.get(cb.getPseudoName()));
					socialAgent.setFeaturesOld(cb, socialAgent.getCBValue(cb)
							.getValue());
				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TextUi.println("&&&&&&&&&&&&  Weights are set Randomly &&&&&&&&&&&&&&&&&&&");
			for (CSSM cssm : socialAgent.getCSSMs()) {
				socialAgent.setWeights(cssm, Math.random() * 100);
				socialAgent.setFeaturesOld(cssm, socialAgent.getCSSMValue(cssm)
						.getDefaultValue());
			}
			for (ConcreteBelief cb : socialAgent.getCBs()) {
				socialAgent.setWeights(cb, Math.random() * 100);
				socialAgent.setFeaturesOld(cb, socialAgent.getCBValue(cb)
						.getValue());
			}
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4825215038288774642L;

	@Override
	public Action getAction() {
		//
		// TextUi.println("Your are " + actor + " played by "
		// + socialAgent.getName() + " in state "
		// + scenario.getCurrentProgressState() + " in scenario "
		// + scenario.getScenarioInstance() + "/"
		// + scenario.getScenarioType());

		int iteration = sip
				.getParameterInt(SpanishStepsContext.SPANISH_STEPS_LEARNING_ITERATION);

		this.alpha = (1.0 / (10.0 + iteration));

		List<String> choices = new ArrayList<String>();
		// Identify all possible actions for agent in this state
		choices.addAll(scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActionTypesForActor(actor));

		// there is no choice
		if (choices.size() == 0) {
			TextUi.errorPrint("behSpStepSellerReinforce: actor " + actor
					+ " can take no action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}

		String action = null;
		Action sa = null;

		if (scenario.getCurrentProgressState() != "S0") {
			updateWeights();

		}
		// there is only one choice
		if (choices.size() == 1 && this.runDefaultIfNoChoice) {
			action = choices.get(0);
			// TextUi.println("behSpStepSellerReinforce: actor " + actor
			// + " can take only " + action + " action in this state: "
			// + scenario.getCurrentProgressState());

		} else {
			double qnew_max = 0 ;
			action = choices.get(0);
			boolean qmaxEmpty = true;
			
			
			for (String act : choices) {

				if (act == "alpha-6") {
					if (!scenario.isPastAction("alpha-5"))
						continue;
				}
				if (scenario.isPastAction("alpha-5")) {
					continue;
				}

				double qnew = 0;
				ActionType sas = scenario.getActionTypes().getActionType(act);
				Action sa_temp = new Action(scenario.getScenarioInstance(),
						actor, sas);
				if (act == "alpha-13") {
					sa_temp = new Action(scenario.getScenarioInstance(), actor,
							sas, waitTime);
				}
				qnew = calculateQValue(sa_temp);

				if(qmaxEmpty){
					qnew_max = qnew;
					action = act;
					qmaxEmpty = false;
				}
				if (qnew > qnew_max) {
					qnew_max = qnew;
					action = act;
				}

			}

			double timeSpent = socialAgent.getCSSMValue(
					CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
							Actors.SELLER, Actors.SELLER, Actors.SELLER))
					.getValue();
			
			if (scenario.getCurrentProgressState() == "S3") {
				if (timeSpent >= 50) {
					action = "alpha-15";
				} else {
					action = "alpha-5";
				}
			}

			if (scenario.getCurrentProgressState() == "S9") {
				if (timeSpent >= 100) {
					action = "alpha-12";
				} else {
					action = "alpha-11";
				}
			}

			Random rand = new Random();
			int random = rand.nextInt(choices.size());
			if (Math.random() < 0.3) {
				action = choices.get(random);
			}

			// action = getActionForStatePolicy(scenario);
			// TextUi.println("behSpStepSellerReinforce: actor " + actor
			// + " Choose to take action " + action + " in this state: "
			// + scenario.getCurrentProgressState());

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

		// Print the action taken by actor
		TextUi.println("----Response of " + socialAgent.getName()
				+ " in state " + scenario.getCurrentProgressState());
		TextUi.println("----" + action + " : "
				+ sa.getActionType().getDescription());

		// Saving CSSMs and CBs feature values because after leaving this
		// function,
		// these CSSMs will be changed
		for (CSSM cssm : socialAgent.getCSSMs()) {
			socialAgent.setFeaturesOld(cssm, socialAgent.getCSSMValue(cssm)
					.getValue());
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			socialAgent.setFeaturesOld(cb, socialAgent.getCBValue(cb)
					.getValue());
		}

		return sa;

	}

	/**
	 * This function calculate new Qvalue for every action provide. This is
	 * predicted Q-value by agent using his imagined actionImpact.
	 * 
	 * @param scenario
	 * @param socialAgent
	 * @param actor
	 * @param action
	 * @return
	 */
	public double calculateQValue(Action action) {
		List<ActionImpact> actionImpacts = scenario.evaluateAction(action,
				new DefineAif());

		double Qnew = 0;

		FeatureList featuresList = new FeatureList();
		for (CSSM cssm : socialAgent.getCSSMs()) {
			featuresList.setCSSMValue(cssm, socialAgent.getCSSMValue(cssm)
					.getValue());
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			featuresList.setCBValue(cb, socialAgent.getCBValue(cb).getValue());
		}

		for (ActionImpact ai : actionImpacts) {
			if (ai.isCSSM()) {
				featuresList.setCSSMValue(scenario, ai.getCultureName(),
						ai.getMetricName(), ai.getSubActor(),
						ai.getPersActor(), ai.getEstActor(), ai.getNewValue());

			} else {

				featuresList.setCBValue(scenario, scenario.getScenarioType(),
						ai.getMetricName(), ai.getPersActor(),
						ai.getEstActor(), ai.getBeliefValue().getValue());
			}
		}

		for (CSSM cssm : socialAgent.getCSSMs()) {
			Qnew = Qnew
					+ socialAgent.getWeight(cssm)
					* socialAgent.getCSSMValue(cssm).normlize(
							featuresList.getCSSMValue(cssm));
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			Qnew = Qnew + socialAgent.getWeight(cb)
					* featuresList.getCBValue(cb);
		}

		return Qnew;
	}

	/**
	 * This function update weights for approximate reinforcement learning. The
	 * weights will be updated before choosing the next action. This update
	 * contains Qnew based on present feature weights and Qold based on previous
	 * feature weights. Both of which are true feature weights.
	 * 
	 * @param scenario
	 * @param socialAgent
	 * @param actor
	 * @param action
	 * @param rewards
	 */
	public void updateWeights() {

		double Qnew = 0;
		double Qold = 0;
		double reward = 0;
		for (CSSM cssm : socialAgent.getCSSMs()) {
			Qnew = Qnew
					+ socialAgent.getWeight(cssm)
					* socialAgent.getCSSMValue(cssm).normlize(
							socialAgent.getCSSMValue(cssm).getValue());
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			Qnew = Qnew + socialAgent.getWeight(cb)
					* socialAgent.getCBValue(cb).getValue();
		}
		for (CSSM cssm : socialAgent.getCSSMs()) {
			Qold = Qold
					+ socialAgent.getWeight(cssm)
					* socialAgent.getCSSMValue(cssm).normlize(
							socialAgent.getFeatureOld(cssm));
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			Qold = Qold + socialAgent.getWeight(cb)
					* socialAgent.getFeatureOld(cb);
		}

		// if (isReward) {
		reward = getReward();
		TextUi.println(this.alpha);
		// }
		for (CSSM cssm : socialAgent.getCSSMs()) {
			double oldweight = socialAgent.getWeight(cssm);
			double weight = socialAgent.getWeight(cssm)
					+ this.alpha
					* (reward + this.gamma * (Qnew) - Qold)
					* socialAgent.getCSSMValue(cssm).normlize(
							socialAgent.getFeatureOld(cssm));

			socialAgent.setWeights(cssm, weight);
		}
		for (ConcreteBelief cb : socialAgent.getCBs()) {

			double weight = 0;
			double oldweight = socialAgent.getWeight(cb);
			weight = socialAgent.getWeight(cb) + this.alpha
					* (reward + this.gamma * (Qnew) - Qold)
					* (socialAgent.getFeatureOld(cb));

			socialAgent.setWeights(cb, weight);
		}

	}

	/**
	 * Hand picked reward values.
	 * 
	 * @return
	 */
	private double getReward() {
		double reward = 0;
		double timeSpent = socialAgent.getCSSMValue(
				CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME, Actors.SELLER,
						Actors.SELLER, Actors.SELLER)).getValue();
		if (scenario.getCurrentProgressState() == "S12") {
			if (scenario.isPastAction("alpha-9")) {
				return -100;
			}
			if (scenario.isPastAction("alpha-15")) {
				return -100;
			}
			if (scenario.isPastAction("alpha-12")) {
				if (timeSpent >= 50) {
					return -100;
				} else {
					return 100;
				}
			}

		} else if (scenario.getCurrentProgressState() == "S11") {
			if (scenario.isPastAction("alpha-3")) {
				return 0;
			}

		} else if (scenario.getCurrentProgressState() == "S13") {
			if (scenario.isPastAction("alpha-16")) {

				return -100;

			}

		} else if (scenario.getCurrentProgressState() == "S14") {
			if (scenario.isPastAction("alpha-12")) {
				if (timeSpent >= 100) {
					return -100;
				} else {
					return 100;
				}
			}

		} else if (scenario.getCurrentProgressState() == "S15") {
			if (scenario.isPastAction("alpha-3")) {
				if (timeSpent >= 100) {
					return -100;
				} else {
					return 100;
				}
			}

		}

		return reward;

		// double reward = 0;
		// Map<CSSM, Double> rewardDelta = socialAgent.getDeltaFeatures();
		//
		// CSSM dignity_Self = CSSM.createCSSM(scenario, CULTURE_WESTERN,
		// DIGNITY,
		// ACTOR_SELLER, ACTOR_SELLER, ACTOR_SELLER);
		// CSSM dignity_Public = CSSM.createCSSM(scenario, CULTURE_WESTERN,
		// DIGNITY, ACTOR_SELLER, ACTOR_CROWD, ACTOR_SELLER);
		// CSSM politeness_Self = CSSM.createCSSM(scenario, CULTURE_WESTERN,
		// POLITENESS, ACTOR_SELLER, ACTOR_SELLER, ACTOR_SELLER);
		// CSSM politeness_Public = CSSM.createCSSM(scenario, CULTURE_WESTERN,
		// POLITENESS, ACTOR_SELLER, ACTOR_CROWD, ACTOR_SELLER);
		// CSSM time = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
		// ACTOR_SELLER, ACTOR_SELLER, ACTOR_SELLER);
		// CSSM worth = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH,
		// ACTOR_SELLER, ACTOR_SELLER, ACTOR_SELLER);
		//
		// if ((rewardDelta.get(dignity_Self) > -1)
		// && (rewardDelta.get(dignity_Public) > -1)
		// && (rewardDelta.get(time) < 30) && (rewardDelta.get(worth) > 0)) {
		// return 100;
		//
		// } else if ((rewardDelta.get(dignity_Self) > -10)
		// && (rewardDelta.get(dignity_Public) > -10)
		// && (rewardDelta.get(time) < 50) && (rewardDelta.get(worth) > 0)) {
		// return 50;
		// } else if ((rewardDelta.get(dignity_Self) > -25)
		// && (rewardDelta.get(dignity_Public) > -25)
		// && (rewardDelta.get(time) < 50) && (rewardDelta.get(worth) > 0)) {
		// return 30;
		//
		// } else if ((rewardDelta.get(dignity_Self) > -50)
		// && (rewardDelta.get(dignity_Public) > -50)
		// && (rewardDelta.get(time) > 50) && (rewardDelta.get(worth) > 0)) {
		// return -10;
		//
		// } else if ((rewardDelta.get(dignity_Self) < -5)
		// && (rewardDelta.get(dignity_Public) < -25)
		// && (rewardDelta.get(time) < 50)
		// && (rewardDelta.get(worth) == 0)) {
		// return -50;
		//
		// } else if ((rewardDelta.get(dignity_Self) < -10)
		// && (rewardDelta.get(dignity_Public) < -50)
		// && (rewardDelta.get(time) > 50)
		// && (rewardDelta.get(worth) == 0)) {
		// return -100;
		//
		// } else if ((rewardDelta.get(dignity_Public) > -25)
		// && (rewardDelta.get(time) > 50) && (rewardDelta.get(worth) > 0)) {
		// return 30;
		// } else if ((rewardDelta.get(dignity_Public) < -25)
		// && (rewardDelta.get(time) > 50)
		// && (rewardDelta.get(worth) == 0)) {
		// return -100;
		// }
		//
		// return reward;
	}

	/**
	 * Predefined policy for choosing actions. Never give up policy for seller.
	 * 
	 * @param state
	 * @param pastAction
	 * @return
	 */
	public String getActionForStatePolicy(Scenario scenario) {
		String state = scenario.getCurrentProgressState();
		double timeSpent = socialAgent.getCSSMValue(
				CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME, Actors.SELLER,
						Actors.SELLER, Actors.SELLER)).getValue();

		String action = null;
		switch (state) {
		case "S0":
			return "alpha-1";
		case "S3":
			if (scenario.isPastAction("alpha-8"))
				return "alpha-6";
			return "alpha-5";
		case "S6":

			if (timeSpent >= 50) {
				return "alpha-12";
			}
			return "alpha-11";
		case "S9":
			if (timeSpent >= 100) {
				return "alpha-12";
			}
			return "alpha-11";
		case "S7":
			if (scenario.isPastAction("alpha-13"))
				return "alpha-14";
			return "alpha-13";
		default:

		}

		return action;
	}
}
