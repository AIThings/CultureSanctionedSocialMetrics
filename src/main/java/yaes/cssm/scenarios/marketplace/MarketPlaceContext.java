package yaes.cssm.scenarios.marketplace;

import java.io.File;
import java.text.DecimalFormat;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ParseActions;
import yaes.cssm.behaviors.aselInteractive;
import yaes.cssm.behaviors.sselDefault;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.scenarios.spanishsteps.DefineAif;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.util.FileWritingUtil;

public class MarketPlaceContext extends SocialCalculusContext {

	private static final long serialVersionUID = -6680730651629152331L;
	@Override
	public void initialize(SimulationInput sip, SimulationOutput sop) {
		super.initialize(sip, sop);
		String directoryName = sip.getParameterString(DIR_HISTORY);
		dirCheckpoints = new File(directoryName);
		dirWeights = new File(sip.getParameterString(DIR_WEIGHTS));
		scenarioSet = new ScenarioSet();
		initScenarioSet();
		if (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
			preplannedActions = ParseActions.getActionList(
					sip.getParameterString(ACTION_FILE_READ), scenarioSet);
		}
	}
	@Override
	protected void initScenarioSet() {
		// create the agents here, agents can be shared among the scenarios
		SocialAgent agentHassan = new SocialAgent("Hassan");
		agents.put(agentHassan.getName(), agentHassan);

		SocialAgent agentAslam = new SocialAgent("Aslam");
		agents.put(agentAslam.getName(), agentAslam);

		SocialAgent agentAli = new SocialAgent("Ali");
		agents.put(agentAli.getName(), agentAli);

		SocialAgent agentSimon = new SocialAgent("Simon");
		agents.put(agentSimon.getName(), agentSimon);

		SocialAgent agentJohn = new SocialAgent("John");
		agents.put(agentJohn.getName(), agentJohn);

		SocialAgent agentRT13 = new SocialAgent("robot13");
		agents.put(agentRT13.getName(), agentRT13);

		SocialAgent agentCrowd = new SocialAgent("CrowdOfOnlookers");
		agents.put(agentCrowd.getName(), agentCrowd);

		// create new Scenario, assign the Agents to Actors, Initialize the
		// scenario, add Scenario to the ScenarioSet
		Scenario scenario1 = MarketPlaceHelper.initializeScenario("one",
				agentSimon, agentJohn, agentHassan, agentRT13, agentCrowd);
		scenarioSet.addScenario(scenario1);

		Scenario scenario2 = MarketPlaceHelper.initializeScenario("two",
				agentSimon, agentJohn, agentAslam, agentRT13, agentCrowd);
		scenarioSet.addScenario(scenario2);

		Scenario scenario3 = MarketPlaceHelper.initializeScenario("three",
				agentSimon, agentJohn, agentAli, agentRT13, agentCrowd);
		scenarioSet.addScenario(scenario3);
		
		// registers the AIF
		registerAIF(scenario1.getScenarioType(), new DefineAif());
		// register the selectors
		registerScenarioSelector(new sselDefault());
		registerActorSelector(scenario1, new aselInteractive(false));

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

	/**
	 * Initializes the environment
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	// public void initialize(SimulationInput sip, SimulationOutput sop) {
	// super.initialize(sip, sop);
	// field = new SimpleField(200, 200);
	// physicalWorld = new PhysicalWorld(field, sop);
	// theWorld = physicalWorld;
	// socialScenario = new OldSocialScenario(theWorld);
	// socialScenario.asg = MarketPlaceHelper.createActionStateGraph();
	// socialScenario.sass = MarketPlaceHelper.getActions();
	// socialScenario.agsi = new ProgressGraphInstance(socialScenario.asg, "S0",
	// socialScenario.sass);
	//
	// // the vendor 1
	// ActionValueImpactMatrix ImpactMatrix = MarketPlaceHelper
	// .getSellerImpactMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerValues());
	// ActionBeliefImpactMatrix BeliefMatrxix = MarketPlaceHelper
	// .getSellerBeliefMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerCBSet(),
	// PerceptionImpactFunction.initializeMassFunctions());
	//
	// SocialAgent agent = new SocialAgent("VendorHassan",
	// MarketPlaceHelper.getSellerValues(),
	// MarketPlaceHelper.getSellerCBSet(), ImpactMatrix,
	// BeliefMatrxix);
	//
	// socialScenario.agents.put(agent.getName(), agent);
	//
	// // the vendor 2
	// ImpactMatrix = MarketPlaceHelper
	// .getSellerImpactMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerValues());
	// BeliefMatrxix = MarketPlaceHelper
	// .getSellerBeliefMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerCBSet(),
	// PerceptionImpactFunction.initializeMassFunctions());
	//
	// agent = new SocialAgent("VendorAslam",
	// MarketPlaceHelper.getSellerValues(),
	// MarketPlaceHelper.getSellerCBSet(), ImpactMatrix,
	// BeliefMatrxix);
	//
	// socialScenario.agents.put(agent.getName(), agent);
	//
	// // the vendor 3
	// ImpactMatrix = MarketPlaceHelper
	// .getSellerImpactMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerValues());
	// BeliefMatrxix = MarketPlaceHelper
	// .getSellerBeliefMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSellerCBSet(),
	// PerceptionImpactFunction.initializeMassFunctions());
	//
	// agent = new SocialAgent("VendorAli", MarketPlaceHelper.getSellerValues(),
	// MarketPlaceHelper.getSellerCBSet(), ImpactMatrix,
	// BeliefMatrxix);
	//
	// socialScenario.agents.put(agent.getName(), agent);
	//
	// // the sergeant
	// ImpactMatrix =
	// MarketPlaceHelper.getSergeantImpactMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSergeantValues());
	// BeliefMatrxix =
	// MarketPlaceHelper.getSergeantBeliefMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSergeantCBSet(),
	// PerceptionImpactFunction.initializeMassFunctions());
	//
	// agent = new SocialAgent("SergeantBob",
	// MarketPlaceHelper.getSergeantValues(),
	// MarketPlaceHelper.getSergeantCBSet(), ImpactMatrix,
	// BeliefMatrxix);
	//
	// socialScenario.agents.put(agent.getName(), agent);
	//
	// // the robot
	// ImpactMatrix =
	// MarketPlaceHelper.getSergeantImpactMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSergeantValues());
	// BeliefMatrxix =
	// MarketPlaceHelper.getSergeantBeliefMatrix(socialScenario.sass,
	// MarketPlaceHelper.getSergeantCBSet(),
	// PerceptionImpactFunction.initializeMassFunctions());
	//
	// agent = new SocialAgent("robot",
	// MarketPlaceHelper.getSergeantValues(),
	// MarketPlaceHelper.getSergeantCBSet(), ImpactMatrix,
	// BeliefMatrxix);
	//
	// socialScenario.agents.put(agent.getName(), agent);
	//
	// socialScenario.initHistory();
	// if (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
	// socialScenario.preplannedActions = ParseActions.getActionList(
	// sip.getParameterString(ACTION_FILE_READ), socialScenario.agsi);
	// }
	// //preplannedActions =
	// ParseActions.getActionList(sip.getParameterString(ACTION_FILE_WRITE),
	// agsi);
	//
	// }

	/*
	 * @Override public double getEffectOnWorld() {
	 * 
	 * return 0; }
	 * 
	 * 
	 * @Override public void setEffectOnWorld(double distance) {
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Override public double getWorldPerception(String agentName,
	 * ConcreteBelief cb) {
	 * 
	 * return 0; }
	 * 
	 * 
	 * @Override public String getCurrentClient() {
	 * 
	 * return null; }
	 * 
	 * 
	 * @Override public String getCurrentSeller() {
	 * 
	 * return null; }
	 */
}
