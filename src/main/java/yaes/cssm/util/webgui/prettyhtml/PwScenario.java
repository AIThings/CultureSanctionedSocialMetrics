package yaes.cssm.util.webgui.prettyhtml;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;
import yaes.ui.format.HtmlFormatter;
import yaes.ui.format.IDetailLevel;

public class PwScenario {

	/**
	 * Concise label for the scenario - used in the link
	 * 
	 * @param scenario
	 * @param scc
	 * @return
	 */
	public static String pwConcise(Scenario scenario, SocialCalculusContext scc) {
		return "Scenario:" + scenario.getScenarioInstance() + " / "
				+ scenario.getScenarioType();
	}

	/**
	 * Generate a page for the scenario type query. Looks up the scenario.
	 * 
	 * @param fmt
	 * @param scc
	 * @param query
	 */
	public static void generate(HtmlFormatter fmt, SocialCalculusContext scc,
			GsQuery query) {
		ScenarioSet scenarioSet = scc.getScenarioSet();
		String key = query.getId();
		Scenario scenario = scenarioSet.findScenario(key);
		if (scenario == null) {
			PwHelper.addErrorMessage(fmt,
					"Could not find the scenario instance with label: " + key);
			return;
		}
		pwDetailed(fmt, scenario, scc, query);
	}

	/**
	 * 
	 * @param fmt
	 * @param scenario
	 * @param scc
	 */
	public static void pwDetailed(HtmlFormatter fmt, Scenario scenario,
			SocialCalculusContext scc, GsQuery query) {
		PwHelper.is(fmt, "Scenario type", scenario.getScenarioType());
		PwHelper.is(fmt, "Scenario instance", scenario.getScenarioInstance());
		PwHelper.is(fmt, "Current progress state",
				scenario.getCurrentProgressState());
		PwHelper.addBold(fmt, "Actors and social agents who play them.");
		PwHelper.indent(fmt);
		for (String actorName : scenario.getActors()) {
			SocialAgent socialAgent = scenario.getActorPlayedBy(actorName);
			fmt.openP();
			PwLinks.linkToActor(fmt, scc, query, scenario, actorName);
			PwHelper.addArrow(fmt);
			PwLinks.linkToSocialAgent(fmt, scc, query, socialAgent);
			fmt.closeP();
		}

		PwHelper.deindent(fmt);
		fmt.addPre(scenario.toStringDetailed(IDetailLevel.MAX_DETAIL));
	}

}
