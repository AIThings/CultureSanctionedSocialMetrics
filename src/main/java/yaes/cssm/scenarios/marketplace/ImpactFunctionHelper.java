package yaes.cssm.scenarios.marketplace;

import yaes.cssm.impactfunctions.CanonicalImpactFunctions;

public class ImpactFunctionHelper {

	
	/**
	 * Signum function
	 * @param x
	 * @return
	 */
	public static double Signum(double x) {
		if (x >= 0) {
			return 1.0;
		}
		return -1.0;
	}	
	/**
	 * sigma_p 
	 * 
	 * loudness x can be in the [0, 10] range
	 * 
	 * @param x
	 * @return
	 */
	public static double epistemicMultiplicativePublic(double x) {
		return CanonicalImpactFunctions.Heaviside(x-2.0) * Math.pow((x -2.0) / 8.0, 2.0);
	}
	
	/**
	 * sigma_p 
	 * 
	 * loudness x can be in the [0, 10] range
	 * 
	 * @param x
	 * @return
	 */
	public static double epistemicMultiplicativePeer(double x) {
		return CanonicalImpactFunctions.Heaviside(x-1.0);		
	}
	
	/**
	 * Impact the dignity of offensiveness
	 * @param x
	 * @return
	 */
	public static double impactDignityOfOffensiveness(double x) {
		return  -CanonicalImpactFunctions.Heaviside(x-6.0) * Math.pow(x - 6.0, 3.0);
	}
	
	/**
	 * Impact the dignity of offensiveness
	 * @param x
	 * @return
	 */
	public static double impactPolitenessOfOffensiveness(double x) {
		if (x > 6.0) {
		    return -Math.pow(x-6.0, 3.0);
		} 
		if (x > 4.0) {
			return 0.0;
		}
		return 5;
	}
}
