package cssm.cssm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import yaes.cssm.cssm.CbDempsterShafer;
import yaes.ui.text.TextUi;

public class testCbDempsterShafer {

	/**
	 * Models for the CB(S,Q-Gift,Client,Client) from the paper
	 * 
	 */
	@Test
	public void testCbGiftClientClient() {
		// no knowledge
		CbDempsterShafer cb = new CbDempsterShafer(0.0, 0.0);
		// alpha 5 offers gift
		CbDempsterShafer cbAlpha5 = new CbDempsterShafer(0.2, 0.0);
		// alpha 6 forces gift
		CbDempsterShafer cbAlpha6 = new CbDempsterShafer(0.1, 0.1);
		// alpha13 waits(t)
		//double t = 1;
		// alpha14 requests payment
		CbDempsterShafer cbAlpha14 = new CbDempsterShafer(0.0, 1.0);
		// alpha16 concedes gift
		CbDempsterShafer cbAlpha16 = new CbDempsterShafer(1.0, 0.0);

		XYSeriesCollection collection = new XYSeriesCollection();
		XYSeriesCollection col2 = new XYSeriesCollection();

		List<Integer> tries = new ArrayList<>();
		tries.add(3);
		tries.add(7);
		tries.add(15);
		tries.add(30);
		//
		// a bunch of weak positive evidences
		//
		for (Integer t : tries) {
			CbDempsterShafer cbAlpha13 = new CbDempsterShafer(0.1, 0.0);
			List<CbDempsterShafer> evidences = new ArrayList<>();
			evidences.add(cbAlpha5);
			evidences.add(cbAlpha6);
			for (int i = 0; i != t; i++) {
				evidences.add(cbAlpha13);
			}
			evidences.add(cbAlpha14);
			col2 = plotBeliefEvolution(collection, cb, evidences, "wait " + t);
			plotXYCollection(col2, "wait " + t);
		}
		// plotXYCollection(col2, "Amb+ Amb-");
		plotXYCollection(collection, "Beliefs compared");
		TextUi.confirm("Press to exit", true);
	}

	@Test
	public void testFromCSSMArticle() {
		CbDempsterShafer cb = new CbDempsterShafer(0.4, 0.1);
		TextUi.println(cb);
		// TextUi.println("" = cb.getBelief())
	}

	/**
	 * 
	 * Ok, this is not what I expected. What I denote here as weak (0.1, 0) is
	 * actually what will conserve the ambiguity (the x+y mass)
	 * 
	 * The one which I denote as ambiguous (0.4, 0.3) is the one which eats up
	 * the ambiguity and by 5 steps the x+y mass is completely gone.
	 * 
	 * 
	 */
	@Test
	public void test() {
		// no knowledge
		CbDempsterShafer cb = new CbDempsterShafer(0.3, 0.3);
		// no evidence of anything
		CbDempsterShafer cb_no_evidence = new CbDempsterShafer(0.1, 0.0);
		// weak positive evidence
		CbDempsterShafer cb_weak_positive = new CbDempsterShafer(0.1, 0.0);
		// ambiguous positive evidence
		CbDempsterShafer cb_ambiguous_positive = new CbDempsterShafer(0.4, 0.3);
		// weak negative evidence
		CbDempsterShafer cb_weak_negative = new CbDempsterShafer(0.0, 0.1);
		// ambiguous negative evidence
		CbDempsterShafer cb_ambiguous_negative = new CbDempsterShafer(0.3, 0.4);
		// strong positive evidence
		CbDempsterShafer cb_strong_positive = new CbDempsterShafer(0.9, 0.0);
		// strong negative evidence
		CbDempsterShafer cb_strong_negative = new CbDempsterShafer(0.0, 0.9);

		XYSeriesCollection collection = new XYSeriesCollection();
		XYSeriesCollection col2 = new XYSeriesCollection();

		int max = 40;
		//
		// a bunch of weak positive evidences
		//
		List<CbDempsterShafer> evidences = new ArrayList<>();
		for (int i = 0; i != max; i++) {
			evidences.add(cb_weak_positive);
		}
		col2 = plotBeliefEvolution(collection, cb, evidences, "Weak+");
		plotXYCollection(col2, "Weak+");
		//
		// a bunch of ambiguous positive evidences
		//
		evidences = new ArrayList<>();
		for (int i = 0; i != max; i++) {
			evidences.add(cb_ambiguous_positive);
		}
		col2 = plotBeliefEvolution(collection, cb, evidences, "Amb+");
		plotXYCollection(col2, "Amb+");
		//
		// a bunch of weak+ followed by weak-
		//
		evidences = new ArrayList<>();
		for (int i = 0; i != max; i++) {
			if (i < max / 2) {
				evidences.add(cb_weak_positive);
			} else {
				evidences.add(cb_weak_negative);
			}
		}
		col2 = plotBeliefEvolution(collection, cb, evidences, "Weak+ Weak-");
		plotXYCollection(col2, "Weak+ Weak-");
		//
		// amb+ followed by amb-
		//
		evidences = new ArrayList<>();
		for (int i = 0; i != max; i++) {
			if (i < max / 2) {
				evidences.add(cb_ambiguous_positive);
			} else {
				evidences.add(cb_ambiguous_negative);
			}
		}
		col2 = plotBeliefEvolution(collection, cb, evidences, "Amb+ Amb-");
		plotXYCollection(col2, "Amb+ Amb-");

		plotXYCollection(collection, "Beliefs compared");
		TextUi.confirm("Press to exit", true);
	}

