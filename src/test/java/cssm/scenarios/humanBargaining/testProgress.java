package cssm.scenarios.humanBargaining;

import org.junit.Test;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.humanBargaining.sbHumanBargaining;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

public class testProgress implements IDetailLevel {

	/**
	 * Test for how the progress of the human bargaining works case of a single
	 * scenario
	 */
	@Test
	public void testSingle() {
		ScenarioSet scenarioSet = new ScenarioSet();
		// create the first scenario
		Scenario scenario = sbHumanBargaining.createScenario("one");
		scenario.setCurrentProgressState(sbHumanBargaining.PROGRESS_STATE_OA);
		scenarioSet.addScenario(scenario);
		// create the agents
		SocialAgent agentBill = new SocialAgent("Bill");
		SocialAgent agentJill = new SocialAgent("Jill");
		// now, assign agents to play the role of the actors
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_SELLER, agentBill);
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_BUYER, agentJill);
		// now perform some actions
		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_SELLER, 100.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_BUYER, 50.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_SELLER, 60.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_ACCEPT,
				sbHumanBargaining.ACTOR_BUYER);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

	}

	@Test
	public void testCSSM() {
		ScenarioSet scenarioSet = new ScenarioSet();
		// create the first scenario
		Scenario scenario = sbHumanBargaining.createScenario("one");
		scenario.setCurrentProgressState(sbHumanBargaining.PROGRESS_STATE_OA);
		scenarioSet.addScenario(scenario);
		// create the agents
		SocialAgent agentBill = new SocialAgent("Bill");
		SocialAgent agentJill = new SocialAgent("Jill");
		// now, assign agents to play the role of the actors
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_SELLER, agentBill);
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_BUYER, agentJill);
		// now perform some actions
		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_SELLER, 100.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_BUYER, 50.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_OFFER,
				sbHumanBargaining.ACTOR_SELLER, 60.0);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

		scenarioSet.performConcreteAction("one",
				sbHumanBargaining.ACTIONTYPE_ACCEPT,
				sbHumanBargaining.ACTOR_BUYER);
		TextUi.println(scenario.toStringDetailed(MAX_DETAIL));

	}

}
