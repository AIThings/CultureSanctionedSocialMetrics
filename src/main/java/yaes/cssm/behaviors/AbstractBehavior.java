package yaes.cssm.behaviors;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;

/**
 * Implements the functionality
 * 
 * @author lboloni
 * 
 */
public abstract class AbstractBehavior implements IBehavior {

	private static final long serialVersionUID = -2303029567406326103L;
	protected String actor;
	protected Scenario scenario;
	protected SocialAgent socialAgent;

	/**
	 * Default constructor, to be implemented by subsequent ones
	 * @param scenario
	 * @param socialAgent
	 * @param actor
	 */
	public AbstractBehavior(Scenario scenario, SocialAgent socialAgent,
			String actor) {
		this.scenario = scenario;
		this.socialAgent = socialAgent;
		this.actor = actor;
	}

	@Override
	public String getActor() {
		return actor;
	}

	@Override
	public Scenario getScenario() {
		return scenario;
	}

	@Override
	public SocialAgent getSocialAgent() {
		return socialAgent;
	}

}
