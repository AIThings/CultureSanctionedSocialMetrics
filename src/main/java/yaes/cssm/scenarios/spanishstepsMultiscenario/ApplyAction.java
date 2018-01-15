package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.util.List;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.Scenario;
import yaes.ui.text.TextUi;

public class ApplyAction {

	public static boolean applyAction(SpanishStepsContext context, Action sa) {

		if (sa == null) {

			TextUi.println("No More Clients to continue, System exitssssss");
			return true;
		}

		boolean finish = context.scenarioSet.performConcreteAction(
				sa.getScenarioInstance(), sa.getActionType().getActionType(),
				sa.getActor(), sa.getParameters());

		SpanishStepsAIF aif = new SpanishStepsAIF();
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

	}

	private static void applyImpact(List<ActionImpact> actionImpact,
			SpanishStepsContext context) {
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
//
// String agentNameRole = sa.getFromActor();
// SocialAgent agentRole = context.agents.get(agentNameRole);
// applyAction(sa, agentRole, agentRole.getImpactMatrix(),context,
// agentNameRole);
// applyAction(sa, agentRole, agentRole.getBeliefMaxtrix(), context,
// agentNameRole);
//
// String agentNameRoleTo = sa.getToActor();
// SocialAgent agentRoleTo = context.agents.get(agentNameRoleTo);
// applyAction(sa,agentRoleTo, agentRoleTo.getImpactMatrix(), context,
// agentNameRoleTo);
// applyAction(sa,agentRoleTo, agentRoleTo.getBeliefMaxtrix(), context,
// agentNameRoleTo);
//
// if (agentNameRole.contains("ACTOR_SELLER")) {
// TextUi.println(sa.getFromActor()
// + " has public dignity: "
// + agentRole.getCSSMandCBValues().getValuesCSSM(
// new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD,
// ACTOR_SELLER, 0, 0, 0)));
// }
// if (agentNameRoleTo.contains("ACTOR_SELLER")) {
// TextUi.println(sa.getToActor()
// + " has public dignity: "
// + agentRoleTo.getCSSMandCBValues().getValuesCSSM(
// new CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD,
// ACTOR_SELLER, 0, 0, 0)));
// }
//
// for (String agentName : context.agents.keySet()) {
//
// if (agentName.contains("Perception")) {
// SocialAgent agent = context.agents.get(agentName);
// applyAction(sa, agent, agent.getImpactMatrix(), context, agentName);
// applyAction(sa, agent, agent.getBeliefMaxtrix(), context, agentName);
// }
// if (!(agentName.equalsIgnoreCase(agentNameRole)
// || agentName.equalsIgnoreCase(agentNameRoleTo) || agentName
// .contains("Perception"))) {
//
// SocialAgent agent = context.agents.get(agentName);
//
// if (agentName.contains("ACTOR_CLIENT")) {
// if (!(agent.isHaveClientOrSeller())) {
// applyAction(sa,agent, agent.getImpactMatrix(), context, agentName);
// applyAction(sa,agent, agent.getBeliefMaxtrix(), context, agentName);
//
// }
//
// }
// }
//
// }

// context.updateHistory(sa);
//
//
// context.agsi.applyAction(sa);
// if (context.agsi.getCurrentState() == "S11") {
// TextUi.println("Transaction Done");
// context.agents.get(sa.getFromActor()).setEndedTransaction(true);
// }
//
// if (context.agsi.getCurrentState() == "S12") {
// TextUi.println("Transaction Failed");
// context.agents.get(sa.getFromActor()).setEndedTransaction(true);
// }
// if (context.agsi.getCurrentState() == "CR") {
// TextUi.println("ACTOR_SELLER go to new ACTOR_CLIENT");
//
// if (!(context.currentClient == null)) {
// SocialAgent agent = context.agents.get(context.currentClient);
// agent.setHaveClientOrSeller(false);
// }
// if (!(context.currentSeller == null)) {
// SocialAgent agent = context.agents.get(context.currentSeller);
// agent.setHaveClientOrSeller(false);
// }
//
// if (!(sa.getFromActor() == null)) {
// SocialAgent agent = context.agents.get(sa.getFromActor());
// agent.setHaveClientOrSeller(false);
// }
//
// // agents.get(sa.getRole()).setHaveClientOrSeller(false);
//
// context.agsi.setCurrentState(context.agsi.getPreviouState());
// }
// if
// (context.asg.getProgressState(context.agsi.getCurrentState()).isTerminal()) {
// TextUi.println("Terminal state reached, done");
// return true;
// }
//
// /*
// * if (asg.getActionStateNode(agsi.getCurrentState()).isChangeRiver()) {
// * TextUi.println("ACTOR_SELLER go to new ACTOR_CLIENT");
// * agents.get(sa.getRole()).setHaveClientOrSeller(false);
// * asg.getActionStateNode(agsi.getCurrentState()).setChangeRiver(false);
// *
// * }
// */
// return false;
// }
//
// private static void applyAction(Action sa, SocialAgent agentRole,
// ActionValueImpactMatrix impactMatrix, IValueSource valueSource, String
// agentName) {
//
// /*
// * if (agentName.equalsIgnoreCase("eric(ACTOR_CLIENT)")){
// * TextUi.println("here is eric"); }
// */
//
// if ((agentName.equalsIgnoreCase(sa.getFromActor()) ||
// agentName.equalsIgnoreCase(sa.getToActor()))) {
//
// for (CSSM valueName : agentRole.getCssmSet().getcssmNameList()) {
// // if (valueName.equals(new CSSM(SPANISH_STEPS, TIME, ACTOR_SELLER,
// // ACTOR_SELLER, ACTOR_SELLER, 0, 0, 0))
// // || valueName.equals(new CSSM(SPANISH_STEPS, TIME,
// // ACTOR_CLIENT, ACTOR_CLIENT, ACTOR_CLIENT, 0, 0, 0))) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// double oldValue =agentRole.getCSSMandCBValues().getValuesCSSM(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// // }
//
// }
//
// }
//
// }
//
// private static void applyAction(Action sa, SocialAgent agentRole,
// ActionBeliefImpactMatrix impactMatrix, IValueSource valueSource, String
// agentName) {
//
// /*
// * if (agentName.equalsIgnoreCase("eric(ACTOR_CLIENT)")){
// * TextUi.println("here is eric"); }
// */
//
// if ((agentName.equalsIgnoreCase(sa.getFromActor()) ||
// agentName.equalsIgnoreCase(sa.getToActor()))) {
// for (ConcreteBelief valueName : agentRole.getCbset().getCbNameList()) {
// if (valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CLIENT, ACTOR_SELLER, 0, 0, 0))
// || valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CLIENT, ACTOR_CLIENT, 0, 0, 0))) {
// if (sa.getAction().getActionName().equalsIgnoreCase("A7")
// || sa.getAction().getActionName().equalsIgnoreCase("A9")
// || sa.getAction().getActionName().equalsIgnoreCase("A16")) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// true);
// double oldValue = agentRole.getCSSMandCBValues().getValueCB(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// } else {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// double oldValue = agentRole.getCSSMandCBValues().getValueCB(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// }
// } else if (valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CROWD, ACTOR_SELLER, 0, 0, 0))
// || valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CROWD, ACTOR_CLIENT, 0, 0, 0))) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// agentRole.getCSSMandCBValues().setValue(valueName, impact);
// } else {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// double oldValue = agentRole.getCSSMandCBValues().getValueCB(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// }
//
// }
//
// } else {
//
// for (ConcreteBelief valueName : agentRole.getCbset().getCbNameList()) {
// if ((valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CLIENT, ACTOR_SELLER, 0, 1, 0.1))
// || valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CLIENT, ACTOR_CLIENT, 0, 1, 0.1)))) {
//
// if (sa.getAction().getActionName().equalsIgnoreCase("A7")
// || sa.getAction().getActionName().equalsIgnoreCase("A9")
// || sa.getAction().getActionName().equalsIgnoreCase("A16")) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// double oldValue = agentRole.getCSSMandCBValues().getValueCB(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// }
//
// } else if (valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CROWD, ACTOR_SELLER, 0, 1, 0.1))
// || valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CROWD, ACTOR_CLIENT, 0, 1, 0.1))) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// agentRole.getCSSMandCBValues().setValue(valueName, impact);
// } else if ((valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT,
// ACTOR_CLIENT, ACTOR_CLIENT, 0, 1, 0.1))
// || valueName.equals(new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
// ACTOR_CLIENT, ACTOR_SELLER, 0, 1, 0.1)))) {
// double impact = impactMatrix.getImpact(valueName,
// sa.getAction().getActionName(), sa.getDetailValues(), valueSource, agentName,
// false);
// double oldValue = agentRole.getCSSMandCBValues().getValueCB(valueName);
// // setValue(valueName, impact);
// agentRole.getCSSMandCBValues().setValue(valueName, oldValue + impact);
// }
//
// }
// }
//
// }
//
//
// }
