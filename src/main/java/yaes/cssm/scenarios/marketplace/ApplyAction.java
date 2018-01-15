package yaes.cssm.scenarios.marketplace;

import java.util.List;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;

public class ApplyAction {

	/**
	 * Applies an action
	 * 
	 * @param sa
	 * @return true if a terminal state has been reached
	 * 
	 */
	public static boolean applyAction(MarketPlaceContext context, Action sa) {

		if (sa == null) {

			TextUi.println("No More Actions to continue, System exitssssss");
			return true;
		}

		boolean finish = context.scenarioSet.performConcreteAction(
				sa.getScenarioInstance(), sa.getActionType().getActionType(),
				sa.getActor(), sa.getParameters());

		MarketplaceAIF aif = new MarketplaceAIF();
		List<ActionImpact> actionImpact = aif.getImpacts(context.scenarioSet
				.findScenario(sa.getScenarioInstance()), sa.getActionType()
				.getActionType(), sa.getActor(), sa.getParameters());
		if (actionImpact != null) {
			for (ActionImpact impact : actionImpact) {
				TextUi.println(impact.toStringDetailed(0));
			}
		}

		applyImpact(actionImpact, context);

		context.currentAction = sa;
		//Saving scenario state in log file 
		context.createCheckpoint();
//		context.createCheckpoint(
//				context.scenarioSet.findScenario(sa.getScenarioInstance())
//						.getFrameCount(), sa.getScenarioInstance());
		//Incrementing scenario frame state
		context.scenarioSet.findScenario(sa.getScenarioInstance())
				.incBy1Framecount();
		
		if (finish) {
			TextUi.println("Terminal state reached, done");
			return true;
		}
		return false;

	}

	private static void applyImpact(List<ActionImpact> actionImpact,
			MarketPlaceContext context) {
		if (actionImpact != null) {

			for (ActionImpact impact : actionImpact) {
				Scenario scene = context.scenarioSet.findScenario(impact
						.getScenarioName());
				if (impact.isCSSM()) {
					scene.setCSSMValue(impact.getCultureName(),
							impact.getMetricName(), impact.getSubActor(),
							impact.getPersActor(), impact.getEstActor(),
							impact.getNewValue());
				} else {
					scene.setCBValue(impact.getCultureName(),
							impact.getMetricName(), impact.getPersActor(),
							impact.getEstActor(), impact.getBeliefValue());
				}

			}
		}

	}

}
