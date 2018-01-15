package cssm.impactfunctions;

import java.io.File;

import org.junit.Test;

import yaes.cssm.impactfunctions.AifPL;
import yaes.cssm.impactfunctions.ParameterizedLogistic;
import yaes.cssm.util.plot.TwoParam;
import yaes.cssm.util.plot.plotAifPL;

public class testPlotAif {

	/**
	 * Test for the creation of the 3d plot
	 */
	@Test
	public void test() {
		ParameterizedLogistic pl = null;
		ParameterizedLogistic pl2 = null;
		ParameterizedLogistic beliefscale = null;
		//
		// Building an AifPL (an early version of the politeness function
		// just for sample)
		//
		// This AifPL depends on 3 different values: b the CB, x the loudness
		// and y the politeness
		//
		AifPL aif = new AifPL("Politeness");
		//
		// negative leg
		//
		beliefscale = new ParameterizedLogistic("b", 1.0, 0.65, 8.0);
		pl = new ParameterizedLogistic("y", -1.0, 0.95, 15.0);
		// constant -x
		pl2 = new ParameterizedLogistic("x", 1, -100, 100);
		aif.addTerm(beliefscale, pl, pl2);
		//
		// scale
		//
		pl = new ParameterizedLogistic("y", 0.5, -100, 15.0);
		// constant -x
		pl2 = new ParameterizedLogistic("x", 1.0, -100, 100);
		aif.addTerm(beliefscale, pl, pl2);
		//
		// positive leg
		//
		pl = new ParameterizedLogistic("y", -0.8, 0, 15.0);
		// constant -x
		pl2 = new ParameterizedLogistic("x", 1, -100, 100);
		aif.addTerm(pl, pl2);
		//
		// scale
		//
		pl = new ParameterizedLogistic("y", 0.5, -100, 15.0);
		// constant -x
		pl2 = new ParameterizedLogistic("x", 1.2, -100, 100);
		aif.addTerm(pl, pl2);
		//
		// Now create a TwoParam object which determines the surface which we
		// are studying.
		// We are fixing the x parameter, and will iterate over y and b
		//
		final double x = 0.5; // loudness
		TwoParam tp = new TwoParam(aif) {
			@Override
			public double value(double b, double y) {
				// double value = theAif.getValue(b, y, x, 0, y, x, b, y, x);
				// set the positive leg's beliefscale to 0
				double value = theAif.getValue(b, y, x, b, y, x, y, x, y, x);
				return value;
			}
		};
		// create the plot, providing the full names
		plotAifPL.matlabSurfaceAif(tp, "b CB(S,Q-Agreed,Crowd,Client)",
				"y (rudeness)", new File("data/aif3d.m"));
	}

}
