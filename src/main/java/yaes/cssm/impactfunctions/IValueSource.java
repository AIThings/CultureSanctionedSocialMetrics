package yaes.cssm.impactfunctions;

import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;

/**
 * 
 * @author Taranjeet
 *	Interface for collection of values. 
 */
public interface IValueSource {
	
    public double getCSSMValue(String agent, CSSM value);
    
    public double getBeliefValue(String agent, ConcreteBelief value);
    
    public double getEffectOnWorld();
    
    public void setEffectOnWorld(double distance);
	
    public double getWorldPerception(String agentName, ConcreteBelief cb);
    
    /**
     * 
     * @return String current client name in the scene
     */
    public String getCurrentClient();
    /**
     * 
     * @return String current Seller name in the scene
     */
    public String getCurrentSeller();
}
