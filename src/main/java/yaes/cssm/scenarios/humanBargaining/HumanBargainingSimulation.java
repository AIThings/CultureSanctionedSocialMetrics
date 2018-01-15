package yaes.cssm.scenarios.humanBargaining;

import yaes.cssm.cssm.Constants;
import yaes.framework.simulation.IContext;
import yaes.framework.simulation.ISimulationCode;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.format.IDetailLevel;

public class HumanBargainingSimulation implements ISimulationCode, Constants,
		IDetailLevel {
	public static String simulationLabel = "Human Bargaining Simulation";

	@Override
	public void postprocess(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		HumanBargainingContext context = (HumanBargainingContext) theContext;
		context.initialize(sip, sop);

	}

	@Override
	public int update(double time, SimulationInput sip, SimulationOutput sop,
			IContext theContext) {

		HumanBargainingContext context = (HumanBargainingContext) theContext;
		context.getWorld().setTime(time);
		// hardwire here the actions
		boolean isDone = false;
		switch ((int)time) {
		case 0:
			isDone = context.scenario.performProgress(
					sbHumanBargaining.ACTIONTYPE_OFFER,
					sbHumanBargaining.ACTOR_SELLER, 30);
			break;
		case 1:
			isDone = context.scenario.performProgress(
					sbHumanBargaining.ACTIONTYPE_OFFER,
					sbHumanBargaining.ACTOR_BUYER, 30);
			break;
		case 2:
			isDone = context.scenario.performProgress(
					sbHumanBargaining.ACTIONTYPE_OFFER,
					sbHumanBargaining.ACTOR_SELLER, 50);
			break;
		case 3:
			isDone = context.scenario.performProgress(
					sbHumanBargaining.ACTIONTYPE_OFFER,
					sbHumanBargaining.ACTOR_BUYER, 40);
			break;
		case 4:
			isDone = context.scenario.performProgress(
					sbHumanBargaining.ACTIONTYPE_ACCEPT,
					sbHumanBargaining.ACTOR_SELLER);
			break;
		}

		if (isDone) {
			// if the context has returned true, it means that the scene has
			// finished
			// so we return zero from the update, and terminate the simulation.
			// TextUi.println(context.scenarioSet.toStringDetailed(MAX_DETAIL));

			return 0;
		}
		return 1;

	}

}
