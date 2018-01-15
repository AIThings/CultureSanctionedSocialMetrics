package yaes.cssm.scenarios.checkpoint;

import java.util.HashMap;
import java.util.Map;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.actions.ProgressState;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.CSSMValue;
import yaes.cssm.cssm.CbDempsterShafer;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.spanishsteps.Actors;

/**
 * Checkpoint Helper class for scenario, action state graph, concrete belief and
 * CSSM
 * 
 * @author Taranjeet
 * 
 */
public class CheckPointHelper implements Constants {

	public static Scenario initializeScenario(String scenarioInstance,
			SocialAgent agentSergeant, SocialAgent agentPrivate,
			SocialAgent agentVendor, SocialAgent agentRobot,
			SocialAgent agentCrowd) {

		Scenario scenario = new Scenario(CULTURE_EASTERN, scenarioInstance,
				getActions());

		// Initialize ProgressGraph
		initializeProgressGraph(scenario);

		// set initial state to S0
		scenario.setCurrentProgressState("S0");

		// Assign Agents to Actors
		scenario.setActorPlayedBy(ACTOR_SERGEANT, agentSergeant);
		scenario.setActorPlayedBy(ACTOR_PRIVATE, agentPrivate);
		scenario.setActorPlayedBy(ACTOR_VENDOR, agentVendor);
		scenario.setActorPlayedBy(ACTOR_ROBOT, agentRobot);
		scenario.setActorPlayedBy(Actors.CROWD, agentCrowd);

		// initialize CSSM
		Map<CSSM, CSSMValue> sergeantCSSM = getSergeantCSSM(scenario);
		Map<CSSM, CSSMValue> privateCSSM = getPrivateCSSM(scenario);
		Map<CSSM, CSSMValue> vendorCSSM = getVendorCSSM(scenario);
		Map<CSSM, CSSMValue> robotCSSM = getRobotCSSM(scenario);

		// initialize Concrete Beliefs
		Map<ConcreteBelief, ConcreteBeliefValue> sergeantCB = getSergeantCB(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> privateCB = getPrivateCB(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> vendorCB = getVendorCB(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> robotCB = getRobotCB(scenario);

		// Apply CSSM and CB to Agents
		agentSergeant.initializeValues(sergeantCSSM, sergeantCB);
		agentPrivate.initializeValues(privateCSSM, privateCB);
		agentVendor.initializeValues(vendorCSSM, vendorCB);
		agentRobot.initializeValues(robotCSSM, robotCB);

		return scenario;

	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getSergeantCB(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario,
				POSES_A_THREAT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeInitialBeliefs(POSES_A_THREAT));
		retval.put(cb, cbv);

		return retval;
	}

	private static CbDempsterShafer initializeInitialBeliefs(String isAGift) {
		// MassFunction<Character> m_function = new MassFunction<>();
		CbDempsterShafer retval = null;
		if (isAGift == IS_A_FRIEND) {
			retval = new CbDempsterShafer(0.2, 0.2);
		} else if (isAGift == POSES_A_THREAT) {
			retval = new CbDempsterShafer(0.2, 0.2);
		}
		return retval;
	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getPrivateCB(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario,
				POSES_A_THREAT, ACTOR_PRIVATE, ACTOR_PRIVATE);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeInitialBeliefs(POSES_A_THREAT));
		retval.put(cb, cbv);

		return retval;
	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getVendorCB(
			Scenario scenario) {

		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, 
				IS_A_FRIEND, ACTOR_VENDOR, ACTOR_VENDOR);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeInitialBeliefs(IS_A_FRIEND));
		retval.put(cb, cbv);

		return retval;
	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getRobotCB(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, 
				POSES_A_THREAT, ACTOR_ROBOT, ACTOR_ROBOT);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeInitialBeliefs(POSES_A_THREAT));
		retval.put(cb, cbv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getSergeantCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FINANCIAL,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);

		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, SECURITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, Actors.CROWD, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, COOPERATION,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, TRUST,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FRIENDSHIP,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getPrivateCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FINANCIAL,
				ACTOR_PRIVATE, ACTOR_PRIVATE, ACTOR_PRIVATE);
		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, SECURITY,
				ACTOR_PRIVATE, ACTOR_PRIVATE, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY,
				ACTOR_PRIVATE, ACTOR_PRIVATE, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_PRIVATE, Actors.CROWD, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_PRIVATE, ACTOR_VENDOR, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, COOPERATION,
				ACTOR_PRIVATE, ACTOR_VENDOR, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, TRUST, ACTOR_PRIVATE,
				ACTOR_VENDOR, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FRIENDSHIP,
				ACTOR_PRIVATE, ACTOR_VENDOR, ACTOR_PRIVATE);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getVendorCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FINANCIAL,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, SECURITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, Actors.CROWD, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, COOPERATION,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, TRUST, ACTOR_VENDOR,
				ACTOR_SERGEANT, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FRIENDSHIP,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getRobotCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FINANCIAL,
				ACTOR_ROBOT, ACTOR_ROBOT, ACTOR_ROBOT);
		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, SECURITY,
				ACTOR_ROBOT, ACTOR_ROBOT, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY, ACTOR_ROBOT,
				ACTOR_ROBOT, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_ROBOT, Actors.CROWD, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_ROBOT, ACTOR_VENDOR, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, COOPERATION,
				ACTOR_ROBOT, ACTOR_VENDOR, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, TRUST, ACTOR_ROBOT,
				ACTOR_VENDOR, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, FRIENDSHIP,
				ACTOR_ROBOT, ACTOR_VENDOR, ACTOR_ROBOT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static void initializeProgressGraph(Scenario scenario) {
		scenario.createProgressGraph();
		ProgressGraph pg = scenario.getProgressGraph();

		pg.add("S0", "A1", ACTOR_SERGEANT, "S1");
		pg.add("S0", "A1", ACTOR_VENDOR, "S1");
		pg.add("S0", "A1", ACTOR_PRIVATE, "S1");

		pg.add("S0", "A4", ACTOR_SERGEANT, "TS1");
		pg.add("S0", "A4", ACTOR_VENDOR, "TS1");
		pg.add("S0", "A4", ACTOR_PRIVATE, "TS1");

		pg.add("S1", "A2", ACTOR_SERGEANT, "S2");
		pg.add("S1", "A2", ACTOR_VENDOR, "S2");
		pg.add("S1", "A2", ACTOR_PRIVATE, "S2");

		pg.add("S2", "A8", ACTOR_SERGEANT, "S3");
		pg.add("S2", "A8", ACTOR_PRIVATE, "S3");

		pg.add("S2", "A11", ACTOR_SERGEANT, "S5");
		pg.add("S2", "A11", ACTOR_PRIVATE, "S5");

		pg.add("S2", "A5", ACTOR_VENDOR, "S7");

		pg.add("S2", "A13", ACTOR_SERGEANT, "S0");
		pg.add("S2", "A13", ACTOR_VENDOR, "S0");
		pg.add("S2", "A13", ACTOR_PRIVATE, "S0");

		pg.add("S3", "A9", ACTOR_VENDOR, "S4");
		pg.add("S3", "A10", ACTOR_VENDOR, "S4");

		pg.add("S4", "A11", ACTOR_SERGEANT, "S5");
		pg.add("S4", "A11", ACTOR_PRIVATE, "S5");

		pg.add("S4", "A13", ACTOR_SERGEANT, "S0");
		pg.add("S4", "A13", ACTOR_PRIVATE, "S0");
		pg.add("S4", "A13", ACTOR_VENDOR, "S0");

		pg.add("S5", "A12", ACTOR_ROBOT, "S6");

		pg.add("S6", "A9", ACTOR_VENDOR, "S5");

		pg.add("S6", "A10", ACTOR_VENDOR, "S4");

		pg.add("S7", "A7", ACTOR_SERGEANT, "S2");
		pg.add("S7", "A7", ACTOR_PRIVATE, "S2");

		pg.add("S7", "A6", ACTOR_SERGEANT, "S2");
		pg.add("S7", "A6", ACTOR_PRIVATE, "S2");

		ProgressState asn = new ProgressState("TS1");
		pg.addProgressState(asn);

	}

	private static ActionTypes getActions() {
		ActionTypes actionSet = new ActionTypes();
		// The army personal initiates the conversation // INITIATE_CONVERSATION
		ActionType spec = new ActionType("A1", "Initiates conversation");
		actionSet.addActionType(spec, false);
		// The kebab seller accepts the conversation // ACCEPT_CONVERSATION
		spec = new ActionType("A2", "Accepts the conversation");
		actionSet.addActionType(spec, false);
		// The kebab seller and sergeant keeps on talking
		spec = new ActionType("A3", "Continue conversation");
		actionSet.addActionType(spec, false);
		// The sergeant stops his talk and goes back
		spec = new ActionType("A4", "stop conversation");
		actionSet.addActionType(spec, true);
		// The kebab-seller offers gift to the sergeant
		spec = new ActionType("A5", "Offers gift");
		actionSet.addActionType(spec, false);
		// The sergeant accepts gift from the kebab-seller
		spec = new ActionType("A6", "Accept gift");
		actionSet.addActionType(spec, false);
		// The sergeant declines the gift from the kebab-seller
		spec = new ActionType(
				"A7",
				"Declines gift(Mitigated_level_of_speech, loudness, offensiveness)",
				"Mitigated_Level_of_Speech", "loudness", "offensiveness");
		actionSet.addActionType(spec, false);

		// The sergeant orders the kebab-seller to move
		spec = new ActionType(
				"A8",
				"Order to move(Mitigated_level_of_speech, loudness, offensiveness)",
				"Mitigated_Level_of_Speech", "loudness", "offensiveness");
		actionSet.addActionType(spec, false);

		// The kebab-seller declines the order from the sergeant to move
		spec = new ActionType("A9",
				"Declines order to move(loudness, offensiveness)", "loudness",
				"offensiveness");
		actionSet.addActionType(spec, false);

		// The kebab-seller accepts the order to move
		spec = new ActionType("A10",
				"Accepts order to move(Location, business hr, business activity)");
		actionSet.addActionType(spec, false);

		// The sergeant passes the order to move to robot
		spec = new ActionType("A11", "Order pass to Robot");
		actionSet.addActionType(spec, false);

		// The robot pushes the kebab-seller to move
		spec = new ActionType("A12", "Robot execute order");
		actionSet.addActionType(spec, false);

		// DELAY
		spec = new ActionType("A13", "Delay between conversations (DelayTime)",
				"delayTime");
		actionSet.addActionType(spec, false);

		// kebab seller rejects conversation at this time
		spec = new ActionType("A14", "Reject conversation");
		actionSet.addActionType(spec, false);

		// army person order for search
		spec = new ActionType("A15", "Search order");
		actionSet.addActionType(spec, false);

		// Overnight delay
		spec = new ActionType("A16", "Overnight");
		actionSet.addActionType(spec, false);

		// Kebab seller allow search
		spec = new ActionType("A17", "Allow search");
		actionSet.addActionType(spec, false);

		// Kebab seller reject search
		spec = new ActionType("A18", "Reject search");
		actionSet.addActionType(spec, false);

		return actionSet;
	}

	/*
	 * 
	 * 
	 * 
	 * public static CpActor sergeant = new CpActor(CpActor.ActorType.SERGEANT,
	 * "SergeantBob"); public static CpActor actorPrivate = new CpActor(
	 * CpActor.ActorType.SERGEANT, "SoliderTom"); public static CpActor vendor =
	 * new CpActor(CpActor.ActorType.VENDOR, "VendorHassan"); public static
	 * CpActor robot = new CpActor(CpActor.ActorType.ROBOT, "R2D2");
	 *//**
	 * Returns the values of the kebab-seller
	 * 
	 * @return
	 */

	// public static CSSMSet getSellerValues() {
	// CSSMSet s2v2 = new CSSMSet(VENDOR_CSSM);
	//
	// CSSM spec = new CSSM(CHECK_POINT, FINANCIAL, VENDOR, VENDOR, VENDOR, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, SECURITY, VENDOR, VENDOR, VENDOR, 0, 0, 0);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100,
	// 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, POLITENESS, VENDOR, ACTOR_CROWD, VENDOR,
	// 0, 100, 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, POLITENESS, VENDOR, SERGEANT, VENDOR, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, COOPERATION, VENDOR, SERGEANT, VENDOR, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, TRUST, VENDOR, SERGEANT, VENDOR, 0, 100,
	// 50);
	// s2v2.addCSSM(spec);
	//
	// spec = new CSSM(CHECK_POINT, FRIENDSHIP, VENDOR, SERGEANT, VENDOR, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	//
	// return s2v2;
	// }

	// public static ConcreteBeliefSet getSellerCBSet() {
	//
	// ConcreteBeliefSet cbSet = new ConcreteBeliefSet(VENDOR_CSSM);
	//
	// ConcreteBelief cb = new ConcreteBelief(CHECK_POINT, IS_A_FRIEND,
	// VENDOR, VENDOR, 0, 1.0, 0);
	// cbSet.add(cb);
	//
	// return cbSet;
	//
	// }

	/**
	 * Returns the values of the sergeant
	 * 
	 * @return
	 */

	// public static CSSMSet getSergeantValues() {
	//
	// CSSMSet s2v2 = new CSSMSet(SERGEANT_CSSM);
	// CSSM spec = new CSSM(CHECK_POINT, FINANCIAL, SERGEANT, SERGEANT,
	// SERGEANT, 0, 0, 0);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, SECURITY, SERGEANT, SERGEANT, SERGEANT, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, DIGNITY, SERGEANT, SERGEANT, SERGEANT, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, POLITENESS, SERGEANT, ACTOR_CROWD,
	// SERGEANT, 0, 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, POLITENESS, SERGEANT, VENDOR, SERGEANT, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, COOPERATION, SERGEANT, VENDOR, SERGEANT,
	// 0, 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, TRUST, SERGEANT, VENDOR, SERGEANT, 0, 100,
	// 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, FRIENDSHIP, SERGEANT, VENDOR, SERGEANT, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// return s2v2;
	// }

	// public static ConcreteBeliefSet getSergeantCBSet() {
	// ConcreteBeliefSet cbSet = new ConcreteBeliefSet(SERGEANT_CSSM);
	// ConcreteBelief cb = new ConcreteBelief(CHECK_POINT, POSES_A_THREAT,
	// SERGEANT, SERGEANT, 0, 1.0, 0);
	// cbSet.add(cb);
	//
	// return cbSet;
	// }

	/**
	 * Returns the values of the solider
	 * 
	 * @return
	 */

	// public static CSSMSet getSoliderValues() {
	// CSSMSet s2v2 = new CSSMSet(PRIVATE_CSSM);
	// CSSM spec = new CSSM(CHECK_POINT, FINANCIAL, PRIVATE, PRIVATE, PRIVATE,
	// 0, 0, 0);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, SECURITY, PRIVATE, PRIVATE, PRIVATE, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, DIGNITY, PRIVATE, PRIVATE, PRIVATE, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, POLITENESS, PRIVATE, ACTOR_CROWD, PRIVATE,
	// 0, 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, POLITENESS, PRIVATE, VENDOR, PRIVATE, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, COOPERATION, PRIVATE, VENDOR, PRIVATE, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, TRUST, PRIVATE, VENDOR, PRIVATE, 0, 100,
	// 50);
	// s2v2.addCSSM(spec);
	// spec = new CSSM(CHECK_POINT, FRIENDSHIP, PRIVATE, VENDOR, PRIVATE, 0,
	// 100, 50);
	// s2v2.addCSSM(spec);
	// return s2v2;
	// }
	/**
	 * Returns the action set specification for the check point
	 * 
	 * @return
	 */

	/**
	 * Creates the action state graph:
	 * 
	 * @return
	 */

	// public static ProgressGraph createActionStateGraph() {
	//
	// String vendor1 = "VendorHassan";
	// String vendor2 = "VendorAslam";
	// String vendor3 = "VendorAli";
	//
	// String army1 = "SergeantBob";
	// String army2 = "privateRyan";
	// String robot = "robot";
	//
	// ProgressGraph asg = new ProgressGraph();
	// asg.addActor(vendor1);
	// asg.addActor(vendor2);
	// asg.addActor(vendor3);
	// asg.addActor(army1);
	// asg.addActor(army2);
	//
	// ProgressState asn = new ProgressState("S0");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A1",
	// Arrays.asList(army1, army2, vendor1, vendor2, vendor3), "S1");
	// asn.addIdenticalProgressForAllActors("A4",
	// Arrays.asList(army1, army2, vendor1, vendor2, vendor3), "TS1");
	//
	// asn = new ProgressState("S1");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A2",
	// Arrays.asList(army1, army2, vendor1, vendor2, vendor3), "S2");
	//
	// asn = new ProgressState("S2");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A8", Arrays.asList(army1, army2),
	// "S3");
	// asn.addIdenticalProgressForAllActors("A11",
	// Arrays.asList(army1, army2), "S5");
	// asn.addIdenticalProgressForAllActors("A5",
	// Arrays.asList(vendor1, vendor2, vendor3), "S7");
	// asn.addIdenticalProgressForAllActors("A13",
	// Arrays.asList(army1, army2, vendor1, vendor2, vendor3), "S0"); // Change
	// // asg.addProgressState(asn);
	//
	// asn = new ProgressState("S3");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A9",
	// Arrays.asList(vendor1, vendor2, vendor3), "S4");
	// asn.addIdenticalProgressForAllActors("A10",
	// Arrays.asList(vendor1, vendor2, vendor3), "S4");
	//
	// asn = new ProgressState("S4");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A11",
	// Arrays.asList(army1, army2), "S5");
	// asn.addIdenticalProgressForAllActors("A13",
	// Arrays.asList(army1, army2, vendor1, vendor2, vendor3), "S0");
	//
	// asn = new ProgressState("S5");
	// asg.addProgressState(asn);
	// asn.addProgress("A12", robot, "S6");
	//
	// asn = new ProgressState("S6");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A9",
	// Arrays.asList(vendor1, vendor2, vendor3), "S5");
	// asn.addIdenticalProgressForAllActors("A10",
	// Arrays.asList(vendor1, vendor2, vendor3), "S4");
	//
	// asn = new ProgressState("S7");
	// asg.addProgressState(asn);
	// asn.addIdenticalProgressForAllActors("A7", Arrays.asList(army1, army2),
	// "S2");
	// asn.addIdenticalProgressForAllActors("A6", Arrays.asList(army1, army2),
	// "S2");
	//
	// asn = new ProgressState("TS1");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// return asg;
	//
	// }
	//
	/**
	 * The impact matrix of the kebab seller
	 * 
	 * @param actionSet
	 * @param socialValues
	 * @return
	 */

	// public static ActionValueImpactMatrix getSellerImpactMatrix(
	// ActionTypes actionSet, CSSMSet socialValues) {
	// ActionValueImpactMatrix am = new ActionValueImpactMatrix(socialValues,
	// actionSet);
	//
	// // Financial - S1
	// CSSM cssm = new CSSM(CHECK_POINT, FINANCIAL, ACTOR_VENDOR,
	// ACTOR_VENDOR, ACTOR_VENDOR, 0, 100, 50);
	//
	// am.setImpact(cssm, "A10",
	// new VendorImpactFunction(VIFType.Financial_A1));
	//
	// // Security - S2 [There are no such security issues for the vendor]
	// cssm = new CSSM(CHECK_POINT, SECURITY, ACTOR_VENDOR, ACTOR_VENDOR,
	// ACTOR_VENDOR, 0, 0, 0);
	//
	// // Dignity - S3
	// cssm = new CSSM(CHECK_POINT, DIGNITY, ACTOR_VENDOR, ACTOR_VENDOR,
	// ACTOR_VENDOR, 0, 100, 50);
	//
	// am.setImpact(cssm, "A8", new VendorImpactFunction(VIFType.DIGNITY_A6));
	//
	// am.setImpact(cssm, "A7", new VendorImpactFunction(VIFType.DIGNITY_A9));
	// am.setImpact(cssm, "A12", -1);
	// am.setImpact(cssm, "A13", new VendorImpactFunction(VIFType.DIGNITY_A11));
	//
	// // Public politeness - S4
	// cssm = new CSSM(CHECK_POINT, POLITENESS, ACTOR_VENDOR, ACTOR_CROWD,
	// ACTOR_VENDOR, 0, 100, 50);
	//
	// am.setImpact(cssm, "A9", new VendorImpactFunction(
	// VIFType.POLITENESS_PUBLIC_A2));
	// am.setImpact(cssm, "A5", 2);
	//
	// // Peer politeness - S5
	// cssm = new CSSM(CHECK_POINT, POLITENESS, ACTOR_VENDOR, ACTOR_SERGEANT,
	// ACTOR_VENDOR, 0, 100, 50);
	//
	// am.setImpact(cssm, "A8", new VendorImpactFunction(
	// VIFType.POLITENESS_PEER_A6));
	// am.setImpact(cssm, "A6", 2);
	// am.setImpact(cssm, "A7", new VendorImpactFunction(
	// VIFType.POLITENESS_PEER_A9));
	// am.setImpact(cssm, "A13", new VendorImpactFunction(
	// VIFType.POLITENESS_PEER_A11));
	//
	// // Cooperation-S6
	// cssm = new CSSM(CHECK_POINT, COOPERATION, ACTOR_VENDOR, ACTOR_SERGEANT,
	// ACTOR_VENDOR, 0, 100, 50);
	// am.setImpact(cssm, "A9", -10);
	// am.setImpact(cssm, "A2", new VendorImpactFunction(VIFType.S6_A5));
	// am.setImpact(cssm, "A12", -15);
	//
	// // Trust-S7
	// cssm = new CSSM(CHECK_POINT, TRUST, ACTOR_VENDOR, ACTOR_SERGEANT,
	// ACTOR_VENDOR, 0, 100, 50);
	// am.setImpact(cssm, "A10", 5);
	// am.setImpact(cssm, "A9", -20);
	//
	// // Friendship-S8
	// cssm = new CSSM(CHECK_POINT, FRIENDSHIP, ACTOR_VENDOR, ACTOR_SERGEANT,
	// ACTOR_VENDOR, 0, 100, 50);
	//
	// am.setImpact(cssm, "A9", -10);
	// am.setImpact(cssm, "A2", new VendorImpactFunction(VIFType.S8_A5));
	// am.setImpact(cssm, "A8", new VendorImpactFunction(VIFType.S8_A6));
	// am.setImpact(cssm, "A7", -5);
	//
	// return am;
	// }
	//
	// public static ActionBeliefImpactMatrix getSellerBeliefMatrix(
	// ActionTypes actionSet, ConcreteBeliefSet socialValues,
	// Map<String, MassFunction<Character>> massFunctions) {
	//
	// ActionBeliefImpactMatrix am = new ActionBeliefImpactMatrix(
	// socialValues, actionSet);
	// ConcreteBelief cb = new ConcreteBelief(CHECK_POINT, IS_A_FRIEND,
	// VENDOR, VENDOR, 0, 1.0, 0);
	//
	// am.setImpact(cb, "A13", new PerceptionImpactFunction(PIFType.FRIEND_11,
	// massFunctions));
	//
	// return am;
	// }
	//
	// /**
	// * Sergeant Action Value Matrix /*
	// *
	// * @param actionSet
	// *
	// * @param socialValues
	// *
	// * @return
	// */
	//
	// public static ActionValueImpactMatrix getSergeantImpactMatrix(
	// ActionTypes actionSet, CSSMSet socialValues) {
	//
	// ActionValueImpactMatrix am = new
	// ActionValueImpactMatrix(socialValues,actionSet);
	//
	// // Financial - S1
	// CSSM cssm = new CSSM(CHECK_POINT, FINANCIAL, SERGEANT, SERGEANT,SERGEANT,
	// 0, 0, 0);
	//
	// // Security - S2
	// cssm = new CSSM(CHECK_POINT, SECURITY, SERGEANT, SERGEANT, SERGEANT, 0,
	// 100, 50);
	//
	// am.setImpact(cssm, "A9", new
	// SergeantImpactFunction(SergeantIFType.SECURITY_A2));
	// // am.setImpact(value, CpAction.ActionType.DELINES_TO_MOVE.name(), -20);
	//
	// // Dignity - S3
	// cssm = new CSSM(CHECK_POINT, DIGNITY, SERGEANT, SERGEANT, SERGEANT, 0,
	// 100, 50);
	//
	// am.setImpact(cssm, "A9", new SergeantImpactFunction(
	// SergeantIFType.DIGNITY_A2));
	// am.setImpact(cssm, "A13", new SergeantImpactFunction(
	// SergeantIFType.DIGNITY_A11));
	//
	// // Public politeness - S4
	// cssm = new CSSM(CHECK_POINT, POLITENESS, SERGEANT, ACTOR_CROWD,
	// SERGEANT, 0, 100, 50);
	//
	// am.setImpact(cssm, "A2", 0.2);
	//
	// am.setImpact(cssm, "A8", new SergeantImpactFunction(
	// SergeantIFType.POLITENESS_PUBLIC_A6));
	// am.setImpact(cssm, "A6", 2);
	// am.setImpact(cssm, "A7", new SergeantImpactFunction(
	// SergeantIFType.POLITENESS_PUBLIC_A9));
	//
	//
	// // Peer politeness - S5
	// cssm = new CSSM(CHECK_POINT, POLITENESS, SERGEANT, VENDOR, SERGEANT, 0,
	// 100, 50);
	// am.setImpact(cssm, "A9", new SergeantImpactFunction(
	// SergeantIFType.POLITENESS_PEER_A2));
	// am.setImpact(cssm, "A5", 2);
	// am.setImpact(cssm, "A13", new SergeantImpactFunction(
	// SergeantIFType.POLITENESS_PEER_A11));
	//
	// // Cooperation - S6
	// cssm = new CSSM(CHECK_POINT, COOPERATION, SERGEANT, VENDOR, SERGEANT,
	// 0, 100, 50);
	// am.setImpact(cssm, "A10", new SergeantImpactFunction(
	// SergeantIFType.S6_A1));
	// am.setImpact(cssm, "A9", -10);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A2", new VendorImpactFunction(VIFType.S6_A5));
	// am.setImpact(cssm, "A6", 10);
	//
	// // Trust - S7
	// cssm = new CSSM(CHECK_POINT, TRUST, SERGEANT, VENDOR, SERGEANT, 0, 100,
	// 50);
	// am.setImpact(cssm, "A10", 10);
	// am.setImpact(cssm, "A9", -10);
	//
	// // Friendship - S8
	// cssm = new CSSM(CHECK_POINT, FRIENDSHIP, SERGEANT, VENDOR, SERGEANT, 0,
	// 100, 50);
	// am.setImpact(cssm, "A9", -10);
	// am.setImpact(cssm, "A2", new VendorImpactFunction(VIFType.S8_A5));
	// am.setImpact(cssm, "A8", -10);
	// am.setImpact(cssm, "A6", 10);
	// am.setImpact(cssm, "A7", new SergeantImpactFunction(
	// SergeantIFType.S8_A9));
	// am.setImpact(cssm, "A12", -20);
	//
	// return am;
	// }
	// /**
	// * Returns the values of the public perception
	// *
	// * @return
	// */
	//
	// public static ActionBeliefImpactMatrix
	// getSergeantBeliefMatrix(ActionTypes actionSet, ConcreteBeliefSet
	// socialValues, Map<String, MassFunction<Character>> massFunctions)
	// {
	//
	// ActionBeliefImpactMatrix am = new ActionBeliefImpactMatrix( socialValues,
	// actionSet);
	//
	// ConcreteBelief cb = new ConcreteBelief(CHECK_POINT, POSES_A_THREAT,
	// SERGEANT, SERGEANT, 0, 1.0, 0);
	//
	// am.setImpact(cb, "A9", new PerceptionImpactFunction(PIFType.THREAT_2,
	// massFunctions));
	//
	// return am;
	//
	// }

}
