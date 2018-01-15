package yaes.cssm.scenarios.giveWayScenario;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.iAIF;
import yaes.cssm.scenarios.spanishsteps.Actors;

public class GiveWayAIF implements iAIF, Constants {

	@Override
	public List<ActionImpact> getImpacts(Scenario scenario, String actionType,
			String actorName, double... parameters) {
		List<ActionImpact> retval = new ArrayList<>();

		

			switch (actionType) {
			case "a1":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_MIXED, TIME, actorName, actorName,
						actorName, 5);

				break;
			case "a2":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_MIXED, TIME, actorName, actorName,
						actorName, 5);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, POLITENESS,
						actorName, actorName, actorName, -5);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, POLITENESS,
						actorName, actorName, actorName, 0);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, POLITENESS,
						actorName, Actors.CROWD, actorName, -5);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, POLITENESS,
						actorName, Actors.CROWD, actorName, 1);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, WIMPINESS,
						actorName, actorName, actorName, -1);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, WIMPINESS,
						actorName, actorName, actorName, -5);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, WIMPINESS,
						actorName, Actors.CROWD, actorName, -1);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, WIMPINESS,
						actorName, Actors.CROWD, actorName, -5);
				break;

			case "a3":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_MIXED, TIME, actorName, actorName,
						actorName, 5);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, POLITENESS,
						actorName, actorName, actorName, 10);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, POLITENESS,
						actorName, actorName, actorName, 1);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, POLITENESS,
						actorName, Actors.CROWD, actorName, 10);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, POLITENESS,
						actorName, Actors.CROWD, actorName, 1);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, WIMPINESS,
						actorName, actorName, actorName, 1);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, WIMPINESS,
						actorName, actorName, actorName, 10);
				
				ActionImpact.cssmDelta(retval, scenario, CULTURE_AMERICAN, WIMPINESS,
						actorName, Actors.CROWD, actorName, 1);
				ActionImpact.cssmDelta(retval, scenario, CULTURE_INDIAN, WIMPINESS,
						actorName, Actors.CROWD, actorName, 10);
				break;
			}
		
		return retval;
	}

	
}
