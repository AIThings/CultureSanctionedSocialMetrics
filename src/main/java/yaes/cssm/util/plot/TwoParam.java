package yaes.cssm.util.plot;

import yaes.cssm.impactfunctions.AifPL;

/**
 * A helper class which allows up to create an object which encapsulates iterating over two 
 * parameters of an AIF function. This can be used as a just-in-time creation of the class, 
 * to be passed as a parameter for a 3d plot creation 
 * 
 * @author lboloni
 *
 */
public abstract class TwoParam {
	
	protected AifPL theAif;
	
	public TwoParam(AifPL aif) {
		this.theAif = aif;
	}
	
	public abstract double value(double x, double y);
}
