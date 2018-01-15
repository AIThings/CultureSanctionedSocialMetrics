package cssm.cssm;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cssm.actions.TestScenarioHelper;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.CSSMValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.ui.format.Formatter;
import yaes.ui.text.TextUi;

/**
 * CSSM class test
 * @author Taranjeet
 *
 */
public class testCSSM implements Constants {



	/**
	 * Test for the creation and comparison of CSSMs
	 */
	@Test
	public void testCreationAndComparison() {
		Scenario scenario = TestScenarioHelper.createScenario();		
		CSSM cssm1 = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		TextUi.println(cssm1);
		CSSM cssm2 = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		CSSM cssm3 = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.SELLER, Actors.CLIENT, Actors.CLIENT);
		assertTrue(cssm1.equals(cssm2));
		assertTrue(!cssm1.equals(cssm3));		
	}

	/**
	 * Test for the use of the CSSM in hashmaps
	 */
	@Test
	public void testCSSMInHashmaps() {
		Scenario scenario = TestScenarioHelper.createScenario();				
		Map<CSSM, CSSMValue> values = new HashMap<>();
		// create the hashmap and initialize the initial values
		CSSM cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		CSSMValue cv = new CSSMValue(-5, 5, 0);
		values.put(cssm, cv);
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.SELLER, Actors.SELLER, Actors.SELLER);
		cv = new CSSMValue(-5, 5, 0);
		values.put(cssm, cv);
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, Actors.CLIENT, Actors.CLIENT, Actors.CLIENT);
		cv = new CSSMValue(-5, 5, 0);
		values.put(cssm, cv);
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, DIGNITY, Actors.CLIENT, Actors.SPOUSE, Actors.CLIENT);
		cv = new CSSMValue(-5, 5, 0);
		values.put(cssm, cv);
		// put values into the hashmap
		// increase the worth of the ACTOR_SELLER
		cssm = CSSM.createCSSM(scenario, CULTURE_WESTERN, WORTH, Actors.SELLER, Actors.SELLER, Actors.SELLER);
		cv = new CSSMValue(-5, 5, 0);
		values.put(cssm, cv);
		printValues(values);
				
	}
	
	/**
	 * Prints the values in a map
	 * @param values
	 */
	public void printValues(Map<CSSM, CSSMValue> values) {
		Formatter fmt = new Formatter();
		fmt.add("All CSSM values");
		fmt.indent();
		for(CSSM cssmit: values.keySet()) {
			CSSMValue value = values.get(cssmit);
			fmt.add(cssmit);
			fmt.addIndented("Value = " + value);
		}		
		TextUi.println(fmt);
	}
}
