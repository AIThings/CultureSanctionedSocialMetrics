package yaes.cssm.scenarios.marketplace;

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
import yaes.cssm.scenarios.SocialCalculusSimulation;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.cssm.scenarios.spanishsteps.Plots;
import yaes.cssm.util.plot.GenerateValuePlot;
import yaes.cssm.util.plot.ResultSet;
import yaes.cssm.util.plot.plotSpecCB;
import yaes.cssm.util.plot.plotSpecCSSM;
import yaes.cssm.util.webgui.httpserver.WebGui;
import yaes.framework.simulation.Simulation;
import yaes.framework.simulation.SimulationInput;
import yaes.ui.format.IDetailLevel;
import yaes.ui.simulationcontrol.SimulationControlGui;
import yaes.ui.text.TextUi;
import yaes.util.DirUtil;
import yaes.util.FileWritingUtil;

/**
 * 
 * @author Saad Khan Marketplace main file.
 */
public class Main implements Constants, IDetailLevel {
	// private static final int TRAINING_SEED = 100; // this is cheating...
	// private static final int TEST_SEED = 100;
	private static final String MENU_RUN_MANUALLY = "Run scenario manually";
	private static final String MENU_RUN_SCRIPT = "Run scenario from script";
	private static final String MENU_LEARNING = "Learning from random clients";
	private static final String MENU_ALL_SCRIPTS = "Run all the scripts for the marketplace scenario";

//	private static final String SCENARIO1 = "Scenario1: The soldiers are very strict";
//	private static final String SCENARIO2 = "Scenario2: The soldiers are very friendly";
//	private static final String SCENARIO3 = "Scenario3: The soldiers have abrupt change in behavior on high alert days";
//	private static final String SCENARIO4 = "Scenario4: The soldiers have friendly persuasive nature";
//	private static final String SCENARIO5 = "Scenario5: The soldiers take help of robot for unpleasant action";

	public static final File inputDir = new File("scenarios/marketplace");
	public static final File graphDir = new File("data/cssm-marketplace-graphs");
	public static final File testDir = new File("data/cssm-marketplace-test");
	public static final File outputDir = new File("data/cssm-marketplace-output");
	public static final File historyDir = new File("data/cssm-marketplace-log");
	public static final File weightsDir = new File(
			"data/cssm-marketplace-weights");

