package yaes.cssm.util.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import yaes.ui.plot.MatlabUtil;
import yaes.ui.text.TextUi;
import yaes.util.FileWritingUtil;

/**
 * Collections of functions for plotting the AifPLs 
 * @author Lotzi
 *
 */
public class plotAifPL {

	/**
	 * Uses JFree to plot an AIF with respect to a single parameter. The values must be already 
	 * put in a XYSeriesCollection
	 * 
	 * @param collection
	 */
	public static void jfreePlotAif(XYSeriesCollection collection) {
		//
		// Line styles
		//
		List<SimpleEntry<Color, Stroke>> lineStylesConservative = null;
		lineStylesConservative = new ArrayList<SimpleEntry<Color, Stroke>>();
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.black);
		colors.add(Color.gray);
		List<Stroke> strokes = new ArrayList<Stroke>();
		strokes.add(new BasicStroke(3.0f));
		strokes.add(new BasicStroke(1.0f));
		for (Color color : colors) {
			for (Stroke stroke : strokes) {
				lineStylesConservative.add(new SimpleEntry<Color, Stroke>(color, stroke));
			}
		}
		List<SimpleEntry<Color, Stroke>> lineStyles = lineStylesConservative;
	
		// generate the chart
		JFreeChart chart = ChartFactory.createXYLineChart(null, null, null,
				collection, PlotOrientation.VERTICAL, true, false, false); // legend,
																			// tooltips,
																			// urls
		chart.setBackgroundPaint(Color.white);
		// chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 14));
		XYPlot plot = chart.getXYPlot();
		plot.setOutlineStroke(null);
		XYItemRenderer rend = plot.getRenderer();
		Stroke stroke = new BasicStroke(2.0f);
		plot.getRenderer().setBaseStroke(stroke);
		// plot.getRenderer().setSeriesOutlinePaint(0, Color.blue);
		// chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setBackgroundPaint(Color.white);
	
		int seriesCount = plot.getSeriesCount();
		for (int i = 0; i != seriesCount; i++) {
			SimpleEntry<Color, Stroke> lineStyle = lineStyles.get(i
					% lineStyles.size());
			rend.setSeriesStroke(i, lineStyle.getValue());
			rend.setSeriesPaint(i, lineStyle.getKey());
		}
	
		JFrame aframe = new JFrame("");
		ChartPanel cp = new ChartPanel(chart);
		aframe.add(cp);
		aframe.pack();
		aframe.setVisible(true);
		TextUi.confirm("Press to exit", true);
	}

	
	/**
	 * Generates a plot of the AIF on a 3D surface. The AIF must be passed as TwoParam class
	 * which specifies the iterated values and the constant values
	 *  
	 * @param tp
	 * @param xLabel
	 * @param yLabel
	 * @param fileGraph - the file to white the graph will be written
	 */
	public static void matlabSurfaceAif(TwoParam tp, String xLabel, String yLabel, File fileGraph) {
		// the resolution of the plotting
		int xres = 10;
		int yres = 10;
		double values[][] = new double[xres + 1][yres + 1];
		double xaxis[] = new double[xres + 1];
		for (int i = 0; i <= xres; i++) {
			xaxis[i] = ((double) i) / ((double) xres);
		}
		double yaxis[] = new double[yres + 1];
		for (int i = 0; i <= yres; i++) {
			yaxis[i] = ((double) i) / ((double) yres);
		}
		for (int i = 0; i <= xres; i++) {
			for (int j = 0; j <= yres; j++) {
				// this is the part which is a bit specific!!!
				// maybe a specifically created function class???
				double b = xaxis[i];
				double y = yaxis[j];
				double value = tp.value(b, y);
				values[j][i] = value;
			}
		}
		String plot = MatlabUtil.generateSurface(values, xLabel, xaxis, yLabel, yaxis);
		FileWritingUtil.writeToTextFile(fileGraph, plot);
	}


}
