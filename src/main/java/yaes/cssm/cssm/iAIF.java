package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.List;

/**
 * This interface class encompasses the impacts of the actions in a certain
 * scenario (type). Normally different instances of the same scenario will 
 * have the same action-impact functions.
 * 
 * @author Taranjeet
 * 
 */
public interface iAIF extends Serializable {

	/**
	 * This function returns the list of the changes which the given action
	 * would perform
	 * 
	 * @param scenario
	 * @param action
	 * @return
	 */
	public List<ActionImpact> getImpacts(Scenario scenario,
			String actionType, String actorName, double... parameters);
}
