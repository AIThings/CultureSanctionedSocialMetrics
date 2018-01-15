package yaes.cssm.impactfunctions;

import java.util.List;

import yaes.cssm.cssm.Scenario;


/**
 * 
 * @author Taranjeet
 * Return the impact of specific action.
 */
public interface IImpactFunction {

	/**
	 * 
	 * @param values
	 * @param valueSource 
	 * @return
	 */
	double getImpact(Scenario scenario, List<Double> values, IValueSource valueSource);
	
	/**
	 * 
	 * @param values
	 * @param valueSource
	 * @param agentName
	 * @return	return impact of specific action according to agent
	 */
	double getImpact(Scenario scenario, List<Double> values, IValueSource valueSource, String agentName);
	
}
