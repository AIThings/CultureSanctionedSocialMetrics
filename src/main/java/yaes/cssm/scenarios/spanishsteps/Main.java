package yaes.cssm.scenarios.spanishsteps;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
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
import yaes.cssm.scenarios.SocialCalculusSimulation;
import yaes.cssm.util.webgui.httpserver.WebGui;
import yaes.framework.simulation.Simulation;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.parametersweep.ExperimentPackage;
import yaes.framework.simulation.parametersweep.ParameterSweep;
import yaes.framework.simulation.parametersweep.ParameterSweep.ParameterSweepType;
import yaes.framework.simulation.parametersweep.ParameterSweepHelper;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;
import yaes.util.DirUtil;

public class Main implements Constants, IDetailLevel {
	// private static final int TRAINING_SEED = 100; // this is cheating...
	// private static final int TEST_SEED = 100;
	private static final String MENU_RUN_MANUALLY = "Run scenario manually";
	private static final String MENU_RUN_SCRIPT = "Run scenario from script";
	private static final String MENU_LEARNING = "Learning from random clients";
	private static final String MENU_SMC_PAPER = "Run all the scripts for the 2014 IEEE SMC submission";

	// public static final File inputDir = new File("scenarios/spanishsteps");
	private static final File inputDir = new File(
			Main.class.getClassLoader().getResource("scenarios/spanishsteps").getFile());

	public static final File graphDir = new File("data/cssm-spanishsteps-graphs");
	public static final File testDir = new File("data/cssm-spanishsteps-test");
	public static final File outputDir = new File("data/cssm-spanishsteps-output");
	public static final File historyDir = new File("data/cssm-spanishsteps-log");
	public static final File weightsDir = new File("data/cssm-spanishsteps-weights");

	static {
		DirUtil.guaranteeDirectory(graphDir.toString());
		// DirUtil.guaranteeDirectory(inputDir.toString());
		DirUtil.guaranteeDirectory(testDir.toString());
		DirUtil.guaranteeDirectory(historyDir.toString());
		DirUtil.guaranteeDirectory(outputDir.toString());
		DirUtil.guaranteeDirectory(weightsDir.toString());
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TextUi.println(Version.versionString());
		SimulationInput model = Main.createDefaultSimulationInput();

		final List<String> menu = new ArrayList<String>();
		menu.add(MENU_LEARNING);
		menu.add(Main.MENU_RUN_MANUALLY);
		menu.add(Main.MENU_RUN_SCRIPT);
		menu.add(Main.MENU_SMC_PAPER);

		String result = null;
		if (result == null) {
			result = TextUi.menu(menu, MENU_LEARNING, "Choose:");
		}
		// String result = Main.MENU_RUN_SCENARIOS;
		// String result = Main.MENU_SIMPLE_RUN_VISUAL;
		// String result = Main.MENU_SMC_PAPER;
		if (result.equals(Main.MENU_LEARNING)) {
			doLearningWithRandomClients();
		}
		if (result.equals(Main.MENU_RUN_MANUALLY)) {
			Main.doRunSingleScenario(model, false, null);
		}
		if (result.equals(Main.MENU_RUN_SCRIPT)) {
			File fileScript = TextUi.inputFileWithNavigation("scenarios/spanishsteps", "*.sspc");
			Main.doRunSingleScenario(model, false, fileScript.toString());
		}
		if (result.equals(Main.MENU_SMC_PAPER)) {
			List<String> scripts = new ArrayList<String>();
			scripts.add("SuccessfulSell");
			scripts.add("SuccessfulSell_wwhh");
			scripts.add("UnsuccessfulSell");
			scripts.add("UnsuccessfulSell_wwhh");
			for (String script : scripts) {
				Main.doRunSingleScenario(model, false, script);
			}
		}
		Toolkit.getDefaultToolkit().beep();
		TextUi.println("Done, exiting");
		System.exit(0);
	}

