package yaes.cssm.behaviors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;

/**
 * An actor selector which asks interactively for the actor to be given the
 * right to take an action
 * 
 * @author lboloni
 * 
 */
public class aselInteractive implements IActorSelector, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4380551610499487615L;
	/**
	 * If true the selector will ask for a confirmation even if there is a
	 * single choice.
	 */
	private boolean forceInteractionIfNoChoice = false;

	/**
	 * @param forceInteractionIfNoChoice
	 */
	public aselInteractive(boolean forceInteractionIfNoChoice) {
		super();
		this.forceInteractionIfNoChoice = forceInteractionIfNoChoice;
	}

	/**
	 * Get the actor interactively
	 */
	@Override
	public String getActor(Scenario scenario, ISocialCalculusContext socialCalculusContext) {
		Set<String> actors = scenario.getProgressGraph()
				.getProgressState(scenario.getCurrentProgressState())
				.getActorsWhoCanAct();
		List<String> actorItems = new ArrayList<String>();
		for (String actor : actors) {
			actorItems.add(actor);
		}
		// no actor, leave the program here
		if (actorItems.size() == 0) {
			TextUi.errorPrint("aselInteractive: no actors can take action in this state: "
					+ scenario.getCurrentProgressState());
			System.exit(1);
		}
		// there is only one actor: unless forced, not be interactive
		if (actorItems.size() == 1 && !forceInteractionIfNoChoice) {
			String actorName = actorItems.get(0);
			TextUi.println("In state " + scenario.getCurrentProgressState()
					+ " actor chosen was: " + actorName + " (only choice)");
			return actorName;
		}
		TextUi.println("Current state:" + scenario.getCurrentProgressState());
		TextUi.println("Select the actor:");
		int actorId = TextUi.menu(actorItems, 0, "Choice: ");
		String actorName = actorItems.get(actorId);
		TextUi.println("In state " + scenario.getCurrentProgressState()
				+ " actor chosen was: " + actorName + " (only choice)");
		return actorName;

	}

}
