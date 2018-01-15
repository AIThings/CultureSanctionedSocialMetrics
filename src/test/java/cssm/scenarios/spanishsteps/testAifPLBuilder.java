package cssm.scenarios.spanishsteps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import yaes.cssm.impactfunctions.AifPL;
import yaes.cssm.scenarios.spanishsteps.BuildAifPL;
import yaes.cssm.util.plot.TwoParam;
import yaes.cssm.util.plot.plotAifPL;
import yaes.ui.text.TextUi;

/**
 * Test the various AIFs by visualizing them
 * @author lboloni
 *
 */
public class testAifPLBuilder {
	
	
	@Test
	public void testA10_Politeness() {
		AifPL aif = BuildAifPL.aif_A10_Politeness();
		TextUi.println(aif);
		//
		// Now create a TwoParam object which determines the surface which we
		// are studying.
		// We are fixing the x parameter, and will iterate over y and b
		//
		final double x = 0.5; // loudness
		TwoParam tp = new TwoParam(aif) {
			@Override
			public double value(double b, double y) {
				Map<String, Double> map = new HashMap<>();
				map.put("b", b);
				map.put("x", x);
				map.put("y", y);
				map.put("1", 1.0);
				double value = theAif.getValue(map);
				return value;
			}
		};
		// create the plot, providing the full names
		plotAifPL.matlabSurfaceAif(tp, "b CB(S,Q-Agreed,Crowd,Client)",
				"y (rudeness)", new File("data/aifA10Politeness.m"));
	}

	
	/**
	 * Building an testing the belief scale
	 */
	@Test
	public void testA10_Dignity() {
		AifPL aif = BuildAifPL.aif_A10_Dignity();
		TextUi.println(aif);
		//
		// Now create a TwoParam object which determines the surface which we
		// are studying.
		// We are fixing the x parameter, and will iterate over y and b
		//
		final double x = 0.5; // loudness
		TwoParam tp = new TwoParam(aif) {
			@Override
			public double value(double b, double y) {
				Map<String, Double> map = new HashMap<>();
				map.put("b", b);
				map.put("x", x);
				map.put("y", y);
				map.put("1", 1.0);
				double value = theAif.getValue(map);
				return value;
			}
		};
		// create the plot, providing the full names
		plotAifPL.matlabSurfaceAif(tp, "b CB(S,Q-Agreed,Crowd,Client)",
				"y (rudeness)", new File("data/aifA10DignityBY.m"));
		//
		// Now create a TwoParam object which determines the surface which we
		// are studying.
		// We are fixing the b parameter, and will iterate over x and y
		//
		final double b = 1.0;
		TwoParam tp2 = new TwoParam(aif) {
			@Override
			public double value(double x, double y) {
				Map<String, Double> map = new HashMap<>();
				map.put("b", b);
				map.put("x", x);
				map.put("y", y);
				map.put("1", 1.0);
				double value = theAif.getValue(map);
				return value;
			}
		};
		// create the plot, providing the full names
		plotAifPL.matlabSurfaceAif(tp2, "b CB(S,Q-Agreed,Crowd,Client)",
				"y (rudeness)", new File("data/aifA10DignityXY.m"));
	}
}
