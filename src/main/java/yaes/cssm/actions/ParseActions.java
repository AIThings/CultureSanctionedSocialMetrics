package yaes.cssm.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.ui.text.TextUi;

/**
 * Class for reading and writing precanned scenarios from a file. 
 * This function allows us to read a series of actions from a text file.
 * The actions can be part of multiple scenarios instances, identified by their
 * scenario instance identifiers
 * 
 * Each line of the file corresponds to one action in the form:
 * 
 * scenarioInstanceName actionType [actionParameters]*
 *
 * Lines starting with % or // are considered comments.
 * 
 * @author Taranjeet
 */
public class ParseActions {

	/**
	 * The set of scenarios within which the actions are to be interpreted
	 */
	private ScenarioSet scenarios;
	// private int lineNumber = 0;
	private FileReader fr;
	private LineNumberReader in;
	private File file = null;

	/**
	 * Simple utility function, to get the list of actions (or an empty list) in
	 * one shot
	 * 
	 * @param actionFile
	 * @return
	 */
	public static List<Action> getActionList(String actionFile,
			ScenarioSet scenarios) {
		List<Action> retval = null;
		try {
			if (!actionFile.isEmpty()) {
				ParseActions pa = new ParseActions(actionFile, scenarios);
				retval = pa.parsePrecanned();
			} else {
				retval = new ArrayList<>();
			}
		} catch (IOException ioex) {
			TextUi.println(ioex);
		}
		return retval;
	}

	public ParseActions(String fileName, ScenarioSet scenarios)
			throws FileNotFoundException {
		this.scenarios = scenarios;
		file = new File(fileName);
		fr = new FileReader(file);
		in = new LineNumberReader(fr);
	}

	/**
	 * Returns the next line, ignores comments
	 * 
	 * @return
	 * @throws IOException
	 */
	public String nextLine() throws IOException {
		while (true) {
			String statement = in.readLine();
			if (statement == null) {
				return null;
			}
			statement = statement.trim();
			if (statement.startsWith("//") || statement.startsWith("%")) {
				continue;
			}
			return statement;
		}
	}

	/**
	 * Creates a list of precanned actions from a text file
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Action> parsePrecanned() throws IOException {
		List<Action> retval = new ArrayList<>();
		while (true) {
			String nextLine = nextLine();
			if (nextLine == null) {
				return retval;
			}
			Action sa = parseLine(nextLine);
			retval.add(sa);
		}
	}

	/**
	 * Parses one line corresponding to one action and returns the specific 
	 * action
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private Action parseLine(String line) {
		StringTokenizer st = new StringTokenizer(line);
		int c = st.countTokens();
		String scenarioLabel = st.nextToken();
		String actor = st.nextToken();
		String actionType = st.nextToken();
		Scenario scenario = scenarios.findScenario(scenarioLabel);
		if (scenario == null) {
			TextUi.errorPrint("Could not find scenario with label:" + scenarioLabel);
			System.exit(1);
		}
		ActionTypes actionTypes = scenario.getActionTypes();
		ActionType actionTypeInfo = actionTypes.getActionType(actionType);
		List<Double> detailValues = new ArrayList<Double>();
		for (String s : actionTypeInfo.getParameters()) {
			if (!st.hasMoreTokens()) {
				throw new Error("Line: " + in.getLineNumber() + " Not enough detail parameters passed action:"
						+ actionTypeInfo);
			}
			String sparam = st.nextToken();
			double param = Double.parseDouble(sparam);
			detailValues.add(param);
		}
		
		double[] para = new double[detailValues.size()];
		for (int i = 0; i < para.length; i++) {
			para[i] = detailValues.get(i);
		}
		
		return new Action(scenarioLabel, actor, actionTypeInfo, para);
		
	}

}
