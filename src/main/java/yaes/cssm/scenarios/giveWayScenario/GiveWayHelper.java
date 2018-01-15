package yaes.cssm.scenarios.giveWayScenario;

import java.util.HashMap;
import java.util.Map;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.actions.ProgressState;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.CSSMValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.spanishsteps.Actors;

public class GiveWayHelper implements Constants {

	

	public static Scenario initScenario(String scenarioInstance,
			SocialAgent agentAmerican, SocialAgent agentIndian,
			SocialAgent agentCrowd) {

		Scenario scenario = new Scenario(CULTURE_MIXED, scenarioInstance,
				getActions());

		// Initialize ProgressGraph
		initializeProgressGraph(scenario);

		scenario.setCurrentProgressState("SS");

		scenario.setActorPlayedBy(ACTOR_AMERICAN, agentAmerican);
		scenario.setActorPlayedBy(ACTOR_INDIAN, agentIndian);
		scenario.setActorPlayedBy(Actors.CROWD, agentCrowd);

		// initialize the CSSMs and CBs of the agents
		Map<CSSM, CSSMValue> americanCSSM = getCSSMs(scenario, ACTOR_AMERICAN);
		agentAmerican.initializeValues(americanCSSM, null);

		Map<CSSM, CSSMValue> indianCSSM = getCSSMs(scenario, ACTOR_INDIAN);
		agentIndian.initializeValues(indianCSSM, null);

		return scenario;
	}

	private static Map<CSSM, CSSMValue> getCSSMs(Scenario scenario, String actor) {
		Map<CSSM, CSSMValue> retval = new HashMap<>();

		
			CSSM cssm = CSSM.createCSSM(scenario, CULTURE_MIXED, TIME,
					actor, actor, actor);
			CSSMValue cv = new CSSMValue(0, 100, 0);
			retval.put(cssm, cv);
			
			
			cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, POLITENESS,
					actor, actor, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);
			
			cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, POLITENESS,
					actor, Actors.CROWD, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);

			cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, WIMPINESS,
					actor, actor, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);
			
			cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, WIMPINESS,
					actor, Actors.CROWD, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);
			
			cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, POLITENESS,
					actor, actor, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);
			
			cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, POLITENESS,
					actor, Actors.CROWD, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);

			cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, WIMPINESS,
					actor, actor, actor);
			cv = new CSSMValue(0, 100, 50);
			retval.put(cssm, cv);
			
			cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, WIMPINESS,
					actor, Actors.CROWD, actor);
			cv = new CSSMValue(0, 100, 50);
		retval.put(cssm, cv);
			

		return retval;
	}

	/**
	 * Initializes the progress graph of scenario
	 * 
	 * @param scenario
	 */
	private static void initializeProgressGraph(Scenario scenario) {
		scenario.createProgressGraph();
		ProgressGraph pg = scenario.getProgressGraph();

		pg.add("SS", "a1", ACTOR_AMERICAN, "OA");
		pg.add("SS", "a4", ACTOR_INDIAN, "OB");

		pg.add("OA", "a3", ACTOR_AMERICAN, "OB");
		pg.add("OB", "a3", ACTOR_INDIAN, "OA");

		pg.add("OA", "a2", ACTOR_AMERICAN, "TN");
		pg.add("OB", "a2", ACTOR_INDIAN, "TN");

		ProgressState asn = new ProgressState("TN");
		pg.addProgressState(asn);

	}

	private static ActionTypes getActions() {

		ActionTypes actionSet = new ActionTypes();

		ActionType spec = new ActionType("a1", "Open Door by agent 1");
		actionSet.addActionType(spec, false);

		spec = new ActionType("a4", "Open Door by agent 2");
		actionSet.addActionType(spec, false);
				
		spec = new ActionType("a2", "Enter the place");
		actionSet.addActionType(spec, true);

		spec = new ActionType("a3", "Give Way");
		actionSet.addActionType(spec, false);

		return actionSet;
	}

}
