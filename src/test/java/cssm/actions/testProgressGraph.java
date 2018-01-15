package cssm.actions;


import org.junit.Test;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;

/**
 * Progress graph class test
 * 
 * @author Taranjeet
 * 
 */
public class testProgressGraph {

	/**
	 * Creates a simple progress graph for the human bargaining scenario
	 * 
	 * @return
	 */
	public static ProgressGraph createProgressGraph() {
		// create the action types
		ActionTypes actionTypes = new ActionTypes();
		ActionType actionType = new ActionType("offer", "make an offer");
		actionTypes.addActionType(actionType, false);
		actionType = new ActionType("accept", "accept the last offer");
		actionTypes.addActionType(actionType, true);
		actionType = new ActionType("withdraw", "withdraw from the negotiation");
		actionTypes.addActionType(actionType, true);
		Scenario scen = new Scenario("Human Bargaining", "Instance1", actionTypes);
		// create the actors
		scen.createActor("seller");
		scen.createActor("buyer");		
		// create the progress graph
		scen.createProgressGraph();
		ProgressGraph pg = scen.getProgressGraph();
		pg.add("OA", "offer", "seller", "OB");
		pg.add("OA", "accept", "seller", "TP");
		pg.add("OA", "withdraw", "seller", "TN");

		pg.add("OB", "offer", "buyer", "OA");
		pg.add("OB", "accept", "seller", "TP");
		pg.add("OB", "withdraw", "seller", "TN");

		return pg;
	}

	@Test
	public void testSimple() {
		ProgressGraph asg = createProgressGraph();
		TextUi.println(asg.toStringDetailed(0));
	}

}
