package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.util.HashMap;
import java.util.Map;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
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
 * 
 * @author Taranjeet
 * 
 *         Helper class provides ACTOR_SELLER CSSMs, ACTOR_CLIENT CSSMS,
 *         actions, action state graphs.
 */

public class SpanishStepsHelper implements Constants {

	public static Scenario initializeScenario(String scenarioInstance,
			SocialAgent agentSeller, SocialAgent agentClient,
			SocialAgent agentClientPeer, SocialAgent agentCrowd) {

		Scenario scenario = new Scenario(CULTURE_WESTERN, scenarioInstance,
				getActions());

		// Initialize ProgressGraph
		initializeProgressGraph(scenario);

		// set initial state to S0
		scenario.setCurrentProgressState("S0");

		// Assign Agents to Actors
		scenario.setActorPlayedBy(Actors.SELLER, agentSeller);
		scenario.setActorPlayedBy(Actors.CLIENT, agentClient);
		scenario.setActorPlayedBy(Actors.CROWD, agentCrowd);
		scenario.setActorPlayedBy(Actors.SPOUSE, agentClientPeer);

		// initialize CSSM
		Map<CSSM, CSSMValue> sellerCSSM = getSellerCSSM(scenario);
		Map<CSSM, CSSMValue> clientCSSM = getClientCSSM(scenario);

		// initialize Concrete Beliefs
		Map<ConcreteBelief, ConcreteBeliefValue> sellerCB = getSellerCB(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> clientCB = getClientCB(scenario);

		// Apply the CSSM and CB
		agentSeller.initializeValues(sellerCSSM, sellerCB);
		agentClient.initializeValues(clientCSSM, clientCB);

		return scenario;
	}

	private static CbDempsterShafer initializeMassFunction(String isAGift) {
		CbDempsterShafer retval = null;
		if (isAGift == Q_GIFT) {
			retval = new CbDempsterShafer(0.2, 0.2);
		} else if (isAGift == Q_AGREED) {
			retval = new CbDempsterShafer(0.2, 0.2);
		} else if (isAGift == IS_DECEPTIVE) {
			retval = new CbDempsterShafer(0.2, 0.2);
		}
		return retval;
	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getClientCB(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, Q_GIFT,
				Actors.CLIENT, Actors.CLIENT);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeMassFunction(Q_GIFT));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, Q_AGREED, Actors.CROWD,
				Actors.CLIENT);
		cbv = new ConcreteBeliefValue(initializeMassFunction(Q_AGREED));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, IS_DECEPTIVE, Actors.CLIENT,
				Actors.CLIENT);
		cbv = new ConcreteBeliefValue(initializeMassFunction(IS_DECEPTIVE));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, IS_DECEPTIVE, Actors.CROWD,
				Actors.CLIENT);
		cbv = new ConcreteBeliefValue(initializeMassFunction(IS_DECEPTIVE));
		retval.put(cb, cbv);

		return retval;
	}

	private static Map<ConcreteBelief, ConcreteBeliefValue> getSellerCB(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, Q_GIFT,
				Actors.CLIENT, Actors.SELLER);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(
				initializeMassFunction(Q_GIFT));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, Q_AGREED, Actors.CROWD,
				Actors.SELLER);
		cbv = new ConcreteBeliefValue(initializeMassFunction(Q_AGREED));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, IS_DECEPTIVE,
				Actors.CLIENT, Actors.SELLER);
		cbv = new ConcreteBeliefValue(initializeMassFunction(IS_DECEPTIVE));
		retval.put(cb, cbv);

		cb = ConcreteBelief.createCB(scenario, IS_DECEPTIVE,
				Actors.CROWD, Actors.SELLER);
		cbv = new ConcreteBeliefValue(initializeMassFunction(IS_DECEPTIVE));
		retval.put(cb, cbv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getClientCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.CLIENT,
				Actors.CLIENT, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.SPOUSE, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.SPOUSE, Actors.CLIENT);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static Map<CSSM, CSSMValue> getSellerCSSM(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME,
				Actors.SELLER, Actors.SELLER, Actors.SELLER);
		CSSMValue cv = new CSSMValue(0, 100, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.SELLER,
				Actors.SELLER, Actors.SELLER);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.SELLER, Actors.SELLER, Actors.SELLER);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.SELLER, Actors.CROWD, Actors.SELLER);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.SELLER, Actors.SELLER, Actors.SELLER);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.SELLER, Actors.CROWD, Actors.SELLER);
		cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);

		return retval;
	}

	private static void initializeProgressGraph(Scenario scenario) {
		scenario.createProgressGraph();
		ProgressGraph pg = scenario.getProgressGraph();

		pg.add("S0", "A1", Actors.SELLER, "S1");

		pg.add("S1", "A4", Actors.CLIENT, "S3");
		pg.add("S1", "A2", Actors.CLIENT, "S2");

		pg.add("S2", "A3", Actors.CLIENT, "S11");

		pg.add("S3", "A5", Actors.SELLER, "S4");
		pg.add("S3", "A6", Actors.SELLER, "S5");
		pg.add("S3", "A8", Actors.SELLER, "S12");

		pg.add("S4", "A7", Actors.CLIENT, "S3");
		pg.add("S4", "A10", Actors.CLIENT, "S7");

		pg.add("S5", "A9", Actors.CLIENT, "S6");
		pg.add("S5", "A13", Actors.CLIENT, "S7");
		pg.add("S5", "A21", Actors.CLIENT, "S12");

		pg.add("S6", "A11", Actors.SELLER, "S12");
		pg.add("S6", "A12", Actors.SELLER, "S5");

		pg.add("S7", "A14", Actors.SELLER, "S7");
		pg.add("S7", "A15", Actors.SELLER, "S8");
		pg.add("S7", "A24", Actors.SELLER, "CR");
		pg.add("S22", "A22", Actors.SELLER, "S12");

		pg.add("S8", "A16", Actors.CLIENT, "S9");
		pg.add("S8", "A19", Actors.CLIENT, "S10");

		pg.add("S9", "A17", Actors.SELLER, "S8");
		pg.add("S9", "A18", Actors.SELLER, "S12");

		pg.add("S10", "A20", Actors.CLIENT, "S11");

		// pg.add("S11", "A23", ACTOR_SELLER, "TP1");
		pg.add("S11", "A14", Actors.SELLER, "S11");
		// pg.add("S11", "A24", ACTOR_SELLER, "CR");

		pg.add("S12", "A23", Actors.SELLER, "TP1");
		pg.add("S12", "A14", Actors.SELLER, "S12");
		pg.add("S12", "A24", Actors.SELLER, "CR");
	}

	public static ActionTypes getActions() {
		ActionTypes actionSet = new ActionTypes();
		// A1 S offers flowers -> S1
		ActionType action = new ActionType("A1", "ACTOR_SELLER offers flowers");
		actionSet.addActionType(action, false);

		// A2 C accepts S1 -> S2
		action = new ActionType("A2", "ACTOR_CLIENT accepts");
		actionSet.addActionType(action, false);
		// A3 S+C payment S2 -> TP1
		action = new ActionType("A3",
				"ACTOR_SELLER and ACTOR_CLIENT arrange payment");
		actionSet.addActionType(action, true);
		// A4 C declines S1 -> S3
		action = new ActionType("A4", "ACTOR_CLIENT declines");
		actionSet.addActionType(action, false);
		// A5 S offers gift S3 -> S4
		action = new ActionType("A5", "ACTOR_SELLER offers gift");
		actionSet.addActionType(action, false);
		// A6 S forces gift S3 -> S5
		action = new ActionType("A6", "ACTOR_SELLER forces gift");
		actionSet.addActionType(action, false);
		// A7 C declines(x) S4 -> S3
		action = new ActionType("A7",
				"ACTOR_CLIENT declines(loudness, offensiveness)", "loudness",
				"offensiveness");
		actionSet.addActionType(action, false);
		// A8 S gives up S3 -> TN1
		action = new ActionType("A8", "ACTOR_SELLER gives up");
		actionSet.addActionType(action, true);
		// A9 C attempts return(x) S5 -> S6
		action = new ActionType("A9",
				"ACTOR_CLIENT declines(loudness, offensiveness)", "loudness",
				"offensiveness");
		actionSet.addActionType(action, false);
		// A10 C accepts gift S4 -> S7
		action = new ActionType("A10", "ACTOR_CLIENT accepts gift");
		actionSet.addActionType(action, false);
		// A11 S gives up S6 -> TN1
		action = new ActionType("A11", "ACTOR_SELLER gives up");
		actionSet.addActionType(action, true);
		// A12 S declines return S6 -> S5
		action = new ActionType("A12", "ACTOR_SELLER declines return");
		actionSet.addActionType(action, false);
		// A13 C accepts S5 -> S7
		action = new ActionType("A13", "ACTOR_CLIENT accepts");
		actionSet.addActionType(action, false);
		// A14 S waits(t) S7 -> S7
		action = new ActionType("A14", "ACTOR_SELLER waits(time)", "time");
		actionSet.addActionType(action, false);
		// A15 S requests payment S7 -> S8
		action = new ActionType("A15", "ACTOR_SELLER requests payment");
		actionSet.addActionType(action, false);
		// A16 C attempts return (x) S8 -> S9
		action = new ActionType("A16", "ACTOR_CLIENT attempts return",
				"loudness", "offensiveness");
		actionSet.addActionType(action, false);
		// A17 S declines return S9 -> S8
		action = new ActionType("A17", "ACTOR_SELLER declines return");
		actionSet.addActionType(action, false);
		// A18 S accepts return S9 -> TN2
		action = new ActionType("A18", "ACTOR_SELLER accepts return");
		actionSet.addActionType(action, false);
		// A19 C accepts S8 -> S10
		action = new ActionType("A19", "ACTOR_CLIENT accepts");
		actionSet.addActionType(action, false);
		// A20 S+C payment S10 -> TP2
		action = new ActionType("A20",
				"ACTOR_SELLER and ACTOR_CLIENT agree on payment");
		actionSet.addActionType(action, false);
		// A21 C throws flower S5 -> TN1
		action = new ActionType("A21", "ACTOR_CLIENT throws flower");
		actionSet.addActionType(action, false);

		// A22 S concedes gift S7 -> TF1
		action = new ActionType("A22", "ACTOR_SELLER concedes gift");
		actionSet.addActionType(action, true);

		// A23 S ends today selling
		action = new ActionType("A23", "ACTOR_SELLER ends today's selling");
		actionSet.addActionType(action, true);

		// A24 S ends today selling
		action = new ActionType("A24", "ACTOR_SELLER go to other ACTOR_CLIENT");
		actionSet.addActionType(action, true);

		return actionSet;
	}

	// public static CSSMSet getSellerCSSMset() {
	// CSSMSet cssmSet = new CSSMSet(SELLER_CSSM);
	//
	// CSSM cssm = new CSSM(SPANISH_STEPS, TIME, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 40, 0);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, WORTH, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, -5, 5, 0);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_SELLER, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	//
	//
	// return cssmSet;
	// }
	//
	// public static ConcreteBeliefSet getSellerCBSet() {
	// ConcreteBeliefSet cbSet = new ConcreteBeliefSet(SELLER_CSSM);
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	//
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CLIENT,
	// ACTOR_SELLER, 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_SELLER,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CLIENT,
	// ACTOR_SELLER, 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_SELLER,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// return cbSet;
	//
	// }
	//
	// public static CSSMSet getClientCSSMset() {
	// CSSMSet cssmSet = new CSSMSet(CLIENT_CSSM);
	//
	// CSSM cssm = new CSSM(SPANISH_STEPS, TIME, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 40, 0);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, WORTH, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, -5, 5, 0);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, ACTOR_CROWD,
	// ACTOR_CLIENT, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, CLIENT_PEER,
	// ACTOR_CLIENT, 0, 100,
	// 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, CLIENT_PEER,
	// ACTOR_CLIENT, 0,
	// 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, ACTOR_CROWD,
	// ACTOR_CLIENT, 0, 100, 50);
	// cssmSet.addCSSM(cssm);
	//
	// /**
	// * perception parameter of the ACTOR_CLIENT need to be defined which was
	// * previously defined here. see spanishstepHelper for details.
	// */
	//
	// return cssmSet;
	// }
	//
	// public static ConcreteBeliefSet getClientCBSet() {
	// ConcreteBeliefSet cbSet = new ConcreteBeliefSet(CLIENT_CSSM);
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	//
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_CLIENT,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_CLIENT,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// return cbSet;
	//
	// }
	//
	// /**
	// *
	// * @return ActionSet : provide list of action associated with particular
	// * scenario
	// */
	//
	// public static ActionTypes getActions() {
	// ActionTypes actionSet = new ActionTypes(SPANISH_STEPS_ACTIONS);
	// // A1 S offers flowers -> S1
	// ActionType action = new ActionType("A1","ACTOR_SELLER offers flowers");
	// actionSet.addAction(action);
	//
	// // A2 C accepts S1 -> S2
	// action = new ActionType("A2","ACTOR_CLIENT accepts");
	// actionSet.addAction(action);
	// // A3 S+C payment S2 -> TP1
	// action = new
	// ActionType("A3","ACTOR_SELLER and ACTOR_CLIENT arrange payment");
	// actionSet.addAction(action);
	// // A4 C declines S1 -> S3
	// action = new ActionType("A4","ACTOR_CLIENT declines");
	// actionSet.addAction(action);
	// // A5 S offers gift S3 -> S4
	// action = new ActionType("A5","ACTOR_SELLER offers gift");
	// actionSet.addAction(action);
	// // A6 S forces gift S3 -> S5
	// action = new ActionType("A6","ACTOR_SELLER forces gift");
	// actionSet.addAction(action);
	// // A7 C declines(x) S4 -> S3
	// action = new ActionType("A7",
	// "ACTOR_CLIENT declines(loudness, offensiveness)",
	// "loudness", "offensiveness");
	// actionSet.addAction(action);
	// // A8 S gives up S3 -> TN1
	// action = new ActionType("A8","ACTOR_SELLER gives up");
	// actionSet.addAction(action);
	// // A9 C attempts return(x) S5 -> S6
	// action = new ActionType("A9",
	// "ACTOR_CLIENT declines(loudness, offensiveness)",
	// "loudness", "offensiveness");
	// actionSet.addAction(action);
	// // A10 C accepts gift S4 -> S7
	// action = new ActionType("A10","ACTOR_CLIENT accepts gift");
	// actionSet.addAction(action);
	// // A11 S gives up S6 -> TN1
	// action = new ActionType("A11","ACTOR_SELLER gives up");
	// actionSet.addAction(action);
	// // A12 S declines return S6 -> S5
	// action = new ActionType("A12","ACTOR_SELLER declines return");
	// actionSet.addAction(action);
	// // A13 C accepts S5 -> S7
	// action = new ActionType("A13","ACTOR_CLIENT accepts");
	// actionSet.addAction(action);
	// // A14 S waits(t) S7 -> S7
	// action = new ActionType("A14", "ACTOR_SELLER waits(time)", "time");
	// actionSet.addAction(action);
	// // A15 S requests payment S7 -> S8
	// action = new ActionType("A15","ACTOR_SELLER requests payment");
	// actionSet.addAction(action);
	// // A16 C attempts return (x) S8 -> S9
	// action = new ActionType("A16", "ACTOR_CLIENT attempts return",
	// "loudness",
	// "offensiveness");
	// actionSet.addAction(action);
	// // A17 S declines return S9 -> S8
	// action = new ActionType("A17","ACTOR_SELLER declines return");
	// actionSet.addAction(action);
	// // A18 S accepts return S9 -> TN2
	// action = new ActionType("A18","ACTOR_SELLER accepts return");
	// actionSet.addAction(action);
	// // A19 C accepts S8 -> S10
	// action = new ActionType("A19","ACTOR_CLIENT accepts");
	// actionSet.addAction(action);
	// // A20 S+C payment S10 -> TP2
	// action = new
	// ActionType("A20","ACTOR_SELLER and ACTOR_CLIENT agree on payment");
	// actionSet.addAction(action);
	// // A21 C throws flower S5 -> TN1
	// action = new ActionType("A21","ACTOR_CLIENT throws flower");
	// actionSet.addAction(action);
	//
	// // A22 S concedes gift S7 -> TF1
	// action = new ActionType("A22","ACTOR_SELLER concedes gift");
	// actionSet.addAction(action);
	//
	// // A23 S ends today selling
	// action = new ActionType("A23","ACTOR_SELLER ends today's selling");
	// actionSet.addAction(action);
	//
	// // A24 S ends today selling
	// action = new ActionType("A24","ACTOR_SELLER go to other ACTOR_CLIENT");
	// actionSet.addAction(action);
	//
	// return actionSet;
	// }
	//
	// /**
	// * Creates the action state graph
	// *
	// * @return
	// */
	// public static ProgressGraph createActionStateGraph(String ACTOR_SELLER,
	// String ACTOR_CLIENT) {
	// ProgressGraph asg = new ProgressGraph();
	// asg.addActor(ACTOR_CLIENT);
	// asg.addActor(ACTOR_SELLER);
	//
	// ProgressState asn = new ProgressState("S0");
	// asn.addProgress("A1", ACTOR_SELLER, "S1");
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("S1");
	// asg.addProgressState(asn);
	// asn.addProgress("A2", ACTOR_CLIENT, "S2");
	// asn.addProgress("A4", ACTOR_CLIENT, "S3");
	//
	// asn = new ProgressState("S2");
	// asg.addProgressState(asn);
	// asn.addProgress("A3", ACTOR_CLIENT, "S11");
	//
	// asn = new ProgressState("S3");
	// asg.addProgressState(asn);
	// asn.addProgress("A5", ACTOR_SELLER, "S4");
	// asn.addProgress("A6", ACTOR_SELLER, "S5");
	// asn.addProgress("A8", ACTOR_SELLER, "S12");
	//
	// asn = new ProgressState("S4");
	// asg.addProgressState(asn);
	// asn.addProgress("A7", ACTOR_CLIENT, "S3");
	// asn.addProgress("A10", ACTOR_CLIENT, "S7");
	//
	// asn = new ProgressState("S5");
	// asg.addProgressState(asn);
	// asn.addProgress("A9", ACTOR_CLIENT, "S6");
	// asn.addProgress("A13", ACTOR_CLIENT, "S7");
	// asn.addProgress("A21", ACTOR_CLIENT, "S12");
	//
	// asn = new ProgressState("S6");
	// asg.addProgressState(asn);
	// asn.addProgress("A11", ACTOR_SELLER, "S12");
	// asn.addProgress("A12", ACTOR_SELLER, "S5");
	//
	// asn = new ProgressState("S7");
	// asg.addProgressState(asn);
	// asn.addProgress("A14", ACTOR_SELLER, "S7");
	// asn.addProgress("A15", ACTOR_SELLER, "S8");
	// asn.addProgress("A24", ACTOR_SELLER, "CR");
	// asn.addProgress("A22", ACTOR_SELLER, "S12");
	//
	// asn = new ProgressState("S8");
	// asg.addProgressState(asn);
	// asn.addProgress("A16", ACTOR_CLIENT, "S9");
	// asn.addProgress("A19", ACTOR_CLIENT, "S10");
	//
	// asn = new ProgressState("S9");
	// asg.addProgressState(asn);
	// asn.addProgress("A17", ACTOR_SELLER, "S8");
	// asn.addProgress("A18", ACTOR_SELLER, "S12");
	//
	// asn = new ProgressState("S10");
	// asg.addProgressState(asn);
	// asn.addProgress("A20", ACTOR_CLIENT, "S11");
	//
	// asn = new ProgressState("S11");
	// asg.addProgressState(asn);
	// asn.addProgress("A23", ACTOR_SELLER, "TP1");
	// asn.addProgress("A14", ACTOR_SELLER, "S11");
	// asn.addProgress("A24", ACTOR_SELLER, "CR");
	//
	// asn = new ProgressState("S12");
	// asg.addProgressState(asn);
	// asn.addProgress("A23", ACTOR_SELLER, "TP1");
	// asn.addProgress("A14", ACTOR_SELLER, "S12");
	// asn.addProgress("A24", ACTOR_SELLER, "CR");
	//
	// asn = new ProgressState("CR");
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("TP1");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("TP2");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("TN1");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("TN2");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// asn = new ProgressState("TF1");
	// asn.setTerminal(true);
	// asg.addProgressState(asn);
	//
	// return asg;
	// }
	//
	// public static ConcreteBeliefSet getPerceptionValues() {
	// ConcreteBeliefSet cbSet = new ConcreteBeliefSet(CROWD_BELIEF);
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	//
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CROWD,
	// ACTOR_CROWD, 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_CROWD,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_CROWD,
	// 0, 1, 0.1);
	// cbSet.add(cb);
	//
	// return cbSet;
	// }
	//
	// public static ActionBeliefImpactMatrix getPerceptionImpactMatrix(
	// ActionTypes actionSet, ConcreteBeliefSet socialValues,
	// Map<String, MassFunction<Character>> massFunctions) {
	//
	// ActionBeliefImpactMatrix am = new ActionBeliefImpactMatrix(
	// socialValues, actionSet);
	//
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	//
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CROWD,
	// ACTOR_CROWD, 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", new PerceptionImpactFunction(PIFType.GIFT_5,
	// massFunctions));
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(PIFType.GIFT_6,
	// massFunctions));
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(
	// PIFType.GIFT_14, massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(
	// PIFType.GIFT_15, massFunctions));
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", new PerceptionImpactFunction(
	// PIFType.GIFT_21, massFunctions));
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_CROWD,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", new PerceptionImpactFunction(PIFType.TRANS_3,
	// massFunctions));
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(PIFType.TRANS_6,
	// massFunctions));
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", new PerceptionImpactFunction(
	// PIFType.TRANS_13, massFunctions));
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(
	// PIFType.TRANS_14, massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(
	// PIFType.TRANS_15, massFunctions));
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", new PerceptionImpactFunction(
	// PIFType.TRANS_20, massFunctions));
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", new PerceptionImpactFunction(
	// PIFType.TRANS_22, massFunctions));
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_CROWD,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_6, massFunctions));
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_8, massFunctions));
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_11, massFunctions));
	// am.setImpact(cb, "A12", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_12, massFunctions));
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_14, massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_15, massFunctions));
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_17, massFunctions));
	// am.setImpact(cb, "A18", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_18, massFunctions));
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_21, massFunctions));
	// am.setImpact(cb, "A22", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_22, massFunctions));
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	// return am;
	// }
	//
	// public static ActionValueImpactMatrix getClientImpactMatrix(
	// ActionTypes actionSet, CSSMSet socialValues) {
	//
	// ActionValueImpactMatrix am = new ActionValueImpactMatrix(socialValues,
	// actionSet);
	//
	// CSSM cssm = new CSSM(SPANISH_STEPS, TIME, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 40, 0);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 5);
	// am.setImpact(cssm, "A3", 5);
	// am.setImpact(cssm, "A4", 5);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", 5);
	// am.setImpact(cssm, "A7", 5);
	// am.setImpact(cssm, "A8", 5);
	// am.setImpact(cssm, "A9", 5);
	// am.setImpact(cssm, "A10", 5);
	// am.setImpact(cssm, "A11", 5);
	// am.setImpact(cssm, "A12", 5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", new ClientImpactFunction(CIFType.T_14));
	// am.setImpact(cssm, "A15", 5);
	// am.setImpact(cssm, "A16", 5);
	// am.setImpact(cssm, "A17", 5);
	// am.setImpact(cssm, "A18", 5);
	// am.setImpact(cssm, "A19", 5);
	// am.setImpact(cssm, "A20", 5);
	// am.setImpact(cssm, "A21", 5);
	// am.setImpact(cssm, "A22", 5);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 5);
	//
	// cssm = new CSSM(SPANISH_STEPS, WORTH, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, -5, 5, 0);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", -5); // depends can be -1
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 0);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", 0);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", 0);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", 0);
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", -5);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 1);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 100, 50);
	// am.setImpact(cssm, "A1", 0.5);
	// am.setImpact(cssm, "A2", 10);
	// am.setImpact(cssm, "A3", 0); // depends can be -1
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", -5);
	// am.setImpact(cssm, "A7", 0);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 5);
	// am.setImpact(cssm, "A11", -10);
	// am.setImpact(cssm, "A12", -5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", new ClientImpactFunction(CIFType.D_15));
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.D_16));
	// am.setImpact(cssm, "A17", -5);
	// am.setImpact(cssm, "A18", 10);
	// am.setImpact(cssm, "A19", 10);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 0);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, ACTOR_CROWD,
	// ACTOR_CLIENT, 0, 100, 50);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 10);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 10);
	// am.setImpact(cssm, "A6", 10);
	// am.setImpact(cssm, "A7", -10);
	// am.setImpact(cssm, "A8", -5);
	// am.setImpact(cssm, "A9", 10);
	// am.setImpact(cssm, "A10", 10);
	// am.setImpact(cssm, "A11", -10);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", new ClientImpactFunction(CIFType.DP_15));
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.DP_16));
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", -5);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", -10);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, CLIENT_PEER,
	// ACTOR_CLIENT, 0, 100,
	// 50);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 10);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 25);
	// am.setImpact(cssm, "A6", 25);
	// am.setImpact(cssm, "A7", -10);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", -20);
	// am.setImpact(cssm, "A10", 5);
	// am.setImpact(cssm, "A11", -10);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", new ClientImpactFunction(CIFType.DR_15));
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.DR_16));
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 0);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 100, 50);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", -5);
	// am.setImpact(cssm, "A5", 0);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", new ClientImpactFunction(CIFType.P_16));
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", new ClientImpactFunction(CIFType.P_16));
	// am.setImpact(cssm, "A10", 10);
	// am.setImpact(cssm, "A11", -40);
	// am.setImpact(cssm, "A12", -5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.P_16));
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 0);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, CLIENT_PEER,
	// ACTOR_CLIENT, 0,
	// 100, 50);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", -5);
	// am.setImpact(cssm, "A5", 0);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", -10);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", -20);
	// am.setImpact(cssm, "A10", 10);
	// am.setImpact(cssm, "A11", -40);
	// am.setImpact(cssm, "A12", -5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.PR_16));
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 0);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, ACTOR_CROWD,
	// ACTOR_CLIENT, 0, 100, 50);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", -5);
	// am.setImpact(cssm, "A5", 0);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", -10);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", -10);
	// am.setImpact(cssm, "A10", 10);
	// am.setImpact(cssm, "A11", -40);
	// am.setImpact(cssm, "A12", -5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", new ClientImpactFunction(CIFType.PP_16));
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 0);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// return am;
	//
	// }
	//
	// public static ActionBeliefImpactMatrix getClientBeliefMatrix(
	// ActionTypes actionSet, ConcreteBeliefSet socialValues,
	// Map<String, MassFunction<Character>> massFunctions) {
	//
	// ActionBeliefImpactMatrix am = new ActionBeliefImpactMatrix(
	// socialValues, actionSet);
	//
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	//
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", new PerceptionImpactFunction(PIFType.GIFT_5,
	// massFunctions));
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(PIFType.GIFT_6,
	// massFunctions));
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", new PerceptionImpactFunction(PIFType.GIFT_12,
	// massFunctions));
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(PIFType.GIFT_14,
	// massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(PIFType.GIFT_15,
	// massFunctions));
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_CLIENT,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", new PerceptionImpactFunction(PIFType.TRANS_3,
	// massFunctions));
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(PIFType.TRANS_6,
	// massFunctions));
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", new PerceptionImpactFunction(PIFType.TRANS_13,
	// massFunctions));
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(PIFType.TRANS_14,
	// massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(PIFType.TRANS_15,
	// massFunctions));
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", new PerceptionImpactFunction(PIFType.TRANS_20,
	// massFunctions));
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", new PerceptionImpactFunction(PIFType.TRANS_22,
	// massFunctions));
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CLIENT,
	// ACTOR_CLIENT, 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_6, massFunctions));
	// am.setImpact(cb, "A7", new PerceptionImpactFunction(
	// PIFType.WORLD_PERCEPTION_7, massFunctions));
	// am.setImpact(cb, "A8", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_8, massFunctions));
	// am.setImpact(cb, "A9", new PerceptionImpactFunction(
	// PIFType.WORLD_PERCEPTION_9, massFunctions));
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_11, massFunctions));
	// am.setImpact(cb, "A12", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_12, massFunctions));
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_14, massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_15, massFunctions));
	// am.setImpact(cb, "A16", new PerceptionImpactFunction(
	// PIFType.WORLD_PERCEPTION_16, massFunctions));
	// am.setImpact(cb, "A17", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_17, massFunctions));
	// am.setImpact(cb, "A18", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_18, massFunctions));
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_21, massFunctions));
	// am.setImpact(cb, "A22", new PerceptionImpactFunction(
	// PIFType.DECEPTIVE_22, massFunctions));
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_CLIENT,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A2", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A3", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A4", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A5", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A6", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A7", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A8", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A9", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A10", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A11", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A12", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A13", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A14", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A15", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A16", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A17", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A18", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A19", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A20", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A21", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A22", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A23", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// am.setImpact(cb, "A24", new PerceptionImpactFunction(
	// PIFType.COM_WLD_PERCP, massFunctions));
	// // DONE
	// return am;
	//
	// }
	//
	// public static ActionValueImpactMatrix getSellerImpactMatrix(
	// ActionTypes actionSet, CSSMSet socialValues) {
	//
	// ActionValueImpactMatrix am = new ActionValueImpactMatrix(socialValues,
	// actionSet);
	//
	// CSSM cssm = new CSSM(SPANISH_STEPS, TIME, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 40, 0);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 5);
	// am.setImpact(cssm, "A3", 5);
	// am.setImpact(cssm, "A4", 5);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", 5);
	// am.setImpact(cssm, "A7", 5);
	// am.setImpact(cssm, "A8", 5);
	// am.setImpact(cssm, "A9", 5);
	// am.setImpact(cssm, "A10", 5);
	// am.setImpact(cssm, "A11", 5);
	// am.setImpact(cssm, "A12", 5);
	// am.setImpact(cssm, "A13", 5);
	// am.setImpact(cssm, "A14", new SellerImpactFunction(SIFType.T_14));
	// am.setImpact(cssm, "A15", 5);
	// am.setImpact(cssm, "A16", 5);
	// am.setImpact(cssm, "A17", 5);
	// am.setImpact(cssm, "A18", 5);
	// am.setImpact(cssm, "A19", 5);
	// am.setImpact(cssm, "A20", 5);
	// am.setImpact(cssm, "A21", 5);
	// am.setImpact(cssm, "A22", 5);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 5);
	//
	// cssm = new CSSM(SPANISH_STEPS, WORTH, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, -5, 5, 0);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0); // fixme
	// am.setImpact(cssm, "A3", 5);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 0);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", 0);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", 0);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", 0);
	// am.setImpact(cssm, "A17", 0);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 5);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", -0.1);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 100, 50);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", -1);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", -5);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", 0);
	// am.setImpact(cssm, "A15", -1);
	// am.setImpact(cssm, "A16", new SellerImpactFunction(SIFType.D_16));
	// am.setImpact(cssm, "A17", -5);
	// am.setImpact(cssm, "A18", 0);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", -5);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 100, 50);
	// am.setImpact(cssm, "A1", 0);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", 0);
	// am.setImpact(cssm, "A7", new SellerImpactFunction(SIFType.DP_7));
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", new SellerImpactFunction(SIFType.DP_9));
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", -10);
	// am.setImpact(cssm, "A12", new SellerImpactFunction(SIFType.DP_12));
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", new SellerImpactFunction(SIFType.DP_14));
	// am.setImpact(cssm, "A15", 0);
	// am.setImpact(cssm, "A16", new SellerImpactFunction(SIFType.DP_16));
	// am.setImpact(cssm, "A17", new SellerImpactFunction(SIFType.DP_17));
	// am.setImpact(cssm, "A18", -10);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", -10);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_SELLER, ACTOR_SELLER,
	// ACTOR_SELLER, 0, 100, 50);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 5);
	// am.setImpact(cssm, "A6", 5);
	// am.setImpact(cssm, "A7", 0);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", 0);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", -1);
	// am.setImpact(cssm, "A15", -1);
	// am.setImpact(cssm, "A16", 0);
	// am.setImpact(cssm, "A17", -5);
	// am.setImpact(cssm, "A18", 5);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 10);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// cssm = new CSSM(SPANISH_STEPS, POLITENESS, ACTOR_SELLER, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 100, 50);
	// am.setImpact(cssm, "A1", 5);
	// am.setImpact(cssm, "A2", 0);
	// am.setImpact(cssm, "A3", 0);
	// am.setImpact(cssm, "A4", 0);
	// am.setImpact(cssm, "A5", 15);
	// am.setImpact(cssm, "A6", 5);
	// am.setImpact(cssm, "A7", 0);
	// am.setImpact(cssm, "A8", 0);
	// am.setImpact(cssm, "A9", 0);
	// am.setImpact(cssm, "A10", 0);
	// am.setImpact(cssm, "A11", 0);
	// am.setImpact(cssm, "A12", 0);
	// am.setImpact(cssm, "A13", 0);
	// am.setImpact(cssm, "A14", -1);
	// am.setImpact(cssm, "A15", -1);
	// am.setImpact(cssm, "A16", 0);
	// am.setImpact(cssm, "A17", -5);
	// am.setImpact(cssm, "A18", 5);
	// am.setImpact(cssm, "A19", 0);
	// am.setImpact(cssm, "A20", 0);
	// am.setImpact(cssm, "A21", 0);
	// am.setImpact(cssm, "A22", 10);
	// am.setImpact(cssm, "A23", 0);
	// am.setImpact(cssm, "A24", 0);
	//
	// return am;
	//
	// }
	//
	// public static ActionBeliefImpactMatrix getSellerBeliefMatrix(
	// ActionTypes actionSet, ConcreteBeliefSet socialValues,
	// Map<String, MassFunction<Character>> massFunctions) {
	//
	// ActionBeliefImpactMatrix am = new ActionBeliefImpactMatrix(
	// socialValues, actionSet);
	//
	// Culture culture = new Culture(SPANISH_STEPS, LANGUAGE_SPANISH);
	// ConcreteBelief cb = new ConcreteBelief(culture, IS_A_GIFT, ACTOR_CLIENT,
	// ACTOR_SELLER, 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", 0);
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", 0);
	// am.setImpact(cb, "A15", 0);
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_SELLER,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", 0);
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", 0);
	// am.setImpact(cb, "A15", 0);
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CLIENT,
	// ACTOR_SELLER, 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", 0);
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", 0);
	// am.setImpact(cb, "A15", 0);
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// cb = new ConcreteBelief(culture, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_SELLER,
	// 0, 1, 0.1);
	// am.setImpact(cb, "A1", 0);
	// am.setImpact(cb, "A2", 0);
	// am.setImpact(cb, "A3", 0);
	// am.setImpact(cb, "A4", 0);
	// am.setImpact(cb, "A5", 0);
	// am.setImpact(cb, "A6", 0);
	// am.setImpact(cb, "A7", 0);
	// am.setImpact(cb, "A8", 0);
	// am.setImpact(cb, "A9", 0);
	// am.setImpact(cb, "A10", 0);
	// am.setImpact(cb, "A11", 0);
	// am.setImpact(cb, "A12", 0);
	// am.setImpact(cb, "A13", 0);
	// am.setImpact(cb, "A14", 0);
	// am.setImpact(cb, "A15", 0);
	// am.setImpact(cb, "A16", 0);
	// am.setImpact(cb, "A17", 0);
	// am.setImpact(cb, "A18", 0);
	// am.setImpact(cb, "A19", 0);
	// am.setImpact(cb, "A20", 0);
	// am.setImpact(cb, "A21", 0);
	// am.setImpact(cb, "A22", 0);
	// am.setImpact(cb, "A23", 0);
	// am.setImpact(cb, "A24", 0);
	//
	// return am;
	//
	// }

}
