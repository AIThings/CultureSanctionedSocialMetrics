package cssm.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.cssm.scenarios.spanishsteps.DefineScenario;

/**
 *
 *
 *
 */
public class testActionCbImpacts implements Constants {

	@Test
	public void test() {
		Scenario scenario = TestScenarioHelper.createScenario();

		Map<ConcreteBelief, ConcreteBeliefValue> cbv = DefineScenario
				.initializeCbClient(scenario);
		List<ConcreteBelief> listcb = new ArrayList<>(cbv.keySet());

		ConcreteBelief cb = ConcreteBelief.createCB(scenario, Q_GIFT,
				Actors.CLIENT, Actors.CLIENT);

	}

}
