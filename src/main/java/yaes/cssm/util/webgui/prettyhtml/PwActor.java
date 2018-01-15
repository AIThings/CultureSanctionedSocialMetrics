package yaes.cssm.util.webgui.prettyhtml;

import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;
import yaes.ui.format.HtmlFormatter;

/**
 * Pretty printing the actor in a social calculus scenario
 * @author lboloni
 *
 */
public class PwActor {

	public static void generate(HtmlFormatter fmt, SocialCalculusContext scc,
			GsQuery query) {
		fmt.addPre("here will come the printing of the actor, its responsibilities etc.");
	}

}
