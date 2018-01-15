package yaes.cssm.scenarios.giveWayScenario;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.Constants;
import yaes.framework.simulation.IContext;
import yaes.framework.simulation.ISimulationCode;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

/**
 * Simulation class : Simulation setup and update methods
 * @author Taranjeet
 *
 */

public class GiveWaySimulation implements ISimulationCode, Constants, IDetailLevel{

	
	public static String simulationLabel = "Give Way Simulation";
	@Override
	public void postprocess(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		// Nothing here
		
	}

	/**
	 * Assign simulator input/output to simulation context
	 */
	@Override
	public void setup(SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		GiveWayContext context = (GiveWayContext) theContext;
		
		context.initialize(sip, sop);
		
	}

	/**
	 * update the simulation step
	 */
	@Override
	public int update(double time, SimulationInput sip, SimulationOutput sop,
			IContext theContext) {
		
		GiveWayContext context = (GiveWayContext) theContext;
		context.getWorld().setTime(time);
		Action sa = context.getNextPrePlannedAction();
		boolean isDone = ApplyAction.applyAction(context, sa);
		
		if(isDone){
			TextUi.println(context.scenarioSet.toStringDetailed(MAX_DETAIL));
			return 0;
		}
		return 1;
	}
	
	@Override
	public String toString(){
		return GiveWaySimulation.simulationLabel;
	}

}
