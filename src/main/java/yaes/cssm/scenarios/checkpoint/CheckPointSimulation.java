package yaes.cssm.scenarios.checkpoint;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.Constants;
import yaes.cssm.scenarios.HumanPlayerTextUi;
import yaes.framework.simulation.IContext;
import yaes.framework.simulation.ISimulationCode;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

public class CheckPointSimulation implements ISimulationCode, Constants, IDetailLevel {
    public static String simulationLabel = "CSSM Simulation";

    @Override
	public void setup(SimulationInput sip, SimulationOutput sop, IContext theContext) {
    	CheckPointContext context = (CheckPointContext) theContext;
        context.initialize(sip, sop);
    }

    /**
     * Updates the simulation step.
     */
    
	@Override
	public int update(double time, SimulationInput sip, SimulationOutput sop,
            IContext theContext) {
    	CheckPointContext context = (CheckPointContext) theContext;
        context.getWorld().setTime(time);
        Action sa = context.getNextPrePlannedAction();


        if (sa == null) {
        	sa = HumanPlayerTextUi.chooseAction(context.getScenarioSet());
        }
//			sa = HumanPlayerTextUi.chooseAction(context.socialScenario.getAgsi());
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
//		}
        boolean isDone = ApplyAction.applyAction(context, sa);
        
        if (isDone) {
			// if the context has returned true, it means that the scene has
			// finished
			// so we return zero from the update, and terminate the simulation.
			TextUi.println(context.scenarioSet.toStringDetailed(MAX_DETAIL));

			return 0;
		}
		return 1;
		
    }

    /**
     * Collect data about the data.
     */
    @Override
	public void postprocess(SimulationInput sip, SimulationOutput sop,
            IContext theContext) {
    	// Nothing here
    }

    @Override
    public String toString() {
        return "Check point " + CheckPointSimulation.simulationLabel;
    }
}
