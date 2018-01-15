package cssm.impactfunctions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import yaes.cssm.impactfunctions.ParameterizedLogistic;
import yaes.ui.format.IDetailLevel;
import yaes.ui.text.TextUi;

public class testParameterizedLogistic {

	/**
	 * Performs a test 
	 */
	@Test
	public void test() {
        XYSeriesCollection collection = new XYSeriesCollection();
        // fill in an x-series with the values        
		ParameterizedLogistic pl = new ParameterizedLogistic("x", 1, 0, 1);
		createXYSeries(pl, collection, -5.0, 5.0);
		pl = new ParameterizedLogistic("x", 2, 0, 1);
		createXYSeries(pl, collection, -5.0, 5.0);
		pl = new ParameterizedLogistic("x", 1, 3, 1);
		createXYSeries(pl, collection, -5.0, 5.0);
		pl = new ParameterizedLogistic("x", 1, 0, 3);
		createXYSeries(pl, collection, -5.0, 5.0);
		pl = new ParameterizedLogistic("x", 1, 0, 0.3);
		createXYSeries(pl, collection, -5.0, 5.0);
		// generate the chart
        JFreeChart chart =
                ChartFactory.createXYLineChart("ParameterizedLogistic",
                        "X", "Value", collection, PlotOrientation.VERTICAL,
                        true, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 14));
		XYPlot plot = chart.getXYPlot();
		plot.setOutlineStroke(null);
		Stroke stroke = new BasicStroke(4.0f);
		plot.getRenderer().setBaseStroke(stroke);
		plot.getRenderer().setSeriesStroke(0, stroke);
		// chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);		
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setBackgroundPaint(Color.white);

		JFrame aframe = new JFrame("");
		ChartPanel cp = new ChartPanel(chart);
		aframe.add(cp);
		aframe.pack();
		aframe.setVisible(true);
		TextUi.confirm("Press to exit", true);
	}
	
	
	public XYSeries createXYSeries(ParameterizedLogistic pl, XYSeriesCollection collection, double low, double high) {
		XYSeries xyseries = new XYSeries(pl.toStringDetailed(IDetailLevel.DTL_NAME_ONLY));
		for(double x = low; x <= high; x = x+ 0.01) {
			double y = pl.getValue(x);
			xyseries.add(x, y);
		}		
		collection.addSeries(xyseries);
		return xyseries;
	}
}
