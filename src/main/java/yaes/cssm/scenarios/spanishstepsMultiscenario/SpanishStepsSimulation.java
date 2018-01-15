package yaes.cssm.scenarios.spanishstepsMultiscenario;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.Constants;
import yaes.cssm.scenarios.HumanPlayerTextUi;
import yaes.framework.simulation.IContext;
import yaes.framework.simulation.ISimulationCode;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

public class SpanishStepsSimulation implements IDetailLevel, Constants,
		ISimulationCode {

	public static String simulationLabel = "CSSM Simulation";

	@Override
	public void postprocess(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		// nothing here

	}

	@Override
	public void setup(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		SpanishStepsContext context = (SpanishStepsContext) theContext;
		context.initialize(sip, sop);

	}

	@Override
	public int update(double time, SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		SpanishStepsContext context = (SpanishStepsContext) theContext;
		context.getWorld().setTime(time);
		Action sa = context.getNextPrePlannedAction();
		if (sa == null) {
			sa = HumanPlayerTextUi.chooseAction(context.getScenarioSet());
//			sa = HumanPlayerTextUi.chooseAction(context);
//			try {
//				File outputFile = new File(sip.getParameterString(ACTION_FILE_WRITE));
//				StringBuilder str = new StringBuilder();
//				str.append(sa.getFromActor()+" "+sa.getToActor()+" "+sa.getAction().getActionName());
//				for (double d : sa.getDetailValues()) {
//					  str.append(" " + d);
//				}
//				str.append("\n");
//				FileWritingUtil.writeToTextFile(outputFile, str.toString());
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
		} else {
//			String sellerrole = null;
//			String clientrole = null;
//			String role = sa.getFromActor();
//			if (role.contains("seller")) {
//				sellerrole = role;
//			} else
//				clientrole = role;
//
//			role = sa.getToActor();
//			if (role.contains("seller")) {
//				sellerrole = role;
//			} else
//				clientrole = role;
//
//			context.setCurrentClient(clientrole);
//			context.setCurrentSeller(sellerrole);
//
//			String pairname = context.getPairedSellerClient(sellerrole
//					+ clientrole);
//			ProgressGraphInstance agsi = context.getAsgiMulti(pairname);
//			context.setAgsi(agsi);
		}

		boolean isDone = ApplyAction.applyAction(context, sa);

		if (isDone) {
			// if the context has returned true, it means that the scene has
			// finished
			// so we return zero from the update, and terminate the simulation.
			TextUi.println(context.scenarioSet.toStringDetailed(IDetailLevel.MAX_DETAIL));

			return 0;
		}
		return 1;
	}

	@Override
	public String toString() {
		return "Spanish Steps " + SpanishStepsSimulation.simulationLabel;
	}

}
