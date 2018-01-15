package yaes.cssm.util.webgui.prettyhtml;

import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;
import yaes.ui.format.Formatter;
import yaes.ui.format.HtmlFormatter;
import yaes.ui.format.IDetailLevel;

public class PwSocialAgent {

	/**
	 * Generate the webpage for a social actor potentially playing in a number
	 * of scenarios
	 * 
	 * @param fmt
	 * @param scc
	 * @param query
	 */
	public static void generate(HtmlFormatter fmt, SocialCalculusContext scc,
			GsQuery query) {
		String id = query.getId();
		SocialAgent socialAgent = scc.agents.get(id);
		PwHelper.is(fmt, "Name:", socialAgent.getName());
		//
		// add the roles it is playing in the various scenarios
		//
		PwHelper.addBold(fmt, "Playing roles:");
		PwHelper.indent(fmt);
		for (String scenarioInstance : scc.getScenarioSet().getScenarios()
				.keySet()) {
			Scenario scenario = scc.getScenarioSet().findScenario(
					scenarioInstance);
			for (String actorName : scenario.getActors()) {
				SocialAgent playedBy = scenario.getActorPlayedBy(actorName);
				if (playedBy.equals(socialAgent)) {
					fmt.addP("in scenario " + scenario.getScenarioInstance()
							+ "/" + scenario.getScenarioType() + " playing "
							+ actorName);
				}
			}
		}
		PwHelper.deindent(fmt);
		//
		// list the CSSMs in its private state
		//
		PwHelper.addBold(fmt, "CSSMs for which the agent is the estimator");
		PwHelper.indent(fmt);
		for (CSSM cssm : socialAgent.getCSSMs()) {
			String cssmName = cssm.toStringDetailed(IDetailLevel.DTL_NAME_ONLY);
			double cssmValue = socialAgent.getCSSMValue(cssm).getValue();
			PwHelper.is(fmt, cssmName, Formatter.fmt(cssmValue));
		}
		PwHelper.deindent(fmt);
		//
		// list the CBs in its private state
		//
		PwHelper.addBold(fmt, "CBs for which the agent is the estimator");
		PwHelper.indent(fmt);
		for (ConcreteBelief cb : socialAgent.getCBs()) {
			String cbName = cb.toStringDetailed(IDetailLevel.DTL_NAME_ONLY);
			ConcreteBeliefValue cbValue = socialAgent.getCBValue(cb);
			PwHelper.is(fmt, cbName, Formatter.fmt(cbValue.getValue())
					+ " " + cbValue.getCbDempsterShafer().toStringDetailed(IDetailLevel.MIN_DETAIL));
		}
		PwHelper.deindent(fmt);
		//
		// list the weights in its private state
		//
		PwHelper.addBold(fmt, "Weights for featues");
		PwHelper.indent(fmt);
		for (CSSM cssm : socialAgent.getCSSMs()) {
			String cssmName = cssm.toStringDetailed(IDetailLevel.DTL_NAME_ONLY);
			double weights = socialAgent.getWeight(cssm);
			PwHelper.is(fmt, cssmName, Formatter.fmt(weights));
		}
		PwHelper.deindent(fmt);
		

	}
}
