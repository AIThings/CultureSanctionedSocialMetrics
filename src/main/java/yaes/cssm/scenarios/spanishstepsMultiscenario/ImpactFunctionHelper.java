package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.awt.geom.Point2D;

import yaes.cssm.impactfunctions.CanonicalImpactFunctions;


public class ImpactFunctionHelper {

	
	public static double boundries(double x){
		
		if (x > 0.9){
			return 0.9;}
		else if (x < 0.1){
			return 0.1;}
		else return x;
		
		
	}
	
	public static double forgettingCurve(double t){
		double retention = Math.exp(-t/15);
		return retention;
	}
	
	/**
	 * sigma_p 
	 * 
	 * loudness x can be in the [0, 10] range
	 * 
	 * @param x
	 * @return
	 */
	public static double epistemicMultiplicativePublic(double x, double defaultVal) {
		return (defaultVal/(1+2*defaultVal*Math.exp(1-x*10)));
	}
	/**
	 * 
	 * @param x loudness
	 * @param y	offensiveness
	 * @param defaultVal default value of CSSV
	 * @return
	 */
	public static double epistemicLoudOffence(double x, double y, double defaultVal) {
		
		double offensiveness = impactDignityOfOffensiveness(y);
		double loudFactor = 2*defaultVal*Math.exp(offensiveness-x*10);
		double estimateEffect = (defaultVal/(1+loudFactor));
		return estimateEffect;
		
	}
	
	public static double estimateEffectPublic(double x1, double y1, double x2, double y2){
		double distance = Point2D.distance(x1, y1, x2, y2);
		//TextUi.println("distance of client from conversation: " + distance);
		double effect = boundries(estimateSigmoidFunction(distance));
		
		return effect;
	}
	
	
	public static double estimateSigmoidFunction(double distance){
		 //(1/(1+e^(x-5)))
		double sigma = (1/(1+ Math.exp(distance-10)));
		
		return sigma;
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
		return  (1 - x);
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
	
	public static double doubleFloor(double impact, int reduction){
		return 0.01 *Math.floor((impact * 100) / reduction );
	}

	public static double dignityRetainInTime(double defaultVal, double oldValue, double time) {
		if (defaultVal > oldValue)
		return ((defaultVal-oldValue) -(defaultVal-oldValue)*forgettingCurve(time));
		return 0;
	}
}
