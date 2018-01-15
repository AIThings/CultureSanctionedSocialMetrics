package yaes.cssm.scenarios;

import yaes.cssm.actions.Action;
import yaes.cssm.behaviors.IActorSelector;
import yaes.cssm.behaviors.IBehavior;
import yaes.cssm.behaviors.IScenarioSelector;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Scenario;
import yaes.framework.simulation.IContext;
import yaes.framework.simulation.ISimulationCode;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

/**
 * Implementation of a generic simulation for the social calculus. The idea here
 * is that this simulation model assumes that we have a SocialCalculusContext
 * object
 * 
 * @author lboloni
 * 
 */
public class SocialCalculusSimulation implements ISimulationCode, IDetailLevel {

	@Override
	public void postprocess(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		SocialCalculusContext context = (SocialCalculusContext) theContext;

		for (String actor : context.agents.keySet()) {
			for (CSSM cssm : context.agents.get(actor).getCSSMs()) {

				double weights = context.agents.get(actor).getWeight(cssm);
				// TextUi.println("#######  Weights value in postprocess function: "+
				// weights);
				sop.update(cssm.getPseudoName(), weights);
			}

			for (ConcreteBelief cb : context.agents.get(actor).getCBs()) {

				double weights = context.agents.get(actor).getWeight(cb);
				// TextUi.println("#######  Weights value in postprocess function: "+
				// weights);
				sop.update(cb.getPseudoName(), weights);
			}

		}
		// double val = sop.getRandomVar("xx").getMax();
		// sop.update("whatIwanttoplot", val);
	}

	@Override
	public void setup(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		SocialCalculusContext context = (SocialCalculusContext) theContext;
		context.initialize(sip, sop);

		for (String actor : context.agents.keySet()) {
			for (CSSM cssm : context.agents.get(actor).getCSSMs()) {
				sop.createVariable(cssm.getPseudoName(), false);
			}

			for (ConcreteBelief cb : context.agents.get(actor).getCBs()) {
				sop.createVariable(cb.getPseudoName(), false);
			}

		}

	}

	/**
	 * The update function performs a single action based on the behaviors
	 */
	@Override
	public int update(double time, SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		SocialCalculusContext context = (SocialCalculusContext) theContext;
		context.getWorld().setTime(time);
		// if there is scripted action, perform it
		Action sa = context.getNextPrePlannedAction();
		if (sa == null) {
			// choose the scenario
			IScenarioSelector scenarioSelector = context.getScenarioSelector();
			Scenario scenario = scenarioSelector.chooseScenario(
					context.getScenarioSet(), context);
			// choose the actor
			IActorSelector actorSelector = context.getActorSelector(scenario);
			String actor = actorSelector.getActor(scenario, context);
			// choose the behavior
			IBehavior behavior = context.getBehavior(scenario, actor);
			sa = behavior.getAction();

		}
		boolean isDone = context.applyAction(sa);
		if (isDone) {
			// if the context has returned true, it means that the scene has
			// finished
			// so we return zero from the update, and terminate the simulation.
			TextUi.println(context.scenarioSet.toStringDetailed(MAX_DETAIL));

			return 0;
		}
		return 1;
	}

}