	public static void flushFolders() {
		try {
			//delete(inputDir);
			delete(graphDir);
			delete(testDir);
			delete(outputDir);
			delete(historyDir);
			//delete(weightsDir);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//inputDir.mkdirs();
		graphDir.mkdirs();;
		testDir.mkdirs();;
		outputDir.mkdirs();;
		historyDir.mkdirs();;
		//weightsDir.mkdirs();;
	}
	
	static {
		DirUtil.guaranteeDirectory(graphDir.toString());
		DirUtil.guaranteeDirectory(inputDir.toString());
		DirUtil.guaranteeDirectory(testDir.toString());
		DirUtil.guaranteeDirectory(historyDir.toString());
		DirUtil.guaranteeDirectory(outputDir.toString());
		DirUtil.guaranteeDirectory(weightsDir.toString());
	}

	public static void main(String[] args) throws Exception {
		TextUi.println(Version.versionString());
		SimulationInput model = Main.createDefaultSimulationInput();

		final List<String> menu = new ArrayList<>();
		menu.add(MENU_LEARNING);
		menu.add(Main.MENU_RUN_MANUALLY);
		menu.add(Main.MENU_RUN_SCRIPT);
		menu.add(Main.MENU_ALL_SCRIPTS);
		String result = Main.MENU_ALL_SCRIPTS;
		// String result = null;
		if (result == null) {
			result = TextUi.menu(menu, MENU_LEARNING, "Choose:");
		}
		// String result = Main.MENU_RUN_SCENARIOS;
		// String result = Main.MENU_SIMPLE_RUN_VISUAL;
		if (result.equals(Main.MENU_LEARNING)) {
			//doLearningWithRandomClients();
		}
		if (result.equals(Main.MENU_RUN_MANUALLY)) {
			//Main.doRunSingleScenario(model, false, null);
		}
		if (result.equals(Main.MENU_RUN_SCRIPT)) {
			File fileScript = TextUi.inputFileWithNavigation(
					"scenarios/spanishsteps", "*.sspc");
			//Main.doRunSingleScenario(model, false, fileScript.toString());
		}
		if (result.equals(Main.MENU_ALL_SCRIPTS)) {
			List<String> scripts = new ArrayList<>();
			scripts.add("ScenarioTest");
		//	scripts.add("Scenario1");
		//	scripts.add("Scenario2");
		//	scripts.add("Scenario3");
		//	scripts.add("Scenario4");
			for (String script : scripts) {
				Main.doRunSingleScenario(model, false, script);
			}
		}
		Toolkit.getDefaultToolkit().beep();
		TextUi.println("Done, exiting");
		System.exit(0);
	}

	/**
	 * Creates the default simulation input
	 * 
	 * @return
	 */
	public static SimulationInput createDefaultSimulationInput() {
		SimulationInput model = new SimulationInput();
		model.setContextClass(MarketPlaceContext.class);
		model.setSimulationClass(MarketPlaceSimulation.class);
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

		MarketPlaceContext context = new MarketPlaceContext();
		
		if (visual) {
			SimulationControlGui.simulate(sip, MarketPlaceSimulation.class,
					context);
		} else {
			Simulation.simulate(sip, MarketPlaceSimulation.class, context);
		}

		// plot the social values of the sergeant
		String plot = "";
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		Scenario scenario1 = context.scenarioSet.findScenario("one");
		Scenario scenario2 = context.scenarioSet.findScenario("two");
		Scenario scenario3 = context.scenarioSet.findScenario("three");

		ResultSet resultSet = new ResultSet(sip.getParameterString(DIR_HISTORY));

		// plot the social values of the sergeant

		List<plotSpecCSSM> toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, SECURITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(SECURITY, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, DIGNITY,
				ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, Actors.CROWD, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, POLITENESS,
				ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "SergeantBob_"
				+ precanned + ".m"), plot);

		// plot the social values of the kebab seller
		toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, DIGNITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, Actors.CROWD, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario1, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "VendorHassan_"
				+ precanned + ".m"), plot);

		// plot the social values of the kebab seller
		toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario2, CULTURE_EASTERN, DIGNITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario2, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, Actors.CROWD, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario2, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "VendorAslam_"
				+ precanned + ".m"), plot);

		// plot the social values of the kebab seller
		toplot = new ArrayList<plotSpecCSSM>();
		cssm = CSSM.createCSSM(scenario3, CULTURE_EASTERN, DIGNITY,
				ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
		svps = new plotSpecCSSM(DIGNITY, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario3, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, Actors.CROWD, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PUBLIC, cssm);
		toplot.add(svps);

		cssm = CSSM.createCSSM(scenario3, CULTURE_EASTERN, POLITENESS,
				ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR);
		svps = new plotSpecCSSM(POLITENESS_PEER, cssm);
		toplot.add(svps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "VendorAli_"
				+ precanned + ".m"), plot);

		// plot the public
		List<plotSpecCB> toplotCB = new ArrayList<>();
		ConcreteBelief cb = null;
		plotSpecCB sbps = null;
		
		cb = ConcreteBelief.createCB(scenario1, IS_A_FRIEND,
				ACTOR_VENDOR, ACTOR_VENDOR);
		sbps = new plotSpecCB("CB(S1,IsAFriend,Vendor,Vendor)", cb);
		toplotCB.add(sbps);
		
		cb = ConcreteBelief.createCB(scenario2, IS_A_FRIEND,
				ACTOR_VENDOR, ACTOR_VENDOR);
		sbps = new plotSpecCB("CB(S2,IsAFriend,Vendor,Vendor)", cb);
		toplotCB.add(sbps);
		
		cb = ConcreteBelief.createCB(scenario3, IS_A_FRIEND,
				ACTOR_VENDOR, ACTOR_VENDOR);
		sbps = new plotSpecCB("CB(S3,IsAFriend,Vendor,Vendor)", cb);
		toplotCB.add(sbps);
		
		

		cb = ConcreteBelief.createCB(scenario1, POSES_A_THREAT,
				ACTOR_SERGEANT, ACTOR_SERGEANT);
		sbps = new plotSpecCB("CB(S1,PosesAThreat,Sergeant,Sergeant)", cb);
		toplotCB.add(sbps);
		
		cb = ConcreteBelief.createCB(scenario2, POSES_A_THREAT,
				ACTOR_SERGEANT, ACTOR_SERGEANT);
		sbps = new plotSpecCB("CB(S2,PosesAThreat,Sergeant,Sergeant)", cb);
		toplotCB.add(sbps);
		
		cb = ConcreteBelief.createCB(scenario3, POSES_A_THREAT,
				ACTOR_SERGEANT, ACTOR_SERGEANT);
		sbps = new plotSpecCB("CB(S3,PosesAThreat,Sergeant,Sergeant)", cb);
		toplotCB.add(sbps);

		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);

		FileWritingUtil.writeToTextFile(new File(graphDir, "perception_"
				+ precanned + ".m"), plot);

	}
	
	public static void doRunSingleScenario(SimulationInput model,
			boolean useWebGui, String scriptFileName)
			throws InstantiationException, IllegalAccessException, IOException {
		final SimulationInput sip = Main.createDefaultSimulationInput();
		// put the log files in the local history dir
		File localHistoryDir = new File(historyDir,scriptFileName);
		DirUtil.guaranteeDirectory(localHistoryDir.toString());

		sip.setParameter(DIR_HISTORY, localHistoryDir.toString());
		sip.setParameter(DIR_WEIGHTS, weightsDir.toString());

		if (scriptFileName == null) {
			setFileConstants(testDir, scriptFileName, sip);
		} else {
			TextUi.printHeader("Running script:" + scriptFileName);
			setFileConstants(inputDir, scriptFileName, sip);
		}

		MarketPlaceContext context = new MarketPlaceContext();
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

	// public static void generatePlots(String precanned,
	// MarketPlaceContext context) throws IOException {
	// String plot = "";
	//
	// // plot the social values of the sergeant
	//
	// List<SVPlotSpec> toplot = new ArrayList<SVPlotSpec>();
	//
	// // toplot.add(new SVPlotSpec("SergeantBob", SECURITY, new CSSM(
	// // MARKET_PLACE, SECURITY, SERGEANT, SERGEANT, SERGEANT, 0, 100,
	// // 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", DIGNITY,
	// new CSSM(MARKET_PLACE, DIGNITY, SERGEANT, SERGEANT, SERGEANT,
	// 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", POLITENESS_PUBLIC,
	// new CSSM(MARKET_PLACE, POLITENESS, SERGEANT, ACTOR_CROWD, SERGEANT,
	// 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("SergeantBob", POLITENESS_PEER, new CSSM(
	// MARKET_PLACE, POLITENESS, SERGEANT, VENDOR, SERGEANT, 0, 100,
	// 50)));
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "Sergeant"
	// + precanned + ".m"), plot);
	//
	// // plot the social values of the kebab seller
	// toplot.add(new SVPlotSpec("VendorHassan", DIGNITY, new CSSM(
	// MARKET_PLACE, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorHassan", POLITENESS_PUBLIC, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, ACTOR_CROWD, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorHassan", POLITENESS_PEER, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, SERGEANT, VENDOR, 0, 100, 50)));
	//
	// // plot the social values of the kebab seller
	// toplot.add(new SVPlotSpec("VendorAslam", DIGNITY, new CSSM(
	// MARKET_PLACE, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorAslam", POLITENESS_PUBLIC, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, ACTOR_CROWD, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorAslam", POLITENESS_PEER, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, SERGEANT, VENDOR, 0, 100, 50)));
	//
	// // plot the social values of the kebab seller
	// toplot.add(new SVPlotSpec("VendorAli", DIGNITY, new CSSM(MARKET_PLACE,
	// DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorAli", POLITENESS_PUBLIC, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, ACTOR_CROWD, VENDOR, 0, 100, 50)));
	//
	// toplot.add(new SVPlotSpec("VendorAli", POLITENESS_PEER, new CSSM(
	// MARKET_PLACE, POLITENESS, VENDOR, SERGEANT, VENDOR, 0, 100, 50)));
	//
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "client_"
	// + precanned + ".m"), plot);
	//
	// // plot the public
	// List<SBPlotSpec> toplotCB = new ArrayList<>();
	// toplotCB.add(new SBPlotSpec("VendorHassan", IS_A_FRIEND,
	// new ConcreteBelief(MARKET_PLACE, IS_A_FRIEND, VENDOR, VENDOR,
	// 0, 1.0, 0)));
	// toplotCB.add(new SBPlotSpec("VendorAslam", IS_A_FRIEND,
	// new ConcreteBelief(MARKET_PLACE, IS_A_FRIEND, VENDOR, VENDOR,
	// 0, 1.0, 0)));
	// toplotCB.add(new SBPlotSpec("VendorAli", IS_A_FRIEND,
	// new ConcreteBelief(MARKET_PLACE, IS_A_FRIEND, VENDOR, VENDOR,
	// 0, 1.0, 0)));
	//
	// toplotCB.add(new SBPlotSpec("SergeantBob", POSES_A_THREAT,
	// new ConcreteBelief(MARKET_PLACE, POSES_A_THREAT, SERGEANT,
	// SERGEANT, 0, 1.0, 0)));
	//
	// plot = GenerateValuePlot.generateSocialValuesPlot(context, toplot);
	//
	// FileWritingUtil.writeToTextFile(new File(graphDir, "perception_"
	// + precanned + ".m"), plot);
	//
	// }
	
	public static void delete(File file) throws IOException {

		if (file.exists()) {

			if (file.isDirectory()) {

				// directory is empty, then delete it
				if (file.list().length == 0) {

					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());

				} else {

					// list all the directory contents
					String files[] = file.list();

					for (String temp : files) {
						// construct the file structure
						File fileDelete = new File(file, temp);

						// recursive delete
						delete(fileDelete);
					}

					// check the directory again, if empty then delete it
					if (file.list().length == 0) {
						file.delete();
						System.out.println("Directory is deleted : "
								+ file.getAbsolutePath());
					}
				}

			} else {
				// if file, then delete it
				file.delete();
				System.out.println("File is deleted : "
						+ file.getAbsolutePath());
			}

		}
	}

}
