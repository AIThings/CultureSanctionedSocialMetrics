package yaes.cssm.behaviors;

import java.io.Serializable;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;

/**
 * An object which, when asked by the simulation to determine which scenario will 
 * be able to generate an action next, it will say which.
 * 
 * @author lboloni
 *
 */
public interface IScenarioSelector extends Serializable {

	Scenario chooseScenario(ScenarioSet scenarioSet, ISocialCalculusContext cssmContext);
	
}
