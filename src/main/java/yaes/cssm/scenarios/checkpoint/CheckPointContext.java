package yaes.cssm.scenarios.checkpoint;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;

public class CheckPointContext extends SocialCalculusContext {

	private static final long serialVersionUID = -6680730651629152331L;

	@Override
	protected void initScenarioSet() {
		// create the agents here, agents can be shared among the scenarios
		SocialAgent agentHassan = new SocialAgent("Hassan");
		agents.put(agentHassan.getName(), agentHassan);

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
		Scenario scenario = CheckPointHelper.initializeScenario(
				"one", agentSimon,agentJohn, agentHassan,agentRT13, agentCrowd);
		scenarioSet.addScenario(scenario);

	}

	/*
	 * private SimpleField field; private PhysicalWorld physicalWorld;
	 * OldSocialScenario socialScenario;
	 */
	/**
	 * Initializes the environment
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	/*
	 * public void initialize(SimulationInput sip, SimulationOutput sop) {
	 * super.initialize(sip, sop); field = new SimpleField(200, 200);
	 * physicalWorld = new PhysicalWorld(field, sop); theWorld = physicalWorld;
	 * socialScenario = new OldSocialScenario(theWorld); socialScenario.asg =
	 * CheckPointHelper.createActionStateGraph(); socialScenario.sass =
	 * CheckPointHelper.getActions(); socialScenario.agsi = new
	 * ProgressGraphInstance(socialScenario.asg, "S0", socialScenario.sass);
	 * 
	 * // the vendor 1 ActionValueImpactMatrix ImpactMatrix = CheckPointHelper
	 * .getSellerImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerValues()); ActionBeliefImpactMatrix
	 * BeliefMatrxix = CheckPointHelper
	 * .getSellerBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * SocialAgent agent = new SocialAgent("VendorHassan",
	 * CheckPointHelper.getSellerValues(), CheckPointHelper.getSellerCBSet(),
	 * ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * // the vendor 2 ImpactMatrix = CheckPointHelper
	 * .getSellerImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerValues()); BeliefMatrxix = CheckPointHelper
	 * .getSellerBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * agent = new SocialAgent("VendorAslam",
	 * CheckPointHelper.getSellerValues(), CheckPointHelper.getSellerCBSet(),
	 * ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * // the vendor 3 ImpactMatrix = CheckPointHelper
	 * .getSellerImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerValues()); BeliefMatrxix = CheckPointHelper
	 * .getSellerBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSellerCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * agent = new SocialAgent("VendorAli", CheckPointHelper.getSellerValues(),
	 * CheckPointHelper.getSellerCBSet(), ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * 
	 * // the sergeant ImpactMatrix =
	 * CheckPointHelper.getSergeantImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantValues()); BeliefMatrxix =
	 * CheckPointHelper.getSergeantBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * agent = new SocialAgent("SergeantBob",
	 * CheckPointHelper.getSergeantValues(),
	 * CheckPointHelper.getSergeantCBSet(), ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * // the private ImpactMatrix =
	 * CheckPointHelper.getSergeantImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantValues()); BeliefMatrxix =
	 * CheckPointHelper.getSergeantBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * agent = new SocialAgent("privateRyan",
	 * CheckPointHelper.getSergeantValues(),
	 * CheckPointHelper.getSergeantCBSet(), ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * // the robot ImpactMatrix =
	 * CheckPointHelper.getSergeantImpactMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantValues()); BeliefMatrxix =
	 * CheckPointHelper.getSergeantBeliefMatrix(socialScenario.sass,
	 * CheckPointHelper.getSergeantCBSet(),
	 * PerceptionImpactFunction.initializeMassFunctions());
	 * 
	 * agent = new SocialAgent("robot", CheckPointHelper.getSergeantValues(),
	 * CheckPointHelper.getSergeantCBSet(), ImpactMatrix, BeliefMatrxix);
	 * 
	 * socialScenario.agents.put(agent.getName(), agent);
	 * 
	 * socialScenario.initHistory(); if
	 * (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
	 * socialScenario.preplannedActions = ParseActions.getActionList(
	 * sip.getParameterString(ACTION_FILE_READ), socialScenario.agsi); }
	 * //preplannedActions =
	 * ParseActions.getActionList(sip.getParameterString(ACTION_FILE_WRITE),
	 * agsi);
	 * 
	 * }
	 * 
	 * 
	 * 
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
	 * @Override public String getCurrentSeller() {
	 * 
	 * return null; }
	 */

}
