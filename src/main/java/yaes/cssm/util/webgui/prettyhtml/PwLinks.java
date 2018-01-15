package yaes.cssm.util.webgui.prettyhtml;

import java.util.AbstractMap.SimpleEntry;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;
import yaes.cssm.util.webgui.httpserver.QueryType;
import yaes.ui.format.HtmlFormatter;

public class PwLinks {

    /**
     * Adds a web link to a specific query. Should be widely used
     * 
     * @param fmt
     * @param gq
     * @param label
     */
    public static String addLinkToQuery(HtmlFormatter fmt, GsQuery gq,
            String label, String theClass, String... params) {
        String p[] = new String[2 + params.length];
        p[0] = "href=" + gq.toQuery();
        p[1] = "class=" + theClass;
        for (int i = 0; i != params.length; i++) {
            p[2 + i] = params[i];
        }
        // fmt.openA("href=" + gq.toQuery(), "class=" + theClass, params);
        fmt.openA(p);
        fmt.add(label);
        fmt.closeA();
        return fmt.toString();
    }

    /**
     * Navigational line for moving the cursor
     * 
     * @return
     */
    public static String cursorLinks(HtmlFormatter fmt, GsQuery gq) {
        SimpleEntry<GsQuery, GsQuery> entry = GsQuery.createCursorNeighbors(gq);
        GsQuery gqPrevious = entry.getKey();
        GsQuery gqNext = entry.getValue();
        if (gqPrevious != null) {
            PwLinks.addLinkToQuery(fmt, gqPrevious, " << ",
                    PwHelper.CLASS_BODYLINK);
        }
        fmt.add("[" + (gq.getCursorFrom() + 1) + " - " + gq.getCursorTo()
                + " ] of " + gq.getCursorTotal());
        if (gqNext != null) {
            PwLinks.addLinkToQuery(fmt, gqNext, " >> ", PwHelper.CLASS_BODYLINK);
        }
        return fmt.toString();
    }
    

    /**
     * Adds a link to a scenario
     * 
     * @param fmt
     * @param agent
     * @param query
     * @param choice
     */
    public static String linkToScenario(HtmlFormatter fmt, SocialCalculusContext scc,
            GsQuery gq, Scenario scenario) {
        GsQuery gqChoice = new GsQuery(gq);
        gqChoice.setQueryType(QueryType.SCENARIO);
        gqChoice.setId(scenario.getScenarioInstance());
        String label = PwScenario.pwConcise(scenario, scc);
        return PwLinks.addLinkToQuery(fmt, gqChoice, label,
                PwHelper.CLASS_BODYLINK);
    }

    /**
     * Adds a link to an actor in a specific scenario
     * @param fmt
     * @param scc
     * @param query
     * @param scenario
     * @param actorName
     */
	public static String linkToActor(HtmlFormatter fmt, SocialCalculusContext scc, 
			GsQuery query, Scenario scenario, String actorName) {
        GsQuery gqChoice = new GsQuery(query);
        gqChoice.setQueryType(QueryType.ACTOR);
        gqChoice.setId(scenario.getScenarioInstance() + "_" + actorName);
        String label = actorName;
        return PwLinks.addLinkToQuery(fmt, query, label,
                PwHelper.CLASS_BODYLINK);
	}

    /**
     * Adds a link to a specific social agent
     * @param fmt
     * @param scc
     * @param query
     * @param scenario
     * @param actorName
     */
	public static String linkToSocialAgent(HtmlFormatter fmt, SocialCalculusContext scc, 
			GsQuery query, SocialAgent socialAgent) {
        GsQuery gqChoice = new GsQuery(query);
        gqChoice.setQueryType(QueryType.SOCIAL_AGENT);
        gqChoice.setId(socialAgent.getName());
        String label = socialAgent.getName();
        return PwLinks.addLinkToQuery(fmt, gqChoice, label,
                PwHelper.CLASS_BODYLINK);
	}

}
