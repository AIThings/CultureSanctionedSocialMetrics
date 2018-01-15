package yaes.cssm.util.webgui.prettyhtml;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;
import yaes.ui.format.HtmlFormatter;

public class PwScenarioSet {

	public static void generate(HtmlFormatter fmt, SocialCalculusContext scc,
			GsQuery query) {
		ScenarioSet scenarioSet = scc.getScenarioSet();
		fmt.addH1("ScenarioSet");
		fmt.addH2("List of scenarios:");
		for(String scenarioInstance: scenarioSet.getScenarios().keySet()) {
			Scenario scenario = scenarioSet.findScenario(scenarioInstance);
			PwHelper.addBold(fmt, scenarioInstance + " / " + scenario.getScenarioType());
			PwLinks.linkToScenario(fmt, scc, query, scenario);
		}
	}

}
