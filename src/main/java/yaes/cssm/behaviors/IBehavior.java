package yaes.cssm.behaviors;

import java.io.Serializable;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;

/**
 * This object describes the behavior of a social agent in a specific scenario.
 * 
 * It is tied to the specific scenario, the specific role and the specific
 * social agent.
 * 
 * 
 * @author lboloni
 * 
 */
public interface IBehavior extends Serializable {
	/**
	 * The action to be taken by the actor played by the specific agent in the
	 * specific scenario. Should depend on the current state of the agent.
	 */
	public Action getAction();

	/**
	 * Return the actor played by this behavior agent in the scenario
	 * 
	 * @return
	 */
	public String getActor();

	/**
	 * Return the scenario in which we are playing
	 */
	public Scenario getScenario();

	/**
	 * Return the social agent which implements the actor - this is the
	 * repository of the CSSMs owned by the agent
	 */
	public SocialAgent getSocialAgent();

}
