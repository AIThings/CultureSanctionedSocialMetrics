package yaes.cssm.scenarios.giveWayScenario;

import java.awt.Toolkit;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yaes.Version;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.cssm.util.plot.GenerateValuePlot;
import yaes.cssm.util.plot.ResultSet;
import yaes.cssm.util.plot.plotSpecCSSM;
import yaes.framework.simulation.Simulation;
import yaes.framework.simulation.SimulationInput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.simulationcontrol.SimulationControlGui;
import yaes.ui.text.TextUi;
import yaes.util.DirUtil;
import yaes.util.FileWritingUtil;

public class Main implements Constants, IDetailLevel {

	private static final String MENU_SIMPLE_RUN_VISUAL = "Simple Run";
	private static final String MENU_RUN_SCENARIOS = "Run scenarios";
	private static final File inputDir = new File(Main.class.getClassLoader().getResource("scenarios/giveWayScenario").getFile());	
	public static final File graphDir = new File("data/cssm-giveWayScenario-graphs");
	public static final File testDir = new File("data/cssm-giveWayScenario-test");
	public static final File historyDir = new File("data/cssm-giveWayScenario-log");
	
	static {
		
		DirUtil.guaranteeDirectory(graphDir.toString());
		DirUtil.guaranteeDirectory(testDir.toString());
		DirUtil.guaranteeDirectory(historyDir.toString());
	}

	public static void main(String[] args) throws Exception {
		TextUi.println(Version.versionString());
		final List<String> menu = new ArrayList<>();
		menu.add(MENU_RUN_SCENARIOS);
		menu.add(MENU_SIMPLE_RUN_VISUAL);

		String result = TextUi.menu(menu, MENU_RUN_SCENARIOS, "Choose:");
		
		if (result == MENU_RUN_SCENARIOS) {
			Main.doSimpleRun(false, "CSSM_Scen1");
		} else if (result == MENU_SIMPLE_RUN_VISUAL) {
			Main.doSimpleRun(false, null);
		}

		Toolkit.getDefaultToolkit().beep();
		TextUi.println("Done, exiting");
		System.exit(0);

	}

	private static void doSimpleRun(boolean visual, String precanned)
			throws InstantiationException, IllegalAccessException {
		final SimulationInput sip = Main.createDefaultSimulationInput();

		sip.setParameter(DIR_HISTORY,
				new File(historyDir.toString()).toString());
		if (precanned == null) {
			setFileConstants(testDir, precanned, sip);
		} else {

			setFileConstants(inputDir, precanned, sip);
		}

		final GiveWayContext context = new GiveWayContext();
		if (visual) {
			SimulationControlGui
					.simulate(sip, GiveWaySimulation.class, context);

		} else {
			Simulation.simulate(sip, GiveWaySimulation.class, context);
		}

		String plot = "";
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		Scenario scenario = context.scenarioSet.findScenario("one");
		ResultSet resultSet = new ResultSet(sip.getParameterString(DIR_HISTORY));
		

		// Plot the politeness of both agents
		List<plotSpecCSSM> toplot = new ArrayList<>();
		cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, POLITENESS,
				ACTOR_AMERICAN, ACTOR_AMERICAN, ACTOR_AMERICAN);
		svps = new plotSpecCSSM("Politeness-American", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, POLITENESS,
				ACTOR_INDIAN, ACTOR_INDIAN, ACTOR_INDIAN);
		svps = new plotSpecCSSM("Politeness-Indian", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, POLITENESS,
				ACTOR_AMERICAN, Actors.CROWD, ACTOR_AMERICAN);
		svps = new plotSpecCSSM("Public-Politeness-American", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, POLITENESS,
				ACTOR_INDIAN, Actors.CROWD, ACTOR_INDIAN);
		svps = new plotSpecCSSM("Public-Politeness-Indian", cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);
		
		FileWritingUtil.writeToTextFile(new File(graphDir, "politeness_"
				+ precanned + ".m"), plot);

		
		
		//Plot wimpiness of both agents
		toplot = new ArrayList<>();
		cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, WIMPINESS,
				ACTOR_AMERICAN, ACTOR_AMERICAN, ACTOR_AMERICAN);
		svps = new plotSpecCSSM("Politeness-American", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, WIMPINESS,
				ACTOR_INDIAN, ACTOR_INDIAN, ACTOR_INDIAN);
		svps = new plotSpecCSSM("Politeness-Indian", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_AMERICAN, WIMPINESS,
				ACTOR_AMERICAN, Actors.CROWD, ACTOR_AMERICAN);
		svps = new plotSpecCSSM("Public-Politeness-American", cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario, CULTURE_INDIAN, WIMPINESS,
				ACTOR_INDIAN, Actors.CROWD, ACTOR_INDIAN);
		svps = new plotSpecCSSM("Public-Politeness-Indian", cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);
		
		FileWritingUtil.writeToTextFile(new File(graphDir, "wimpiness_"
				+ precanned + ".m"), plot);
	}

	private static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(GiveWayContext.class);
		model.setSimulationClass(GiveWaySimulation.class);
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

}
