package yaes.cssm.behaviors;

import yaes.cssm.actions.Action;

/**
 * A number of functions which must be implemented by the context
 * in order to be able to be run by the generic CssmSimulation 
 * 
 * @author lboloni
 *
 */
public interface ISocialCalculusContext {
	/**
	 * Returns the next pre-planned action - this is a functionality for scripting
	 * @return
	 */
	public Action getNextPrePlannedAction();
}
