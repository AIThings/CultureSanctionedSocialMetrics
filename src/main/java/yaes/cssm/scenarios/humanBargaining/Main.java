package yaes.cssm.scenarios.humanBargaining;

import java.io.IOException;

import yaes.Version;
import yaes.cssm.cssm.Constants;
import yaes.framework.simulation.Simulation;
import yaes.framework.simulation.SimulationInput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

public class Main implements Constants, IDetailLevel{

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		TextUi.println(Version.versionString());
		SimulationInput model = Main.createDefaultSimulationInput();
		Main.doSimpleRun(model, false, null);
		

	}

	public static void doSimpleRun(SimulationInput model, boolean visual,
			String precanned) throws InstantiationException,
			IllegalAccessException, IOException {			
		HumanBargainingContext context = new HumanBargainingContext();
		Simulation.simulate(model, HumanBargainingSimulation.class, context);
		
		
	}

	private static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(HumanBargainingContext.class);
		model.setSimulationClass(HumanBargainingSimulation.class);
		model.setStopTime(50);
		return model;
	}

}