	/**
	 * Plots an XY series collection
	 * 
	 * @param collection
	 */
	public void plotXYCollection(XYSeriesCollection collection, String title) {
		// 
		//  Line styles 
		//
		List<SimpleEntry<Color, Stroke>> lineStylesConservative = null;
		lineStylesConservative = new ArrayList<>();
		List<Color> colors = new ArrayList<>();
		colors.add(Color.black);
		colors.add(Color.gray);
		List<Stroke> strokes = new ArrayList<>();
		strokes.add(new BasicStroke(3.0f));
		strokes.add(new BasicStroke(1.0f));
		for (Color color : colors) {
			for (Stroke stroke : strokes) {
				lineStylesConservative.add(new SimpleEntry<>(color, stroke));
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
        XYItemRenderer rend = plot.getRenderer();

		// plot.setOutlineStroke(null);
		// Stroke stroke = new BasicStroke(2.0f);
		// plot.getRenderer().setBaseStroke(stroke);
		// plot.getRenderer().setSeriesStroke(0, stroke);
		// plot.getRenderer().setSeriesPaint(0, Color.black);
		// plot.getRenderer().setSeriesOutlinePaint(0, Color.blue);
		// chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setBackgroundPaint(Color.white);
		

        int seriesCount = plot.getSeriesCount();
        for (int i = 0; i != seriesCount; i++) {
            SimpleEntry<Color, Stroke> lineStyle =
                    lineStyles.get(i % lineStyles.size());
            rend.setSeriesStroke(i, lineStyle.getValue());
            rend.setSeriesPaint(i, lineStyle.getKey());
        }
		
		
		JFrame aframe = new JFrame(title);
		ChartPanel cp = new ChartPanel(chart);
		aframe.add(cp);
		aframe.pack();
		aframe.setVisible(true);
	}

	/**
	 * Applies the evidences and creates a plot of the beliefs
	 * 
	 * @param collection
	 * @param cbInit
	 * @param evidences
	 * @return
	 */
	public XYSeriesCollection plotBeliefEvolution(
			XYSeriesCollection collection, CbDempsterShafer cbInit,
			List<CbDempsterShafer> evidences, String name) {
		double count = 0;
		XYSeries series = new XYSeries(name);
		XYSeries seriesBelief = new XYSeries("Belief");
		XYSeries seriesPlausibility = new XYSeries("Plausibility");
		series.add(count, cbInit.getBelief());
		seriesBelief.add(count, cbInit.getBelief());
		seriesPlausibility.add(count, cbInit.getPlausibility());
		count++;
		CbDempsterShafer current = cbInit;
		for (CbDempsterShafer evidence : evidences) {
			current = current.applyEvidence(evidence);
			series.add(count, current.getBelief());
			seriesBelief.add(count, current.getBelief());
			seriesPlausibility.add(count, current.getPlausibility());
			count++;
		}
		collection.addSeries(series);
		XYSeriesCollection localCollection = new XYSeriesCollection();
		localCollection.addSeries(seriesBelief);
		localCollection.addSeries(seriesPlausibility);
		return localCollection;
	}

}
