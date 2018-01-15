package yaes.cssm.util.plot;

import java.text.DecimalFormat;
import java.util.List;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import yaes.cssm.actions.Action;
import yaes.ui.plot.AbstractGraphDescription;
import yaes.ui.text.TextUi;

/**
 * Generating plots of CSSMs and CBs
 * 
 * @author Lotzi Boloni
 * 
 */
public class GenerateValuePlot {

	/**
	 * If set to true, the values to be used in the plot will be the absolute
	 * values. Otherwise, the range adapted values (to 0)
	 */
	public static boolean absolute = true;

	/**
	 * Generates a plot of the evolution of the CSSM values in sceneario
	 * instance
	 * 
	 * 
	 * @param resultSet
	 *            - the result set of the scenario
	 * @param specs
	 *            - the plots to be plotted
	 * @return
	 */
	public static String generatePlotCSSM(ResultSet resultSet,
			List<plotSpecCSSM> specs) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("figure1 = figure ;\n");
		// the number of steps == the time point when we are with the context
		int steps = resultSet.getMaxTime();
		StringBuffer time = new StringBuffer();
		time.append("time = [");
		StringBuffer ticks = new StringBuffer();
		ticks.append("[");
		StringBuffer ticklabels = new StringBuffer();
		ticklabels.append("{");
		// StringBuffer labels = new StringBuffer();
		// labels.append("labels = [");
		StringBuffer values[] = new StringBuffer[specs.size()];
		for (int i = 0; i != specs.size(); i++) {
			values[i] = new StringBuffer();
			values[i].append("values" + i + " = [");
		}
		
		//
		// iterating over the time steps, prepares the variables for the values
		// the tick values and the tick label, which will be the action performed at the
		// given time step
		//
		for (int i = 0; i <= steps; i++) {
			time.append(i + " ");
			// if (i % 5 == 0) {
			ticks.append(i + " ");
			// }

			for (int j = 0; j != specs.size(); j++) {
				plotSpecCSSM spec = specs.get(j);
				double value = resultSet.getCssmValue(i, spec.getCssm());
				if (absolute) {
					values[j].append(value + " ");
				} else {
					// reduce the 0..100 range from Spanish Steps
					value = value / 100;
					values[j].append(value + " ");
				}
			}
			//  Generate the tick labels
			if (i == -1) {
				ticklabels.append("'init' ");
			} else {
				String label = createTickLabelForAction(resultSet, i);
				ticklabels.append("'" + label + "' ");
			}
		}

