package yaes.cssm.impactfunctions;


/**
 * Contains a collection of functions which help us assemble 
 * the impact functions. 
 * 
 * As a note, we are moving towards making these a collection of sigmoids, 
 * but for the time being we are refactoring whatever we have
 * 
 * @author Lotzi Boloni
 *
 */
public class CanonicalImpactFunctions {

    /**
     * Heaviside step function
     * @param x
     * @return
     */
    public static double Heaviside(double x) {
    	if (x > 0) {
    		return 1.0;
    	}
    	return 0.0;
    }

}
