package cssm.impactfunctions;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import yaes.cssm.impactfunctions.AifPL;
import yaes.cssm.impactfunctions.ParameterizedLogistic;
import yaes.cssm.util.plot.plotAifPL;
import yaes.ui.format.Formatter;

/**
 * These tests are for the additive composition and the generation of the graphs
 * for them.
 * 
 * @author lboloni
 * 
 */
public class testAifPLBuilding {

	/**
	 * This creates an identity function v' = v (for ranges -5, 5)
	 */
	@Test
	public void testIdentity() {
		ParameterizedLogistic pl = null;
		AifPL aif = new AifPL("identity");
		// this creates an extended 45% angle
		pl = new ParameterizedLogistic("1", 50, 0, 0.08); // was 5, 0, 0.2
		aif.addTerm(pl);
		// shift term, brings it to 0 (by putting the shift at -100
		// in all the area of interest for us, this is a constant
		pl = new ParameterizedLogistic("1", -25, -100, 100);
		aif.addTerm(pl);
		XYSeriesCollection collection = new XYSeriesCollection();
		double high = 1.0; // was 5.0
		double low = -1.0; // was -5.0
		createXYSeries(aif, collection, low, high);
		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * Testing the v' = v + 5 test (adding a value
	 * 
	 * -basically, we are taking an identity, and add 5 to the shift component
	 */
	@Test
	public void testWorthOfClient() {
		AifPL aif = new AifPL("addition");
		ParameterizedLogistic pl = null;
		double payed = -5;
		// this creates an extended 45% angle
		pl = new ParameterizedLogistic("x", 50, 0, 0.08); // was 5, 0, 0.2
		aif.addTerm(pl);
		// shift term, brings it to 0 (by putting the shift at -100
		// in all the area of interest for us, this is a constant
		pl = new ParameterizedLogistic("x", -25 + payed, -100, 100);
		aif.addTerm(pl);
		XYSeriesCollection collection = new XYSeriesCollection();
		double high = 15.0; // was 5.0
		double low = 5.0; // was -5.0
		createXYSeries(aif, collection, low, high);
		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * Testing the v' = v + 5 test (adding a value
	 * 
	 * -basically, we are taking an identity, and add 5 to the shift component
	 */
	@Test
	public void testBeliefScaling() {
		AifPL aif = new AifPL("beliefscaling");
		ParameterizedLogistic pl = null;
		double payed = -5;
		// this creates an extended 45% angle
		pl = new ParameterizedLogistic("b", 1.0, 0.65, 8.0); // was 5, 0, 0.2
		aif.addTerm(pl);
		XYSeriesCollection collection = new XYSeriesCollection();
		double high = 1.0; // was 5.0
		double low = 0.0; // was -5.0
		createXYSeries(aif, collection, low, high);
		plotAifPL.jfreePlotAif(collection);
	}



	/**
	 * The evolution of the dignity in function of the alpha 10 action FOR the
	 * time being, checking Taranjeet's function
	 * 
	 * This is not a single parameter function, but we will do it for taking
	 * just x as the parameter, and outputting delta v
	 */
	@Test
	public void testPolitenessAlpha10_y() {
		ParameterizedLogistic pl = null;
		ParameterizedLogistic pl2 = null;
		XYSeriesCollection collection = new XYSeriesCollection();
		// double x = 0; // loudness
		double y = 0; // rudeness
		for (double x = 0.0; x <= 1.0; x = x + 0.2) {
			AifPL aif = new AifPL("x=" + Formatter.fmt(x));
			//
			// y
			//
			// negative leg
			//
			pl = new ParameterizedLogistic("y", -1.0, 0.95, 15.0);
			// constant -x
			pl2 = new ParameterizedLogistic("x", x, -100, 100);
			aif.addTerm(pl, pl2);
			//
			// positive leg
			//
			pl = new ParameterizedLogistic("y", -0.8, 0, 15.0);
			// constant -x
			pl2 = new ParameterizedLogistic("x", x, -100, 100);
			aif.addTerm(pl, pl2);
			//
			// scale
			//
			pl = new ParameterizedLogistic("y", 0.5, -100, 15.0);
			// constant -x
			pl2 = new ParameterizedLogistic("x", 2 * x, -100, 100);
			aif.addTerm(pl, pl2);
			// aif.addTerm(pl);
			//
			// Now let us create some plots
			//
			double high = 1.0; // was 5.0
			double low = 0.0; // was -5.0
			createXYSeries(aif, collection, low, high);
		}
		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * The evolution of the dignity in function of the alpha 10 action FOR the
	 * time being, checking Taranjeet's function
	 * 
	 * This is not a single parameter function, but we will do it for taking
	 * just x as the parameter, and outputting delta v
	 */
	@Test
	public void testDignityAlpha10_x() {
		AifPL aif = new AifPL("alpha10-dignity");
		ParameterizedLogistic pl = null;
		double x = 0; // loudness
		double y = 0; // rudeness
		//
		// - x = negative identity
		//
		// this creates an extended 45% angle
		pl = new ParameterizedLogistic("x", -50, 0, 0.08); // was 5, 0, 0.2
		aif.addTerm(pl);
		// shift term, brings it to 0 (by putting the shift at -100
		// in all the area of interest for us, this is a constant
		pl = new ParameterizedLogistic("x", 26, -100, 100);
		aif.addTerm(pl);
		//
		// Now let us create some plots
		//
		XYSeriesCollection collection = new XYSeriesCollection();
		double high = 1.0; // was 5.0
		double low = 0.0; // was -5.0
		createXYSeries(aif, collection, low, high);
		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * The evolution of the dignity in function of the alpha 10 action FOR the
	 * time being, checking Taranjeet's function
	 * 
	 * This is not a single parameter function, but we will do it for taking
	 * just y as the parameter
	 */
	@Test
	public void testDignityAlpha10_y() {
		ParameterizedLogistic pl = null;
		ParameterizedLogistic pl2 = null;
		XYSeriesCollection collection = new XYSeriesCollection();
		// double x = 0; // loudness
		double y = 0; // rudeness
		for (double x = 0.0; x <= 1.0; x = x + 0.2) {
			AifPL aif = new AifPL("x=" + Formatter.fmt(x));
			//
			// y
			//
			pl = new ParameterizedLogistic("y", -1.0, 0.95, 15.0);
			// constant -x
			pl2 = new ParameterizedLogistic("x", x, -100, 100);
			aif.addTerm(pl, pl2);
			//
			// Now let us create some plots
			//
			double high = 1.0; // was 5.0
			double low = 0.0; // was -5.0
			createXYSeries(aif, collection, low, high);
		}
		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * Performs a test
	 */
	@Test
	public void test() {
		XYSeriesCollection collection = new XYSeriesCollection();
		double high = 1.0; // was 5.0
		double low = 0.0; // was -5.0
		//
		// a sigmoid AIF
		//
		AifPL aif = new AifPL("sigmoid");
		ParameterizedLogistic pl = new ParameterizedLogistic("x", 1, 0.5, 10);
		aif.addTerm(pl);
		// createXYSeries(aif, collection, low, high);
		//
		// a step function
		//
		aif = new AifPL("step");
		pl = new ParameterizedLogistic("x", 1, 0.5, 1000);
		aif.addTerm(pl);
		// createXYSeries(aif, collection, low, high);
		//
		// an arbitrary near-linear function
		//
		aif = new AifPL("linear");
		pl = new ParameterizedLogistic("x", 5, 0, 0.2);
		aif.addTerm(pl);
		pl = new ParameterizedLogistic("x", -2, -10, 100);
		aif.addTerm(pl);
		// createXYSeries(aif, collection, low, high);
		//
		// a multi-plateau function
		//
		aif = new AifPL("multi-plateau");
		pl = new ParameterizedLogistic("x", 0.3, 0.2, 20.0);
		aif.addTerm(pl);
		pl = new ParameterizedLogistic("x", 0.7, 0.8, 20.0);
		aif.addTerm(pl);
		createXYSeries(aif, collection, low, high);
		//
		// a flat function is a step with the inflexion point far out in the
		// negative
		//
		aif = new AifPL("flat");
		pl = new ParameterizedLogistic("x", -2, -10, 100);
		aif.addTerm(pl);
		// createXYSeries(aif, collection, low, high);

		plotAifPL.jfreePlotAif(collection);
	}

	/**
	 * Gets the value of an Aif where all the components have exactly one value
	 * 
	 * @param aif
	 * @param x
	 * @return
	 */
	public static double getAifWithOneValue(AifPL aif, double x) {
		int count = aif.getParameterCount();
		double val[] = new double[count];
		for (int i = 0; i != count; i++) {
			val[i] = x;
		}
		return aif.getValue(val);
	}

	/**
	 * Creates a series for the single parameter AIF
	 * 
	 * @param pl
	 * @param collection
	 * @param low
	 * @param high
	 * @return
	 */
	public static XYSeries createXYSeries(AifPL aif,
			XYSeriesCollection collection, double low, double high) {
		XYSeries xyseries = new XYSeries(aif.getName());
		for (double x = low; x <= high; x = x + 0.01) {
			double y = getAifWithOneValue(aif, x);
			xyseries.add(x, y);
		}
		collection.addSeries(xyseries);
		return xyseries;
	}

}
