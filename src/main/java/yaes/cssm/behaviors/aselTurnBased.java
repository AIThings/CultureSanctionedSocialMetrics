package yaes.cssm.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;


/**
 * An actor selector which is to be used in turn based scenarios. In this scenarios, only one
 * actor can take an action in any progress state: choose that. 
 * 
 * @author lboloni
 *
 */
public class aselTurnBased implements IActorSelector {

	private static final long serialVersionUID = 6683912236908242710L;

	@Override
	public String getActor(Scenario scenario, ISocialCalculusContext socialCalculusContext) {
		Set<String> actors = scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActorsWhoCanAct();
		List<String> actorItems = new ArrayList<>();
		for (String actor : actors) {
			actorItems.add(actor);
		}
		// no actor, leave the program here
		if (actorItems.size() == 0) {
			TextUi.errorPrint("aselTurnBased: no actor can take action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}
		if (actorItems.size() > 1) {
			TextUi.errorPrint("aselTurnBased: more than one actor can take an action in this state: "
					+ scenario.getCurrentProgressState());
			TextUi.errorPrint("This doesn't appear to be a turn based scenario, use something other than aselTurnBased");
			System.exit(1);
		}
		// there is only one actor
		String actorName = actorItems.get(0);
		TextUi.println("In state " + scenario.getCurrentProgressState()
				+ " actor chosen was: " + actorName + " (only choice)");
		return actorName;
	}

}
