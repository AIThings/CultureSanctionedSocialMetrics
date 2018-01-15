package yaes.cssm.scenarios.spanishsteps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.util.plot.GenerateValuePlot;
import yaes.cssm.util.plot.ResultSet;
import yaes.cssm.util.plot.plotSpecCB;
import yaes.cssm.util.plot.plotSpecCSSM;
import yaes.util.FileWritingUtil;

/**
 * This class creates the plots for the Spanish Steps scenario. The assumption
 * is that the scenario had been run and we now have a directory based on which
 * the result set can be created.
 * 
 * There are a number of standard plots which make sense, so we encapsulate them
 * here.
 * 
 * @author lboloni
 * 
 */
public class Plots implements Constants {

	public static List<String> linestylesBlack = null;
	public static List<String> linestylesGray = null;

	/**
	 * Initializes a group of linestyles of thick lines
	 */
	private static void initLineStyles() {
		linestylesBlack = new ArrayList<>();
		linestylesBlack.add("'LineStyle','-','LineWidth',3,'Color',[0 0 0]");
		linestylesBlack.add("'LineStyle',':','LineWidth',3,'Color',[0 0 0]");
		linestylesBlack.add("'LineStyle','--','LineWidth',3,'Color',[0 0 0]");
		linestylesBlack.add("'LineStyle','-','LineWidth',2,'Color',[0 0 0]");
		linestylesBlack.add("'LineStyle',':','LineWidth',2,'Color',[0 0 0]");
		linestylesBlack.add("'LineStyle','--','LineWidth',2,'Color',[0 0 0]");
		linestylesGray = new ArrayList<>();
		linestylesGray
				.add("'LineStyle','-','LineWidth',3,'Color',[0.5 0.5 0.5]");
		linestylesGray
				.add("'LineStyle',':','LineWidth',3,'Color',[0.5 0.5 0.5]");
		linestylesGray
				.add("'LineStyle','--','LineWidth',3,'Color',[0.5 0.5 0.5]");
		linestylesGray
				.add("'LineStyle','-','LineWidth',2,'Color',[0.5 0.5 0.5]");
		linestylesGray
				.add("'LineStyle',':','LineWidth',2,'Color',[0.5 0.5 0.5]");
		linestylesGray
				.add("'LineStyle','--','LineWidth',2,'Color',[0.5 0.5 0.5]");
	}

