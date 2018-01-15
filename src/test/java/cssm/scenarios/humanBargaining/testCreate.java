package cssm.scenarios.humanBargaining;

import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.ACTIONTYPE_ACCEPT;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.ACTIONTYPE_OFFER;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.ACTIONTYPE_WITHDRAW;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.ACTOR_BUYER;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.ACTOR_SELLER;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.PROGRESS_STATE_OA;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.PROGRESS_STATE_OB;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.PROGRESS_STATE_TN;
import static yaes.cssm.scenarios.humanBargaining.sbHumanBargaining.PROGRESS_STATE_TP;

import org.junit.Test;

import yaes.cssm.actions.ActionType;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.humanBargaining.sbHumanBargaining;
import yaes.ui.text.TextUi;

public class testCreate {

	/**
	 * Simple test for the creation of a scenario, in this case Human Bargaining
	 */
	@Test
	public void test() {

		// create the action types
		ActionTypes actionTypes = new ActionTypes();
		ActionType action = new ActionType(sbHumanBargaining.ACTIONTYPE_OFFER,
				"Agent make offer",
				sbHumanBargaining.ACTIONTYPE_PARAMETER_PRICE);
		actionTypes.addActionType(action, false);
		action = new ActionType(sbHumanBargaining.ACTIONTYPE_ACCEPT,
				"Agent accept offer");
		actionTypes.addActionType(action, true);
		action = new ActionType(sbHumanBargaining.ACTIONTYPE_WITHDRAW,
				"Agent withdraw offer");
		actionTypes.addActionType(action, true);

		Scenario socialScenario = new Scenario("Human Bargaining", "one",
				actionTypes);
		// create the actors
		socialScenario.createActor(sbHumanBargaining.ACTOR_SELLER);
		socialScenario.createActor(sbHumanBargaining.ACTOR_BUYER);
		// TextUi.println(scen.toStringDetailed(ToStringDetailed.DTL_HUMAN_ONLY));
		// create the progress graph
		socialScenario.createProgressGraph();
		ProgressGraph pg = socialScenario.getProgressGraph();
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_OFFER, ACTOR_SELLER,
				PROGRESS_STATE_OB);
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_ACCEPT, ACTOR_SELLER,
				PROGRESS_STATE_TP);
		pg.add(PROGRESS_STATE_OA, ACTIONTYPE_WITHDRAW, ACTOR_SELLER,
				PROGRESS_STATE_TN);

		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_OFFER, ACTOR_BUYER,
				PROGRESS_STATE_OA);
		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_ACCEPT, ACTOR_BUYER,
				PROGRESS_STATE_TP);
		pg.add(PROGRESS_STATE_OB, ACTIONTYPE_WITHDRAW, ACTOR_BUYER,
				PROGRESS_STATE_TN);


		TextUi.print(pg.toStringDetailed(0));
		// now, assign some actual agents to play the role of the actors
		SocialAgent agentBill = new SocialAgent("Bill");
		socialScenario.setActorPlayedBy("seller", agentBill);
		SocialAgent agentJill = new SocialAgent("Jill");
		socialScenario.setActorPlayedBy("buyer", agentJill);

		TextUi.print(pg.toStringDetailed(0));

	}

}
