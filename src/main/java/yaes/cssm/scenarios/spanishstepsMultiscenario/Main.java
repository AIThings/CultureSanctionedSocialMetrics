package yaes.cssm.scenarios.spanishstepsMultiscenario;

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
import yaes.ui.format.ToStringDetailed;
import yaes.ui.simulationcontrol.SimulationControlGui;
import yaes.ui.text.TextUi;
import yaes.util.DirUtil;
import yaes.util.FileWritingUtil;

public class Main implements Constants, IDetailLevel, ToStringDetailed {

	private static final String MENU_RUN_SCENARIOS = "Run scenarios";
	private static final String MENU_SIMPLE_RUN_VISUAL = "Simple Run with Visual Controller";

	private static final String SCENARIO1 = "Scenario1: Without interleaving and without breaks";
	private static final String SCENARIO2 = "Scenario2: Without interleaving and with breaks";
	private static final String SCENARIO3 = "Scenario3: Without interleaving";

	/**
	 * External files for 1)Pre-canned Scenario files 2)Graph Data files
	 * 3)Creating Test scenario files 4)log files for saving history
	 */
	public static final File inputDir = new File("scenarios/spanishstepsMultiscenario");
	public static final File graphDir = new File(
			"data/cssm-multiscenario-graphs");
	public static final File testDir = new File("data/cssm-multiscenario-test");
	public static final File historyDir = new File(
			"data/cssm-multiscenario-log");

	/**
	 * Initializing all the file paths
	 */
	static {
		DirUtil.guaranteeDirectory(inputDir.toString());
		DirUtil.guaranteeDirectory(testDir.toString());
		DirUtil.guaranteeDirectory(historyDir.toString());
		DirUtil.guaranteeDirectory(graphDir.toString());
	}

	/**
	 * Creates the default simulation input
	 * 
	 * @return
	 */
	public static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(SpanishStepsContext.class);
		model.setSimulationClass(SpanishStepsSimulation.class);
		model.setStopTime(50);
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

		SpanishStepsContext context = new SpanishStepsContext();

		if (visual) {
			SimulationControlGui.simulate(sip, SpanishStepsSimulation.class,
					context);
		} else {
			Simulation.simulate(sip, SpanishStepsSimulation.class, context);
		}

		String plot = "";
		CSSM cssm = null;
		ConcreteBelief cb = null;
		plotSpecCB sbps = null;
		plotSpecCSSM svps = null;
		Scenario scenario1 = context.scenarioSet.findScenario("one");
		Scenario scenario2 = context.scenarioSet.findScenario("two");
		Scenario scenario3 = context.scenarioSet.findScenario("three");

		ResultSet resultSet = new ResultSet(sip.getParameterString(DIR_HISTORY));

		// plot the seller
		List<plotSpecCB> toplot = new ArrayList<>();
		cb = ConcreteBelief.createCB(scenario1, Q_GIFT,
				Actors.CLIENT, Actors.SELLER);
		sbps = new plotSpecCB("CB(one,Q-Gift,Client,Seller)", cb);
		toplot.add(sbps);

		cb = ConcreteBelief.createCB(scenario1, 
				Q_AGREED, Actors.CROWD, Actors.SELLER);
		sbps = new plotSpecCB("CB(one,Q-Agreed,Crowd,Seller)", cb);
		toplot.add(sbps);

		cb = ConcreteBelief.createCB(scenario1, IS_DECEPTIVE,
				Actors.CLIENT, Actors.SELLER);
		sbps = new plotSpecCB("CB(one,Q-IsDeceptive,Client,Seller)", cb);
		toplot.add(sbps);

		cb = ConcreteBelief.createCB(scenario1, IS_DECEPTIVE,
				Actors.CROWD, Actors.SELLER);
		sbps = new plotSpecCB("CB(one,Q-IsDeceptive,Crowd,Seller)", cb);
		toplot.add(sbps);

		plot = GenerateValuePlot.generatePlotCB(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "seller_"
				+ precanned + ".m"), plot);
		
		
		List<plotSpecCSSM> topSVlot = new ArrayList<>();
		cssm = CSSM.createCSSM(scenario1, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		topSVlot.add(svps);
		cssm = CSSM.createCSSM(scenario1, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		svps = new plotSpecCSSM(DIGNITY_PUBLIC, cssm);
		topSVlot.add(svps);
		cssm = CSSM.createCSSM(scenario1, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		svps = new plotSpecCSSM(POLITENESS, cssm);
		topSVlot.add(svps);
		cssm = CSSM.createCSSM(scenario1, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		topSVlot.add(svps);
		
		plot = GenerateValuePlot.generatePlotCSSM(resultSet, topSVlot);
		
		
		FileWritingUtil.writeToTextFile(new File(graphDir, "client_"
				+ precanned + ".m"), plot);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		TextUi.println(Version.versionString());
		final SimulationInput model = Main.createDefaultSimulationInput();

		final List<String> _scenarioMenu = new ArrayList<>();
		_scenarioMenu.add(SCENARIO1);
		_scenarioMenu.add(SCENARIO2);
		_scenarioMenu.add(SCENARIO3);

		final List<String> menu = new ArrayList<String>();
		menu.add(Main.MENU_SIMPLE_RUN_VISUAL);
		menu.add(Main.MENU_RUN_SCENARIOS);
		String result = TextUi.menu(menu, MENU_SIMPLE_RUN_VISUAL, "Choose:");
		if (result.equals(Main.MENU_SIMPLE_RUN_VISUAL)) {
			// Main.doSimpleRun(model, false, "precanned");
			Main.doSimpleRun(model, false, null);
		}
		if (result.equals(Main.MENU_RUN_SCENARIOS)) {
			String scenario = TextUi.menu(_scenarioMenu, SCENARIO1, "Choose: ");
			Main.doSimpleRun(model, false, scenario.substring(0, 9));

		}

		Toolkit.getDefaultToolkit().beep();
		TextUi.println("Done, exiting");
		System.exit(0);

	}

	@Override
	public String toStringDetailed(int detailLevel) {
		// TODO Auto-generated method stub
		return null;
	}

}