	/**
	 * Generates all the plots which normally characterize the Spanish Steps
	 * scenario
	 * 
	 * @param scriptFileName
	 * @param historyDir
	 * @param scenario
	 */
	public static void generateAllPlots(String scriptFileName,
			String historyDir, Scenario scenario) {
		initLineStyles();
		ResultSet resultSet = new ResultSet(historyDir);
		List<plotSpecCSSM> toplot = null;
		boolean spouse = false;
		//
		// Generate the plot for client politeness and dignity
		//
		toplot = new ArrayList<>();
		clientPoliteness(toplot, scenario, spouse, linestylesBlack);
		clientDignity(toplot, scenario, spouse, linestylesGray);
		String plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);
		FileWritingUtil.writeToTextFile(new File(Main.graphDir,
				"client_politeness_dignity_" + scriptFileName + ".m"), plot);
		//
		// Generate the plot for seller politeness and dignity
		//
		toplot = new ArrayList<>();
		sellerPoliteness(toplot, scenario, false, linestylesBlack);
		sellerDignity(toplot, scenario, false, linestylesGray);
		plot = GenerateValuePlot.generatePlotCSSM(resultSet, toplot);
		FileWritingUtil.writeToTextFile(new File(Main.graphDir,
				"seller_politeness_dignity_" + scriptFileName + ".m"), plot);
		//
		// Generate the plot for CBs
		//
		List<plotSpecCB> toplotCB = new ArrayList<>();
		beliefs(toplotCB, scenario, linestylesBlack);
		plot = GenerateValuePlot.generatePlotCB(resultSet, toplotCB);
		FileWritingUtil.writeToTextFile(new File(Main.graphDir, "cb_"
				+ scriptFileName + ".m"), plot);
	}

	/**
	 * Adds the plot spec lines for the CBs
	 * 
	 * @param toplotCB
	 * @param scenario
	 */
	private static void beliefs(List<plotSpecCB> toplotCB, Scenario scenario,
			List<String> lineStyles) {
		ConcreteBelief cb = null;
		plotSpecCB sbps = null;
		int count = 0;
		//
		//  Q-Gift - estimated by the client and seller - should be the same
		//
		cb = ConcreteBelief.createCB(scenario, Q_GIFT,
				Actors.CLIENT, Actors.CLIENT);
		sbps = new plotSpecCB("CB(S,Q-Gift,Client,Client)", cb);
		sbps.setLineStyle(lineStyles.get(count++));
		toplotCB.add(sbps);
		//
		//  Q-Agreed - of the crowd, estimated by the client and seller - should be the same
		//
		cb = ConcreteBelief.createCB(scenario, Q_AGREED,
				Actors.CROWD, Actors.CLIENT);
		sbps = new plotSpecCB("CB(S,Q-Agreed,Crowd,Client)", cb);
		sbps.setLineStyle(lineStyles.get(count++));
		toplotCB.add(sbps);
	}

	/**
	 * Adds to the plot spec lines for seller dignity (crowd perspective)
	 * 
	 * @param self
	 *            - if true, adds the self perspective as well
	 * 
	 */
	private static void sellerDignity(List<plotSpecCSSM> toplot,
			Scenario scenario, boolean self, List<String> lineStyles) {
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		int count = 0;
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.SELLER, Actors.CROWD, Actors.SELLER);
		svps = new plotSpecCSSM("CSSM(Western,Dignity,Seller,Crowd,Seller)",
				cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		if (self) {
			cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
					Actors.SELLER, Actors.SELLER, Actors.SELLER);
			svps = new plotSpecCSSM(
					"CSSM(Western,Dignity,Seller,Seller,Seller)", cssm);
			svps.setLineStyle(lineStyles.get(count++));
			toplot.add(svps);
		}
	}

	/**
	 * Adds the values of seller politeness (crowd perspective)
	 * 
	 * @param self
	 *            - if true, adds the self perspective as well
	 * 
	 */
	private static void sellerPoliteness(List<plotSpecCSSM> toplot,
			Scenario scenario, boolean self, List<String> lineStyles) {
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		int count = 0;
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.SELLER, Actors.CROWD, Actors.SELLER);
		svps = new plotSpecCSSM("CSSM(Western,Politeness,Seller,Crowd,Seller)",
				cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		if (self) {
			cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
					Actors.SELLER, Actors.SELLER, Actors.SELLER);
			svps = new plotSpecCSSM(
					"CSSM(Western,Politeness,Seller,Seller,Seller)", cssm);
			svps.setLineStyle(lineStyles.get(count++));
			toplot.add(svps);
		}
	}

	/**
	 * Adds the values of client politeness (client and crowd perspective)
	 * 
	 * @param spouse
	 *            - if true, adds the spouse's graph as well
	 * 
	 */
	private static void clientDignity(List<plotSpecCSSM> toplot,
			Scenario scenario, boolean spouse, List<String> lineStyles) {
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		int count = 0;
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		svps = new plotSpecCSSM("CSSM(Western,Dignity,Client,Client,Client)",
				cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		svps = new plotSpecCSSM("CSSM(Western,Dignity,Client,Crowd,Client)",
				cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		if (spouse) {
			cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY,
					Actors.CLIENT, Actors.SPOUSE, Actors.CLIENT);
			svps = new plotSpecCSSM(
					"CSSM(Western,Dignity,Client,Spouse,Client)", cssm);
			svps.setLineStyle(lineStyles.get(count++));
			toplot.add(svps);
		}
	}

	/**
	 * Adds the values of client dignity (client and crowd perspective)
	 * 
	 * @param spouse
	 *            - if true, adds the spouse's graph as well
	 */
	private static void clientPoliteness(List<plotSpecCSSM> toplot,
			Scenario scenario, boolean spouse, List<String> lineStyles) {
		CSSM cssm = null;
		plotSpecCSSM svps = null;
		int count = 0;
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		svps = new plotSpecCSSM(
				"CSSM(Western,Politeness,Client,Client,Client)", cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
				Actors.CLIENT, Actors.CROWD, Actors.CLIENT);
		svps = new plotSpecCSSM("CSSM(Western,Politeness,Client,Crowd,Client)",
				cssm);
		svps.setLineStyle(lineStyles.get(count++));
		toplot.add(svps);
		if (spouse) {
			cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, POLITENESS,
					Actors.CLIENT, Actors.SPOUSE, Actors.CLIENT);
			svps = new plotSpecCSSM(
					"CSSM(Western,Politeness,Client,Spouse,Client)", cssm);
			svps.setLineStyle(lineStyles.get(count++));
			toplot.add(svps);
		}
	}

}
