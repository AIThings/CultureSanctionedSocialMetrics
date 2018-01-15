package yaes.cssm.scenarios.checkpoint;

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
	public static boolean applyAction(CheckPointContext context, Action sa) {

		if (sa == null) {

			TextUi.println("No More Actions to continue, System exitssssss");
			return true;
		}

		boolean finish = context.scenarioSet.performConcreteAction(
				sa.getScenarioInstance(), sa.getActionType().getActionType(),
				sa.getActor(), sa.getParameters());

		CheckPointAIF aif = new CheckPointAIF();
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
		// Saving scenario state in log file
		context.createCheckpoint();
//		context.createCheckpoint(
//				context.scenarioSet.findScenario(sa.getScenarioInstance())
//						.getFrameCount(), sa.getScenarioInstance());
		// Incrementing scenario frame state
		context.scenarioSet.findScenario(sa.getScenarioInstance())
				.incBy1Framecount();

		if (finish) {
			TextUi.println("Terminal state reached, done");
			return true;
		}
		return false;

		// String agentNameRole = sa.getFromActor();
		// String agentNameRoleTo = sa.getToActor();
		//
		// if (agentNameRole != null) {
		// SocialAgent agentRole = socialScenario.agents.get(agentNameRole);
		// applyActionCSSM(sa, agentRole, agentRole.getImpactMatrix(),
		// socialScenario, agentNameRole);
		// applyActionCB(sa, agentRole, agentRole.getBeliefMaxtrix(),
		// socialScenario, agentNameRole);
		// }
		//
		// if (agentNameRoleTo != null) {
		// SocialAgent agentRoleTo =
		// socialScenario.agents.get(agentNameRoleTo);
		// applyActionCSSM(sa, agentRoleTo, agentRoleTo.getImpactMatrix(),
		// socialScenario, agentNameRoleTo);
		// applyActionCB(sa, agentRoleTo, agentRoleTo.getBeliefMaxtrix(),
		// socialScenario, agentNameRoleTo);
		// }
		//
		// socialScenario.updateHistory(sa);
		// socialScenario.agsi.applyAction(sa);
		// if (socialScenario.asg.getProgressState(
		// socialScenario.agsi.getCurrentState()).isTerminal()) {
		// TextUi.println("Terminal state reached, done");
		// return true;
		// }
		// return false;
	}

	/**
	 * Apply changes over the CSSM and CB
	 * 
	 * @param actionImpact
	 * @param context
	 */
	public static void applyImpact(List<ActionImpact> actionImpact,
			CheckPointContext context) {
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
	//
	// private static void applyActionCSSM(Action sa, SocialAgent agent,
	// ActionValueImpactMatrix impactMatrix, IValueSource valueSource,
	// String agentName) {
	// for (CSSM valueName : agent.getCssmSet().getcssmNameList()) {
	//
	// double impact =
	// impactMatrix.getImpact(valueName, sa.getAction()
	// .getActionName(), sa.getDetailValues(),
	// valueSource, agentName);
	//
	// double oldValue = agent.getCSSMandCBValues().getValuesCSSM(valueName);
	// agent.getCSSMandCBValues().setValue(valueName, oldValue + impact);
	// }
	//
	// }
	//
	// private static void applyActionCB(Action sa, SocialAgent agent,
	// ActionBeliefImpactMatrix impactMatrix, IValueSource valueSource,
	// String agentName) {
	//
	// for (ConcreteBelief valueName : agent.getCbset().getCbNameList()) {
	//
	// double impact =
	// impactMatrix.getImpact(valueName, sa.getAction()
	// .getActionName(), sa.getDetailValues(),
	// valueSource, agentName);
	//
	// double oldValue = agent.getCSSMandCBValues().getValueCB(valueName);
	// agent.getCSSMandCBValues().setValue(valueName, oldValue + impact);
	// }
	//
	// }

}
