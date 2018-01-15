package yaes.cssm.scenarios.checkpoint;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yaes.Version;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.cssm.util.plot.GenerateValuePlot;
import yaes.cssm.util.plot.ResultSet;
import yaes.cssm.util.plot.plotSpecCB;
import yaes.cssm.util.plot.plotSpecCSSM;
import yaes.framework.simulation.Simulation;
import yaes.framework.simulation.SimulationInput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.simulationcontrol.SimulationControlGui;
import yaes.ui.text.TextUi;
import yaes.util.DirUtil;
import yaes.util.FileWritingUtil;

/**
 * Checkpoint Scenario Using CSSM
 * @author Taranjeet
 *
 */
public class Main implements Constants, IDetailLevel {
	private static final String SCENARIO1 = "Scenario1: The checkpoint memebers are very strict";
	private static final String SCENARIO2 = "Scenario2: The checkpoint memebers are very friendly";
	private static final String SCENARIO3 = "Scenario3: The checkpoint memebers have abrupt change in behavior on high alert days";
	private static final String SCENARIO4 = "Scenario4: The checkpoint memebers have friendly persuasive nature";
	private static final String SCENARIO5 = "Scenario5: The checkpoint memebers take help of robot for unpleasant action";

	private static String scenario, result;

	// private static final String MENU_MILESTONE1 =
	// "Milestone 1/2: No graphics - Input required from the user";
	private static final String MENU_SIMPLE_RUN_VISUAL = "Simple Run with Visual Controller";
	private static final String MENU_RUN_SCENARIOS = "Run the scenarios without user prompt input";

	/**
	 * External files for 
	 * 1)Pre-canned Scenario files
	 * 2)Graph Data files
	 * 3)Creating Test scenario files
	 * 4)log files for saving history  
	 */
	public static final File inputDir = new File("scenarios/checkpoint");
	public static final File graphDir = new File("data/cssm-checkpoint-graphs");
	public static final File testDir = new File("data/cssm-checkpoint-test");
	public static final File historyDir = new File("data/cssm-checkpoint-log");
	
	/**
	 * Initializing all the file paths
	 */
	static{
		DirUtil.guaranteeDirectory(inputDir.toString());
		DirUtil.guaranteeDirectory(testDir.toString());
		DirUtil.guaranteeDirectory(historyDir.toString());
		DirUtil.guaranteeDirectory(graphDir.toString());
	}

	
	public static final CheckPointContext context = new CheckPointContext();

	public static void main(String[] args) throws Exception {

		TextUi.println(Version.versionString());
		final SimulationInput model = Main.createDefaultSimulationInput();
		

		final List<String> ScenarioMenu = new ArrayList<String>();
		ScenarioMenu.add(Main.SCENARIO1);
		ScenarioMenu.add(Main.SCENARIO2);
		ScenarioMenu.add(Main.SCENARIO3);
		ScenarioMenu.add(Main.SCENARIO4);
		ScenarioMenu.add(Main.SCENARIO5);

		final List<String> menu = new ArrayList<String>();
		// menu.add(Main.MENU_MILESTONE1);

		menu.add(Main.MENU_SIMPLE_RUN_VISUAL);
		menu.add(Main.MENU_RUN_SCENARIOS);

		result = TextUi.menu(menu, MENU_SIMPLE_RUN_VISUAL, "Choose:");
		if (result.equals(Main.MENU_SIMPLE_RUN_VISUAL)) {
			Main.doSimpleRun(model, false, null);
		}
		if (result.equals(Main.MENU_RUN_SCENARIOS)) {
			scenario = TextUi.menu(ScenarioMenu, SCENARIO1, "Choose:");
			Main.doSimpleRun(model, false, scenario.substring(0, 9));

		}

		Toolkit.getDefaultToolkit().beep();
		TextUi.println("Done, exiting");
		System.exit(0);
	}

	public static String getScenario() {
		return scenario;
	}

	public static String getResult() {
		return result;
	}