		// buffer.append(labels + "] ; ;\n");
		buffer.append(time + "] ;\n");
		ticks.append("]");
		ticklabels.append("}");
		// FIXME: it was ticks instead of tick labels
		buffer.append("axes1= axes('Parent', figure1," + "'XTick', " + ticks
				+ ", 'XTickLabel', " + ticklabels + ") ;\n");
		// buffer.append("axes1= axes('Parent', figure1) ;\n");
		// no X label is needed, anyhow the values rotated values will mess it uo
		// buffer.append("xlabel(axes1, 'Action sequence') ;\n");
		buffer.append("ylabel(axes1,'CSSM value') ;\n");
		buffer.append("ylim(axes1,[0 1]);\n");
		buffer.append("box(axes1,'on') ;\n");
		buffer.append("hold(axes1,'all') ;\n");
		//
		//  Performs the plots, using the getLineStyle values from the AbstractGraphDescription
		//
		for (int i = 0; i != specs.size(); i++) {
			plotSpecCSSM spec = specs.get(i);
			buffer.append(values[i] + "] ;\n");
			String linestyle = null;
			if (spec.getLineStyle() == null) {
				linestyle = AbstractGraphDescription.getLineStyle(i); 
			} else {
				linestyle = "," + spec.getLineStyle();
			}
			buffer.append("plot(time, values" + i
					+ linestyle
					+ ", 'DisplayName','" + spec.getValueLabel()
					+ "', 'Parent', axes1) ;\n");
			buffer.append("hold on ;\n");
		}
		// buffer.append("title('"+ title+"') ;\n");
		buffer.append("legend(axes1, 'show') ;\n");
		// rotates the tick labels 45 degree
		buffer.append("rotateticklabel(gca,45)");
		return buffer.toString();
	}

	/**
	 * Generates the tick label for an action from the resultset
	 * @param resultSet
	 * @param i
	 * @return
	 */
	private static String createTickLabelForAction(ResultSet resultSet, int i) {
		String label;
		// the action label, replace the alpha with \alpha to get a greek letter in Matlab
		Action action = resultSet.getHistoryAction(i);
		String actionText = action.getActionType()
				.getActionType();
		actionText = actionText.replaceAll("alpha", "\\alpha");
		// the parameter label
		String parameterText = "";
		double params[] = action.getParameters();
		if (params.length > 0) {
			parameterText = "(";
			for(double val: params) {				
				parameterText += new DecimalFormat("0.##").format(val) +  ",";
				// parameterText += String.format("%.3f", val)+",";
			}
			// get rid of the last comma
			parameterText = parameterText.substring(0,parameterText.length()-1);
			parameterText += ")";
		}
		label = actionText + parameterText;
		return label;
	}

	/**
	 * Generates a matlab plot of the evolution of a CB in a scenario instance
	 * 
	 * @param resultSet
	 *            - the result of the scenario instance
	 * @param specs
	 *            - description of the CBs to be plotted
	 * @return
	 */
	public static String generatePlotCB(ResultSet resultSet,
			List<plotSpecCB> specs) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("figure1 = figure ;\n");
		// the number of steps == the time point when we are with the context
		int steps = resultSet.getMaxTime();
		StringBuffer time = new StringBuffer();
		time.append("time = [");
		StringBuffer ticks = new StringBuffer();
		ticks.append("[");
		StringBuffer ticklabels = new StringBuffer();
		ticklabels.append("{");
		// StringBuffer labels = new StringBuffer();
		// labels.append("labels = [");
		StringBuffer values[] = new StringBuffer[specs.size()];
		for (int i = 0; i != specs.size(); i++) {
			values[i] = new StringBuffer();
			values[i].append("values" + i + " = [");
		}

		for (int i = 0; i <= steps; i++) {
			time.append(i + " ");
			// if (i % 5 == 0) {
			ticks.append(i + " ");
			// }

			for (int j = 0; j != specs.size(); j++) {
				plotSpecCB spec = specs.get(j);
				if (absolute) {
					values[j].append(resultSet.getCbValue(i, spec.getCb())
							+ " ");
				} else {
					values[j].append(resultSet.getCbValue(i, spec.getCb())
							+ " ");
				}
			}
			//  Generate the tick labels
			if (i == -1) {
				ticklabels.append("'init' ");
			} else {
				String label = createTickLabelForAction(resultSet, i);
				ticklabels.append("'" + label + "' ");
			}
		}
		buffer.append(time + "] ;\n");
		ticks.append("]");
		ticklabels.append("}");
		buffer.append("axes1= axes('Parent', figure1," + "'XTick', " + ticks
				+ ", 'XTickLabel', " + ticklabels + ") ;\n");
		// buffer.append("axes1= axes('Parent', figure1) ;\n");
		// buffer.append("xlabel(axes1, 'Action sequence') ;\n");
		buffer.append("ylabel(axes1,'CB value') ;\n");
		buffer.append("ylim(axes1,[0 1]);\n");
		buffer.append("box(axes1,'on') ;\n");
		buffer.append("hold(axes1,'all') ;\n");
		for (int i = 0; i != specs.size(); i++) {
			plotSpecCB spec = specs.get(i);			
			buffer.append(values[i] + "] ;\n");
			String linestyle = null;
			if (spec.getLineStyle() == null) {
				linestyle = AbstractGraphDescription.getLineStyle(i); 
			} else {
				linestyle = "," + spec.getLineStyle();
			}
			buffer.append("plot(time, values" + i
					+ linestyle
					+ ", 'DisplayName','" + spec.getValueLabel()
					+ "', 'Parent', axes1) ;\n");
			buffer.append("hold on ;\n");
		}
		buffer.append("legend(axes1, 'show') ;\n");
		// rotates the tick labels 45 degree
		buffer.append("rotateticklabel(gca,45)");
		return buffer.toString();
	}

	/**
	 * Generate comparison plots between different XY series (used in AIF
	 * tuning)
	 * 
	 * @param collection
	 * @return
	 */
	public static String generateComparePlots(XYSeriesCollection collection) {
		List<XYSeries> series = collection.getSeries();
		TextUi.println(series.size());

		StringBuffer buffer = new StringBuffer();
		buffer.append("figure1 = figure ;\n");

		StringBuffer time = new StringBuffer();
		time.append("time = [");
		StringBuffer ticks = new StringBuffer();
		ticks.append("[");
		StringBuffer ticklabels = new StringBuffer();
		ticklabels.append("{");
		// StringBuffer labels = new StringBuffer();
		// labels.append("labels = [");
		StringBuffer values[] = new StringBuffer[series.size()];

		for (int i = 0; i != series.size(); i++) {
			values[i] = new StringBuffer();
			values[i].append("values" + i + " = [");
		}

		for (double i = 0; i < 100; i++) {
			time.append(i / 100 + " ");
			if (i % 10 == 0)
				ticks.append(i / 100 + " ");
		}

		for (int j = 0; j < series.size(); j++) {
			XYSeries xySeries = collection.getSeries(j);
			for (int i = 0; i < xySeries.getItemCount(); i++) {
				values[j].append(xySeries.getY(i) + " ");

			}

		}
		// buffer.append(labels + "] ; ;\n");
		buffer.append(time + "] ;\n");
		ticks.append("]");
		ticklabels.append("}");
		buffer.append("axes1= axes('Parent', figure1," + "'XTick', " + ticks
				+ ", 'XTickLabel', " + ticks + ") ;\n");
		// buffer.append("axes1= axes('Parent', figure1) ;\n");
		// buffer.append("xlabel(axes1, '') ;\n");
		// buffer.append("ylabel(axes1,'Value of belief') ;\n");
		buffer.append("box(axes1,'on') ;\n");
		buffer.append("hold(axes1,'all') ;\n");

		for (int i = 0; i < series.size(); i++) {
			buffer.append(values[i] + "] ;\n");
			buffer.append("plot(time, values" + i
					+ AbstractGraphDescription.getLineStyle(i)
					+ ", 'DisplayName','"
					+ collection.getSeries(i).getKey().toString()
					+ "', 'Parent', axes1) ;\n");
			buffer.append("hold on ;\n");
		}

		buffer.append("legend(axes1, 'show') ;\n");
		return buffer.toString();

	}
}
