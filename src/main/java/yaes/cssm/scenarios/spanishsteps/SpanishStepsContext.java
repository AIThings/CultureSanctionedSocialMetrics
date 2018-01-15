package yaes.cssm.scenarios.spanishsteps;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Random;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ParseActions;
import yaes.cssm.behaviors.aselInteractive;
import yaes.cssm.behaviors.behInteractive;
import yaes.cssm.behaviors.behSpStepClientSimple;
import yaes.cssm.behaviors.behSpStepSellerReinforce;
import yaes.cssm.behaviors.sselDefault;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.text.TextUi;
import yaes.util.FileWritingUtil;

public class SpanishStepsContext extends SocialCalculusContext {

	public static final String SPANISH_STEPS_RANDOM_CLIENT = "SpanishStepsRandomClient";
	public static final String SPANISH_STEPS_LEARNING_ITERATION = "SpanishStepsLearningIteration";
	private static final long serialVersionUID = -7436772332667075499L;
	// the random number generator will be used to generate different types of
	// clients
	Random rand;
	/**
	 * The current iteration where we are. If this is 0 the agent must start
	 * with default weights. If it is > 0, the agent starts with the weights
	 * from learningIteration-1 and saves it to learningIteration
	 */
	int learningIteration;

	public SpanishStepsContext() {
		super();
	}

	@Override
	public void createCheckpoint() {
		super.createCheckpoint();
		// Save weights of all agents. This will help in starting new simulation
		// and update weights with these.
		saveWeights(currentScenario, currentAction);
	}

	/**
	 * Overriding the initialize from SocialCalculusContext
	 */
	@Override
	public void initialize(SimulationInput sip, SimulationOutput sop) {
		// calling socialcalculuscontext
		super.initialize(sip, sop);
		String directoryName = sip.getParameterString(DIR_HISTORY);
		dirCheckpoints = new File(directoryName);
		dirWeights = new File(sip.getParameterString(DIR_WEIGHTS));
		scenarioSet = new ScenarioSet();
		// initialize the random number generator
		learningIteration = sip
				.getParameterInt(SPANISH_STEPS_LEARNING_ITERATION);
		// a little trick to allow iterating on only one step on learning
		// if we are learning, use the iteration as random seed
		int randomSeed = sip.getParameterInt(SPANISH_STEPS_RANDOM_CLIENT);
		if (randomSeed == -1) {
			randomSeed = learningIteration;
		}
		rand = new Random(randomSeed);
		initScenarioSet();
		if (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
			preplannedActions = ParseActions.getActionList(
					sip.getParameterString(ACTION_FILE_READ), scenarioSet);
		}
	}

	/**
	 * Initializes the collection of the scenarios currently running.
	 * 
	 * Right now, this is hardwired to a single scenario, with specific users and
	 * specific behaviors, this will need to be changed appropriately.
	 * 
	 *  The setup with the "interactive" is the one which can be used for 
	 *  step-by-step or scripted work.
	 *  
	 *  The setup with the "reinforcement" is the one which should be used for
	 *  Taranjeet's work with the reinforcement learning.
	 * 
	 */
	@Override
	protected void initScenarioSet() {

		// create the agents here, agents can be shared among the scenarios
		SocialAgent agentSeller = new SocialAgent("Crafty");
		agents.put(agentSeller.getName(), agentSeller);

		SocialAgent agentClient = new SocialAgent("John");
		agents.put(agentClient.getName(), agentClient);

		SocialAgent agentCrowd = new SocialAgent("CrowdOfOnlookers");
		agents.put(agentCrowd.getName(), agentCrowd);

		SocialAgent agentSpouse = new SocialAgent("Mary");
		agents.put(agentSpouse.getName(), agentSpouse);

		// create new Scenario, assign the Agents to Actors, Initialize the
		// scenario, add Scenario to the ScenarioSet
		Scenario scenario = DefineScenario.initializeScenario("one",
				agentSeller, agentClient, agentSpouse, agentCrowd);
		scenarioSet.addScenario(scenario);
		// registers the AIF
		registerAIF(scenario.getScenarioType(), new DefineAif());
		// register the selectors
		registerScenarioSelector(new sselDefault());
		registerActorSelector(scenario, new aselInteractive(false));

		//
		// The setup of the reinforcement learning code used by Taranjeet for
		// the AAMAS 2013 submission. The seller behavior uses reinforcement
		// learning while the client is chosen randomly
		//
		boolean setupReinforcementLearning = false;
		if (setupReinforcementLearning) {
			behSpStepSellerReinforce bi1 = new behSpStepSellerReinforce(sip,
					scenario, agentSeller, Actors.SELLER, true, dirWeights);
			registerBehavior(bi1);

			int random = rand.nextInt(ClientType.values().length);
			ClientType clientType = ClientType.values()[random];
			TextUi.println("########## Client Type: " + clientType.toString()
					+ "#################");

			behSpStepClientSimple bi2 = new behSpStepClientSimple(scenario,
					agentClient, Actors.CLIENT, clientType);
			registerBehavior(bi2);
		}
		// The setup of interactive seller and interactive client. This setup is
		// also appropriate for the case when we are studying pre-canned
		// scenarios
		boolean setupInteractive = true;
		if (setupInteractive) {
			// register the behaviors of Interactive Seller
			behInteractive bi1 = new behInteractive(scenario, agentSeller,
					Actors.SELLER, true);
			registerBehavior(bi1);

			// // register the predefined simple seller behavior
			// behSpStepSellerSimple bi1 = new behSpStepSellerSimple(scenario,
			// agentHassan, ACTOR_SELLER, true, 30, 25, 25);
			// registerBehavior(bi1);

			// register the behaviors of Interactive Client
			behInteractive bi2 = new behInteractive(scenario, agentClient,
					Actors.CLIENT, true);
			registerBehavior(bi2);

			// // register the predefined simple client behavior
			// behSpStepClientSimple bi2 = new behSpStepClientSimple(scenario,
			// agentJill, ACTOR_CLIENT, true, 0.5, 0.5, 0.3, 0.3, 25, 25);
			// registerBehavior(bi2);

			// register the predefined simple client behavior
		}
	}

	/**
	 * I assume that this is somehow related to the learning agents 
	 * 	
	 * @param scenario
	 * @param sa
	 */
	public void saveWeights(Scenario scenario, Action sa) {
		SimulationOutput output = new SimulationOutput();
		// Save agent's feature weights
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			for (String actor : agents.keySet()) {
				File outputFile = new File(dirWeights, actor);
				StringBuilder str = new StringBuilder();
				for (CSSM cssm : agents.get(actor).getCSSMs()) {
					double weights = agents.get(actor).getWeight(cssm);
					// TextUi.println("$$$$$$$$$  Weights value in Saving in the file: "+
					// weights);
					str.append(cssm.getPseudoName() + ":" + df.format(weights));
					str.append("\n");
					output.update(cssm.getPseudoName(), agents.get(actor)
							.getWeight(cssm));
				}
				for (ConcreteBelief cb : agents.get(actor).getCBs()) {
					double weights = agents.get(actor).getWeight(cb);
					// TextUi.println("$$$$$$$$$  Weights value in Saving in the file: "+
					// weights);
					str.append(cb.getPseudoName() + ":" + df.format(weights));
					str.append("\n");
				}
				FileWritingUtil.writeToTextFile(outputFile, str.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