	/**
	 * Creates the default simulation input
	 * 
	 * @return
	 */
	public static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(CheckPointContext.class);
		model.setSimulationClass(CheckPointSimulation.class);
		model.setStopTime(1000);
		return model;
	}

	public static void setFileConstants(File file, String precanned,
			SimulationInput sip) {

		if (precanned == null) {
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
			String s = df.format(now);
			sip.setParameter(ACTION_FILE_READ, "");
			sip.setParameter(ACTION_FILE_WRITE, new File(file, "test" + s
					+ ".sspc").toString());

		} else {
			sip.setParameter(ACTION_FILE_READ, new File(file, precanned
					+ ".sspc").toString());
			sip.setParameter(ACTION_FILE_WRITE, "");

		}
	}

	/**
	 * Does a simple run in the visual mode - for experimentation etc.
	 * 
	 * @param model
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public static void doSimpleRun(SimulationInput model, boolean visual,
			String precanned) throws InstantiationException,
			IllegalAccessException, IOException {
		final SimulationInput sip = Main.createDefaultSimulationInput();

		sip.setParameter(DIR_HISTORY,
				new File(historyDir.toString()).toString());
		
		
		if (precanned == null) {
			setFileConstants(testDir, precanned, sip);
		} else {
			setFileConstants(inputDir, precanned, sip);
		}

		if (visual) {
			SimulationControlGui.simulate(sip, CheckPointSimulation.class,
					context);
		} else {
			Simulation.simulate(sip, CheckPointSimulation.class, context);
		}

		String plot = "";
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		Scenario scenario = context.scenarioSet.findScenario("one");
		ResultSet resultSet = new ResultSet(sip.getParameterString(DIR_HISTORY));
		
		
		// plot the social values of the sergeant

		List<plotSpecCSSM> toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, SECURITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(SECURITY, cssm);
		toplot.add(svps);
		


		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);
		
		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, Actors.CROWD, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);
		
		
		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);
		

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "SergeantBob_"
				+ precanned + ".m"), plot);

		// plot the social values of the kebab seller
		toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, DIGNITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);
		
		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, Actors.CROWD, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);
		
		
		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "VendorHassan_"
				+ precanned + ".m"), plot);

		// plot the public
				
		List<plotSpecCB> toplotCB = new ArrayList<>();
		ConcreteBelief cb = null;
		plotSpecCB sbps = null;
		cb = ConcreteBelief.createCB(scenario, IS_A_FRIEND,
				ACTOR_VENDOR, ACTOR_VENDOR);
		sbps = new plotSpecCB("CB(IsAFriend,Vendor,Vendor)", cb);
		toplotCB.add(sbps);
	

		cb = ConcreteBelief.createCB(scenario, POSES_A_THREAT,
				ACTOR_SERGEANT, ACTOR_SERGEANT);
		sbps = new plotSpecCB("CB(PosesThreat,Sergeant,Sergeant)", cb);
		toplotCB.add(sbps);
		
		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "perception_"
				+ precanned + ".m"), plot);
	}

	// public static void generatePlots(String precanned,
	// CheckPointContext context) throws IOException {
	// String plot = "";
	//
	// // plot the social values of the sergeant
	//
	// List<SVPlotSpec> toplot = new ArrayList<SVPlotSpec>();
	//
	// toplot.add(new SVPlotSpec("SergeantBob", SECURITY, new CSSM(
	// CHECK_POINT, SECURITY, SERGEANT, SERGEANT, SERGEANT, 0, 100,
	// 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", DIGNITY,
	// new CSSM(CHECK_POINT, DIGNITY, SERGEANT, SERGEANT, SERGEANT,
	// 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", POLITENESS_PUBLIC,
	// new CSSM(CHECK_POINT, POLITENESS, SERGEANT, ACTOR_CROWD, SERGEANT,
	// 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", POLITENESS_PEER, new CSSM(
	// CHECK_POINT, POLITENESS, SERGEANT, VENDOR, SERGEANT, 0, 100,
	// 50)));
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "client_"
	// + precanned + ".m"), plot);
	//
	// // plot the social values of the kebab seller
	// toplot.add(new SVPlotSpec("VendorHassan", DIGNITY, new CSSM(
	// CHECK_POINT, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorHassan", POLITENESS_PUBLIC, new CSSM(
	// CHECK_POINT, POLITENESS, VENDOR, ACTOR_CROWD, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorHassan", POLITENESS_PEER, new CSSM(
	// CHECK_POINT, POLITENESS, VENDOR, SERGEANT, VENDOR, 0, 100, 50)));
	//
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "client_"
	// + precanned + ".m"), plot);
	//
	// // plot the public
	// List<SBPlotSpec> toplotCB = new ArrayList<>();
	// toplotCB.add(new SBPlotSpec("VendorHassan", IS_A_FRIEND,
	// new ConcreteBelief(CHECK_POINT, IS_A_FRIEND,
	// VENDOR, VENDOR, 0, 1.0, 0)));
	//
	// toplotCB.add(new SBPlotSpec("SergeantBob", POSES_A_THREAT,
	// new ConcreteBelief(CHECK_POINT, POSES_A_THREAT,
	// SERGEANT, SERGEANT, 0, 1.0, 0)));
	//
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "perception_"
	// + precanned + ".m"), plot);
	//
	// }
}
