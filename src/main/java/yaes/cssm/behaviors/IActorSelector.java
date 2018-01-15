package yaes.cssm.behaviors;

import java.io.Serializable;

import yaes.cssm.cssm.Scenario;

/**
 * An interface for the classes which select which actor is to be given the 
 * right to take the next action
 * 
 * @author lboloni
 *
 */
public interface IActorSelector extends Serializable {
	
	public String getActor(Scenario scenario, ISocialCalculusContext socialCalculusContext);

}