	/**
	 * The learning process goes through: in each process the seller loads the
	 * weight from the previous process, learns some more against a random
	 * client and then saves the weights it had learned.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws FileNotFoundException
	 */
	private static void doLearningWithRandomClients() throws FileNotFoundException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException {

		SimulationInput model = Main.createDefaultSimulationInput();

		// for learning set the seed to -1, use iteration as seed
		model.setParameter(SpanishStepsContext.SPANISH_STEPS_RANDOM_CLIENT, -1);

		ExperimentPackage pack = new ExperimentPackage(outputDir, graphDir);
		
		setFileConstants(testDir, null, model);
		
		
		pack.setModel(model);
		
		ParameterSweep sweepLearning = ParameterSweepHelper.generateParameterSweepInteger("Iteration",
				SpanishStepsContext.SPANISH_STEPS_LEARNING_ITERATION, 0, 5);
		sweepLearning.setType(ParameterSweepType.Range);
		pack.addParameterSweep(sweepLearning);

//		ParameterSweep sweepRandom = ParameterSweepHelper.generateParameterSweepInteger("Label ",
//				SpanishStepsContext.SPANISH_STEPS_RANDOM_CLIENT, 0, 10);
//		sweepRandom.setType(ParameterSweepType.Repetition);
//		pack.addParameterSweep(sweepRandom);

		pack.cleanUp();
		pack.initialize();
		pack.run(true);

		SpanishStepsContext context = new SpanishStepsContext();
		for (String actor : context.agents.keySet()) {
			if (actor == "Hassan") {
				for (CSSM cssm : context.agents.get(actor).getCSSMs()) {
					pack.generateGraph(cssm.getPseudoName(), null, cssm.getPseudoName());
				}

				for (ConcreteBelief cb : context.agents.get(actor).getCBs()) {
					pack.generateGraph(cb.getPseudoName(), null, cb.getPseudoName());
				}
			}
		}
		// pack.setVariableDescription("DVCR","UA-DVCR");
		// pack.generateGraph(Metrics_AverageNeighborsCount, "weight_",
		// "average_neighbors_count");

	}

	/**
	 * Creates the default simulation input
	 * 
	 * @return
	 */
	public static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(SpanishStepsContext.class);
		model.setSimulationClass(SocialCalculusSimulation.class);
		model.setParameter(DIR_HISTORY, historyDir.toString());
		model.setParameter(DIR_WEIGHTS, weightsDir.toString());
		model.setStopTime(200);
		return model;
	}

	/**
	 * Sets the constants in the simulation input with regards to what
	 * pre-canned file to read and to which file to write the results.
	 * 
	 * 
	 * @param directory
	 * @param scriptFileName
	 * @param sip
	 */
	public static void setFileConstants(File directory, String scriptFileName, SimulationInput sip) {
		if (scriptFileName == null) {
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
			String s = df.format(now);
			sip.setParameter(ACTION_FILE_READ, "");
			sip.setParameter(ACTION_FILE_WRITE, new File(directory, "test" + s + ".sspc").toString());
		} else {
			sip.setParameter(ACTION_FILE_READ, new File(directory, scriptFileName + ".sspc").toString());
			sip.setParameter(ACTION_FILE_WRITE, "");
		}
	}

	/**
	 * Runs a single scenario (from a script, from a script followed by hand
	 * input or completely from a hand input).
	 * 
	 * Then, it generates graphs from the result of the simulation
	 * 
	 * @param model
	 *            - the initial values for the sip
	 * @param useWebGui
	 *            - in this case it means turn on the webgui
	 * @param scriptFileName
	 *            - the name of the file from which we will read the script, if
	 *            any
	 * 
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public static void doRunSingleScenario(SimulationInput model, boolean useWebGui, String scriptFileName)
			throws InstantiationException, IllegalAccessException, IOException {
		final SimulationInput sip = Main.createDefaultSimulationInput();
		// put the log files in the local history dir
		File localHistoryDir = new File(historyDir, scriptFileName);
		DirUtil.guaranteeDirectory(localHistoryDir.toString());
		sip.setParameter(DIR_HISTORY, localHistoryDir.toString());

		sip.setParameter(DIR_WEIGHTS, weightsDir.toString());
		// for learning set the seed to -1, use iteration as seed
		sip.setParameter(SpanishStepsContext.SPANISH_STEPS_RANDOM_CLIENT, -1);
		sip.setParameter(SpanishStepsContext.SPANISH_STEPS_LEARNING_ITERATION, 0);
		if (scriptFileName == null) {
			setFileConstants(testDir, scriptFileName, sip);
		} else {
			TextUi.printHeader("Running script:" + scriptFileName);
			setFileConstants(inputDir, scriptFileName, sip);
		}

		SpanishStepsContext context = new SpanishStepsContext();
		if (useWebGui) {
			WebGui.startWebGui(context);
		}

		// if (visual) {
		// SimulationControlGui.simulate(sip, SocialCalculusSimulation.class,
		// context);
		// } else {
		Simulation.simulate(sip, SocialCalculusSimulation.class, context);
		// }
		//
		// Simulation ended, generate plots
		//
		Scenario scenario = context.currentScenario;
		// the prefix which will be used in the generated name of the graphs -
		// normally the name of the script, otherwise "manual"
		String prefix = null;
		if (scriptFileName == null) {
			prefix = "manual";
		} else {
			File scriptFile = new File(scriptFileName);
			prefix = scriptFile.getName();
			prefix = prefix.replaceAll(".sspc", "");
		}
		Plots.generateAllPlots(prefix, localHistoryDir.toString(), scenario);
	}

}
