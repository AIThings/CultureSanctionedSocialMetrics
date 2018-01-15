package yaes.cssm.scenarios.giveWayScenario;

import java.util.List;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;

public class ApplyAction {

	public static boolean applyAction(GiveWayContext context, Action sa) {

		if (sa == null) {
			TextUi.println("No more states to continue, system exitsss");
			return true;
		}

		boolean finish = context.scenarioSet.performConcreteAction(
				sa.getScenarioInstance(), sa.getActionType().getActionType(),
				sa.getActor(), sa.getParameters());

		context.currentAction = sa;

		GiveWayAIF aif = new GiveWayAIF();

		List<ActionImpact> actionImpact = aif.getImpacts(context.scenarioSet
				.findScenario(sa.getScenarioInstance()), sa.getActionType()
				.getActionType(), sa.getActor(), sa.getParameters());

		for (ActionImpact impact : actionImpact) {
			TextUi.println(impact.toStringDetailed(0));
		}

		applyImpact(actionImpact, context);
		// Saving scenario state in log file
		context.createCheckpoint();
//		context.createCheckpoint(
//				context.scenarioSet.findScenario(sa.getScenarioInstance())
//						.getFrameCount(), sa.getScenarioInstance());
		// Incrementing scenario frame state
		context.scenarioSet.findScenario(sa.getScenarioInstance())
				.incBy1Framecount();

		if (finish) {
			TextUi.println("terminal state reach, done");
			context.scenarioSet.findScenario(sa.getScenarioInstance())
					.setCurrentProgressState("SS");
			context.scenarioSet.removeScenario(context.scenarioSet
					.findScenario(sa.getScenarioInstance()));

			// return true;
		}
		return false;
	}

	// Apply Action Impact on the CSSM and CB of Agent
	private static void applyImpact(List<ActionImpact> actionImpact,
			GiveWayContext context) {

		for (ActionImpact impact : actionImpact) {
			Scenario scene = context.scenarioSet.findScenario(impact
					.getScenarioName());
			if (impact.isCSSM()) {
				scene.setCSSMValue(impact.getCultureName(), impact.getMetricName(),
						impact.getSubActor(), impact.getPersActor(),
						impact.getEstActor(), impact.getNewValue());

			} else {

			}
		}

	}

}
