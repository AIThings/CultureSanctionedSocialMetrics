package yaes.cssm.scenarios.humanBargaining;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.cssm.Scenario;

/**
 * Scenario Builder functions for Human Bargaining
 * 
 * @author Taranjeet
 * 
 */
public class sbHumanBargaining {

	
	public static final String PROGRESS_STATE_OA = "OA";
	public static final String PROGRESS_STATE_TN = "TN";
	public static final String PROGRESS_STATE_TP = "TP";
	public static final String PROGRESS_STATE_OB = "OB";
	public static final String ACTIONTYPE_WITHDRAW = "withdraw";
	public static final String ACTIONTYPE_ACCEPT = "accept";
	public static final String ACTIONTYPE_OFFER = "offer";
	public static final String ACTOR_BUYER = "buyer";
	public static final String ACTOR_SELLER = "seller";
	public static final String ACTIONTYPE_PARAMETER_PRICE = "price";

	/**
	 * Creates the actors, action types and progress graph
	 * of a scenario
	 * 
	 * TODO: extract the names into constants
	 * 
	 * @return
	 */
	public static Scenario createScenario(String instanceName) {
		
		// create the action types
		ActionTypes actionSet = new ActionTypes();
		ActionType action = new ActionType(ACTIONTYPE_OFFER, "Agent make offer", ACTIONTYPE_PARAMETER_PRICE);
		actionSet.addActionType(action, false);
		action = new ActionType(ACTIONTYPE_ACCEPT, "Agent accept offer");
		actionSet.addActionType(action, true);
		action = new ActionType(ACTIONTYPE_WITHDRAW, "Agent withdraw offer");
		actionSet.addActionType(action, true);
		// create the social scenario
		Scenario socialScenario = new Scenario("Human Bargaining", instanceName, actionSet);
		// create the actors
		socialScenario.createActor(ACTOR_SELLER);
		socialScenario.createActor(ACTOR_BUYER);
		// TextUi.println(scen.toStringDetailed(ToStringDetailed.DTL_HUMAN_ONLY));
		// create the progress graph
		socialScenario.createProgressGraph();
		ProgressGraph pg = socialScenario.getProgressGraph();
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_OFFER, ACTOR_SELLER, PROGRESS_STATE_OB);
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_ACCEPT, ACTOR_SELLER, PROGRESS_STATE_TP);
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_WITHDRAW, ACTOR_SELLER, PROGRESS_STATE_TN);

		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_OFFER, ACTOR_BUYER, PROGRESS_STATE_OA);
		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_ACCEPT, ACTOR_BUYER, PROGRESS_STATE_TP);
		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_WITHDRAW, ACTOR_BUYER, PROGRESS_STATE_TN);
		
		
		socialScenario.setCurrentProgressState(PROGRESS_STATE_OA);
	    //TextUi.print(pg.toStringDetailed(0));
		
		return socialScenario;
	}

}
