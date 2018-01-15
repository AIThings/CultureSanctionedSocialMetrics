package yaes.cssm.util.plot;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class JFreeChartPlot extends ApplicationFrame {

	/**
	 * A custom label generator.
	 */
	static class LabelGenerator extends AbstractCategoryItemLabelGenerator
			implements CategoryItemLabelGenerator {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2613446006912703038L;
		/**
		 * The index of the category on which to base the percentage
		 * 
		 * 
		 * (null = use series total).
		 */
		@SuppressWarnings("unused")
		private Integer category;
		/** A percent formatter. */
		//private NumberFormat formatter = NumberFormat.getPercentInstance();

		/**
		 * Creates a new label generator that displays the item value and a
		 * percentage relative to the value in the same series for the specified
		 * category.
		 * 
		 * @param category
		 *            the category index (zero-based).
		 */
		public LabelGenerator(int category) {
			this(new Integer(category));
		}

		/**
		 * Creates a new label generator that displays the item value and a
		 * percentage relative to the value in the same series for the specified
		 * category. If the category index is <code>null</code>, the total of
		 * all items in the series is used.
		 * 
		 * @param category
		 *            the category index (<code>null</code> permitted).
		 */
		public LabelGenerator(Integer category) {
			super("", NumberFormat.getInstance());
			this.category = category;
		}

		/**
		 * Calculates a series total.
		 * 
		 * @param dataset
		 *            the dataset.
		 * @param series
		 *            the series index.
		 * 
		 * @return The total.
		 */
		@SuppressWarnings("unused")
		private static double calculateSeriesTotal(CategoryDataset dataset, int series) {
			double result = 0.0;
			for (int i = 0; i < dataset.getColumnCount(); i++) {
				Number value = dataset.getValue(series, i);
				if (value != null) {
					result = result + value.doubleValue();
				}
			}
			return result;
		}

		/**
		 * Generates a label for the specified item. The label is typically a
		 * formatted version of the data value, but any text can be used.
		 * 
		 * @param dataset
		 *            the dataset (<code>null</code> not permitted).
		 * @param series
		 *            the series index (zero-based).
		 * @param category
		 *            the category index (zero-based).
		 * 
		 * @return the label (possibly <code>null</code>).
		 */
		@Override
		public String generateLabel(CategoryDataset dataset, int series,
				int category) {
			String result = null;
		/*	double base = 0.0;
			if (this.category != null) {
				final Number b = dataset.getValue(series,
						this.category.intValue());
				base = b.doubleValue();
			} else {
				base = calculateSeriesTotal(dataset, series);
			}*/
			Number value = dataset.getValue(series, category);
			if (value != null) {
				//final double v = value.doubleValue();
				// you could apply some formatting here
				// result = value.toString() + " (" + this.formatter.format(v /
				// base) + ")";
				result = value.toString();

			}
			return result;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -376865058059707487L;

	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(CategoryDataset dataset,
			String chartName) {
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart(chartName, // chart title
				"Actors", // domain axis label
				"Social Values", // range axis label
				dataset, // data
				PlotOrientation.HORIZONTAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		chart.setBackgroundPaint(Color.white);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.25);
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setBaseItemLabelsVisible(true);// setItemLabelsVisible(true);
		// use one or the other of the following lines to see the
		// different modes for the label generator...
		renderer.setItemLabelGenerator(new LabelGenerator(null));
		// renderer.setLabelGenerator(new LabelGenerator(0));
		return chart;
	}

	public static CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100.0, "S1", "C1");
		dataset.addValue(44.3, "S1", "C2");
		dataset.addValue(93.0, "S1", "C3");
		dataset.addValue(80.0, "S2", "C1");
		dataset.addValue(75.1, "S2", "C2");
		dataset.addValue(15.1, "S2", "C3");
		return dataset;
	}

	
	/**
	 * Creates the data set for a plot, by extracting it from a ResultSet
	 * @param socialScenario
	 * @param specs
	 * @return
	 */
	public static CategoryDataset createDataset(ResultSet socialScenario,
			List<plotSpecCSSM> specs) {
		boolean absolute = false;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int steps = (int) socialScenario.getMaxTime();
		double socialValue = 0;
		for (int j = 0; j != specs.size(); j++) {
			plotSpecCSSM spec = specs.get(j);
			if (absolute) {
				socialValue = socialScenario.getCssmValue(steps, spec.getCssm());
			} else {
				socialValue = socialScenario.getCssmValue(steps, spec.getCssm());
			}
			dataset.addValue(socialValue, spec.getValueLabel(),
					spec.getCssm().getEstimatorAgent());
		}
		return dataset;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFreeChartPlot sampleChart = new JFreeChartPlot(
				"Social Surface Value Vector");

		// sampleChart.createDataset(context, specs);
		sampleChart.pack();
		RefineryUtilities.centerFrameOnScreen(sampleChart);
		sampleChart.setVisible(true);

	}

	public JFreeChartPlot(String title) {
		super(title);
		CategoryDataset dataset = JFreeChartPlot.createDataset();
		JFreeChart chart = JFreeChartPlot.createChart(dataset, title);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1000, 1000));
		setContentPane(chartPanel);

	}

	public JFreeChartPlot(String title, ResultSet resultSet,
			List<plotSpecCSSM> specs) {
		super(title);
		CategoryDataset dataset = JFreeChartPlot.createDataset(resultSet, specs);
		JFreeChart chart = JFreeChartPlot.createChart(dataset, title);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		setContentPane(chartPanel);
	}

}
