package yaes.cssm.scenarios.spanishsteps;

import static yaes.cssm.scenarios.spanishsteps.Actors.CLIENT;
import static yaes.cssm.scenarios.spanishsteps.Actors.CROWD;
import static yaes.cssm.scenarios.spanishsteps.Actors.SELLER;
import static yaes.cssm.scenarios.spanishsteps.Actors.SPOUSE;

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

/**
 * Helper class for defining ACTORs CSSM and Concrete Belief, Progress State
 * Graph
 * 
 * @author Taranjeet
 * 
 */
public class DefineScenario implements Constants {

	/**
	 * Calling this function we will create all the features of the scenario in
	 * the specific object
	 * 
	 * @param scenario
	 */
	public static Scenario initializeScenario(String scenarioInstance,
			SocialAgent agentSeller, SocialAgent agentClient,
			SocialAgent agentClientPeer, SocialAgent agentCrowd) {
		Scenario scenario = new Scenario(CULTURE_WESTERN, scenarioInstance,
				getActionTypes());
		// Initialize ProgressGraph
		initializeProgressGraph(scenario);

		scenario.setCurrentProgressState("S0");

		scenario.setActorPlayedBy(SELLER, agentSeller);
		scenario.setActorPlayedBy(CLIENT, agentClient);
		scenario.setActorPlayedBy(SPOUSE, agentClientPeer);
		scenario.setActorPlayedBy(CROWD, agentCrowd);

		// ACTOR_SELLER
		Map<CSSM, CSSMValue> sellerCssms = getSellerCssms(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> sellerCBs = initializeCbSeller(scenario);
		agentSeller.initializeValues(sellerCssms, sellerCBs);
		//
		// ACTOR_CLIENT
		Map<CSSM, CSSMValue> clientCssms = getClientCssms(scenario);
		Map<ConcreteBelief, ConcreteBeliefValue> clientCBs = initializeCbClient(scenario);
		agentClient.initializeValues(clientCssms, clientCBs);
		return scenario;

	}

	/**
	 * Initializes the progress graph of the Spanish Steps scenario
	 * 
	 * @return
	 */
	public static void initializeProgressGraph(Scenario scenario) {

		scenario.createProgressGraph();
		ProgressGraph pg = scenario.getProgressGraph();
		//
		// the actions of the seller actor
		//
		pg.add("S0", "alpha-1", SELLER, "S1"); // offers flowers
		pg.add("S3", "alpha-5", SELLER, "S4"); // offers gift
		pg.add("S3", "alpha-6", SELLER, "S5"); // forces gift
		pg.add("S3", "alpha-15", SELLER, "S12"); // gives up
		pg.add("S6", "alpha-12", SELLER, "S12"); // accepts return
		pg.add("S6", "alpha-11", SELLER, "S5"); // declines return
		pg.add("S7", "alpha-13", SELLER, "S7"); // waits
		pg.add("S7", "alpha-14", SELLER, "S8"); // requests payment
		pg.add("S7", "alpha-16", SELLER, "S13"); // concedes gift
		pg.add("S9", "alpha-11", SELLER, "S8"); // declines return
		pg.add("S9", "alpha-12", SELLER, "S14"); // accepts return
		//
		// the actions of the client actor
		//
		pg.add("S1", "alpha-2", CLIENT, "S2"); // accepts buying
		pg.add("S1", "alpha-4", CLIENT, "S3"); // declines buying
		pg.add("S2", "alpha-3", CLIENT, "S11"); // pays
		pg.add("S4", "alpha-8", CLIENT, "S3"); // declines gift
		pg.add("S4", "alpha-7", CLIENT, "S7"); // accepts gift
		pg.add("S5", "alpha-10", CLIENT, "S6"); // attempts return
		pg.add("S5", "alpha-7", CLIENT, "S7"); // accepts gift
		pg.add("S5", "alpha-9", CLIENT, "S12"); // throws flower
		pg.add("S8", "alpha-10", CLIENT, "S9"); // attempts return
		pg.add("S8", "alpha-2", CLIENT, "S10"); // accepts buying
		pg.add("S10", "alpha-3", CLIENT, "S15"); // pays
		//
		// terminal actions (made profit or not)
		//
		pg.add("S11", "alpha-17", SELLER, "TP1");
		pg.add("S12", "alpha-18", SELLER, "TN1");
		pg.add("S13", "alpha-19", SELLER, "TF1");
		pg.add("S14", "alpha-18", SELLER, "TN2");
		pg.add("S15", "alpha-17", SELLER, "TP2");
	}

	/**
	 * Generate the list of CSSMs for the agent which is acting as ACTOR_SELLER
	 * 
	 * @return
	 */
	public static Map<CSSM, CSSMValue> getSellerCssms(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME, SELLER,
				SELLER, SELLER);

		CSSMValue cv = new CSSMValue(0, 200, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, SELLER,
				SELLER, SELLER);
		cv = new CSSMValue(-5, 5, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, SELLER,
				SELLER, SELLER);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, SELLER,
				CROWD, SELLER);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				CROWD, SELLER);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		return retval;
	}

	/**
	 * Initializes the CBs of the Seller
	 * 
	 * The relevant one is:
	 * 
	 * CB(scenario,Q-Agreed,Crowd,Seller)
	 * 
	 * but that is equal to CB(scenario,Q-Agreed,Crowd,Client) due to the
	 * intra-cultural uniformity assumption, so there is no reason tracking it
	 * here.
	 * 
	 * We might consider tracking
	 * 
	 * CB(scenario,Q-Gift,Client,Seller) later.
	 * 
	 * So currrently, this returns an empty set.
	 * 
	 * @param scenario
	 * @return
	 */
	public static Map<ConcreteBelief, ConcreteBeliefValue> initializeCbSeller(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		return retval;

	}

	/**
	 * Returns a list of the CSSMs and initial values of the ACTOR_CLIENT.
	 * 
	 * It performs a mapping of the actor names to the specific names of the
	 * actors in the given scenario
	 * 
	 * @return
	 */
	public static Map<CSSM, CSSMValue> getClientCssms(Scenario scenario) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, TIME, CLIENT,
				CLIENT, CLIENT);
		CSSMValue cv = new CSSMValue(0, 200, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, CLIENT,
				CLIENT, CLIENT);
		cv = new CSSMValue(-5, 5, 0);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, CLIENT,
				CLIENT, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, CLIENT,
				CROWD, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, CLIENT,
				SPOUSE, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CROWD, CLIENT);
		cv = new CSSMValue(0, 1.0, 0.5);
		retval.put(cssm, cv);

		/**
		 * perception parameter of the client need to be defined which was
		 * previously defined here. see spanishstepHelper for details.
		 */

		return retval;
	}

	/**
	 * Initialize the CBs of the client:
	 * 
	 * CB(scenario,Q-Gift,Client,Client) 
	 * 
	 * CB(scenario,Q-Agreed,Crowd,Client)
	 * 
	 * @param scenario
	 * @return
	 */
	public static Map<ConcreteBelief, ConcreteBeliefValue> initializeCbClient(
			Scenario scenario) {
		Map<ConcreteBelief, ConcreteBeliefValue> retval = new HashMap<>();
		ConcreteBelief cb = ConcreteBelief.createCB(scenario, Q_GIFT, CLIENT,
				CLIENT);
		CbDempsterShafer initDS = new CbDempsterShafer(0.0, 0.0);
		ConcreteBeliefValue cbv = new ConcreteBeliefValue(initDS);
		retval.put(cb, cbv);
		cb = ConcreteBelief.createCB(scenario, Q_AGREED, CROWD, CLIENT);
		initDS = new CbDempsterShafer(0.0, 0.0);
		cbv = new ConcreteBeliefValue(initDS);
		retval.put(cb, cbv);
		return retval;
	}

	/**
	 * Returns the action types specification
	 * 
	 * @return
	 */
	public static ActionTypes getActionTypes() {
		ActionTypes actionSet = new ActionTypes();
		// alpha-1: seller offers flowers to sell
		ActionType spec = new ActionType("alpha-1",
				"seller offers flowers to sell");
		actionSet.addActionType(spec, false);
		// alpha-2: client accept buying
		spec = new ActionType("alpha-2", "client accepts buying");
		actionSet.addActionType(spec, false);
		// alpha-3: client pays for flowers
		spec = new ActionType("alpha-3", "client pays for flowers");
		actionSet.addActionType(spec, false);
		// alpha-4: client declines buying flowers
		spec = new ActionType("alpha-4", "client declines buying flowers");
		actionSet.addActionType(spec, false);
		// alpha-5: seller offers flower as gift
		spec = new ActionType("alpha-5", "seller offers flower as gift");
		actionSet.addActionType(spec, false);
		// alpha-6: seller forces gift
		spec = new ActionType("alpha-6", "seller forces gift");
		actionSet.addActionType(spec, false);
		// alpha-7: client accepts flower as gift
		spec = new ActionType("alpha-7", "client accepts flower as gift");
		actionSet.addActionType(spec, false);
		// alpha-8: client declines flower (loadness, offensiveness)
		spec = new ActionType("alpha-8",
				"client declines flower (loudness, offensiveness)", "loudness",
				"offensiveness");
		actionSet.addActionType(spec, false);
		// alpha-9: client throws gift to the ground
		spec = new ActionType("alpha-9", "client throws gift to the ground");
		actionSet.addActionType(spec, false);
		// alpha-10: client attempts return of flower
		spec = new ActionType("alpha-10", "client attempts return of flower",
				"loudness", "offensiveness");
		actionSet.addActionType(spec, false);
		// alpha-11: seller declines return of flower
		spec = new ActionType("alpha-11", "seller declines return of flower");
		actionSet.addActionType(spec, false);
		// alpha-12: seller accepts return of flower
		spec = new ActionType("alpha-12", "seller accepts return of flower");
		actionSet.addActionType(spec, false);
		// alpha-13: seller waits
		spec = new ActionType("alpha-13", "seller waits(time)", "time");
		actionSet.addActionType(spec, false);
		// alpha-14: seller requests payment
		spec = new ActionType("alpha-14", "seller requests payment");
		actionSet.addActionType(spec, false);
		// alpha-15: seller gives up
		spec = new ActionType("alpha-15", "seller gives up");
		actionSet.addActionType(spec, false);
		// alpha-16: seller concedes gift
		spec = new ActionType("alpha-16", "seller concedes gift");
		actionSet.addActionType(spec, false);
		// alpha-17: seller made profit
		spec = new ActionType("alpha-17", "seller made profit");
		actionSet.addActionType(spec, true);
		// alpha-18: seller made no profit but wasted time
		spec = new ActionType("alpha-18", "seller made no profit");
		actionSet.addActionType(spec, true);
		// alpha-19: seller made loss
		spec = new ActionType("alpha-19", "seller made loss");
		actionSet.addActionType(spec, true);

		return actionSet;
	}
}
