package yaes.cssm.impactfunctions.util;

public class AIFHelper {

	/**
	 * Sigmoid function helper
	 * @param x 
	 * @param scale
	 * @param inflection
	 * @param tighten
	 * @return
	 */
	public static double[] aifSigmoid(double[] x, double scale, double inflection, double tighten){
		double[] retval = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			retval[i] = Math.floor(scale * (-1 + 2/(1 + Math.exp(-(x[i] - inflection) * tighten))) * 10000) / 10000;
		}
		return retval; 
	}
	
	public static double aifSigmoid(double x, double scale, double inflection, double tighten){
		double retval;
		retval = Math.floor(scale * (-1 + 2/(1 + Math.exp(-(x - inflection) * tighten))) * 10000) / 10000;
		return retval; 
	}
		
	
}
