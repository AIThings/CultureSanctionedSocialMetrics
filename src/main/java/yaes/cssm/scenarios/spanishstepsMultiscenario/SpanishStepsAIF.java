package yaes.cssm.scenarios.spanishstepsMultiscenario;

import static yaes.cssm.scenarios.spanishsteps.Actors.CLIENT;
import static yaes.cssm.scenarios.spanishsteps.Actors.CROWD;
import static yaes.cssm.scenarios.spanishsteps.Actors.SELLER;
import static yaes.cssm.scenarios.spanishsteps.Actors.SPOUSE;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.CbDempsterShafer;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.iAIF;
import yaes.ui.text.TextUi;

/**
 * Action Impact Function implementations for Spanish Steps Multiscenario Impact
 * of Actions on CSSM and CB is defined in this class.
 * 
 * @author Taranjeet
 * 
 */
public class SpanishStepsAIF implements iAIF, Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6268026680628927273L;

	public enum CIFType {
		D_15, D_16, DP_7, DP_9, DP_12, DP_14, DP_15, DP_16, DP_17, DR_15, DR_16, P_16, PP_16, PR_16, T_14, GIFT_5, GIFT_6, GIFT_12, GIFT_14, GIFT_15, GIFT_21, TRANS_3, TRANS_6, TRANS_13, TRANS_14, TRANS_15, TRANS_20, TRANS_22, DECEPTIVE_3, DECEPTIVE_5, DECEPTIVE_6, DECEPTIVE_8, DECEPTIVE_10, DECEPTIVE_11, DECEPTIVE_12, DECEPTIVE_14, DECEPTIVE_15, DECEPTIVE_17, DECEPTIVE_18, DECEPTIVE_20, DECEPTIVE_21, DECEPTIVE_22, DECEPTIVE_23, WORLD_PERCEPTION_7, WORLD_PERCEPTION_9, WORLD_PERCEPTION_16
	};

	private static double loudness = 0;
	private static double offensiveness = 0;

	@Override
	public List<ActionImpact> getImpacts(Scenario scenario, String actionType,
			String actorName, double... parameters) {
		List<ActionImpact> retval = new ArrayList<>();
		if (actorName.equalsIgnoreCase(CLIENT)) {

			switch (actionType) {
			case "A1":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 0.5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 5);

				break;
			case "A2":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 10);
				break;
			case "A3":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, CLIENT, CLIENT, CLIENT, -5);
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_3));

				break;
			case "A4":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT,
								CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CROWD, CLIENT, -5);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE,
								CLIENT, -5);
				break;
			case "A5":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 25);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_5));

				break;
			case "A6": {
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 25);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_6));
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_6));

				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_6));

				break;
			}
			case "A7":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT, CLIENT,
						clientFunction(scenario, CIFType.P_16, parameters));
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
								CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE, CLIENT,
						-10);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.WORLD_PERCEPTION_7));
				break;
			case "A8":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_8));
				break;
			case "A9":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, -20);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT, CLIENT,
						clientFunction(scenario, CIFType.P_16, parameters));
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
								CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE, CLIENT,
						-20);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.WORLD_PERCEPTION_9));
				break;
			case "A10":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 5);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT,
								CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CROWD, CLIENT, 10);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE,
								CLIENT, 10);
				break;
			case "A11":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT, CLIENT,
						-40);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
								CLIENT, -40);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE, CLIENT,
						-40);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_11));
				break;
			case "A12":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, -5);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT,
								CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CROWD, CLIENT, -5);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE,
								CLIENT, -5);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_12));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_12));
				break;
			case "A13":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CROWD, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE, CLIENT, 5);
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_13));

				break;
			case "A14":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT,
						parameters[0]);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_14));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						Q_AGREED,
						CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_14,
								parameters));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_14));
				break;
			case "A15":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT,
						clientFunction(scenario, CIFType.D_15, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT,
						clientFunction(scenario, CIFType.DP_15, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT,
						clientFunction(scenario, CIFType.DR_15, parameters));

				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_15));
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_15));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_15));
				break;
			case "A16":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT,
						clientFunction(scenario, CIFType.D_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT,
						clientFunction(scenario, CIFType.DP_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE, CLIENT,
						clientFunction(scenario, CIFType.DR_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CLIENT, CLIENT,
						clientFunction(scenario, CIFType.P_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, CROWD, CLIENT,
						clientFunction(scenario, CIFType.PP_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, CLIENT, SPOUSE, CLIENT,
						clientFunction(scenario, CIFType.PR_16, parameters));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.WORLD_PERCEPTION_16));
				break;
			case "A17":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_17));
				break;
			case "A18":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_18));
				break;
			case "A19":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT, CLIENT, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				break;
			case "A20":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, CLIENT, CLIENT, CLIENT, -5);
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_20));
				break;
			case "A21":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						CLIENT,
						clientBeliefMass(scenario, Q_GIFT, CIFType.GIFT_21));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_21));
				break;
			case "A22":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, CLIENT, CROWD, CLIENT, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, CLIENT, CLIENT, CLIENT, 1);
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						CLIENT,
						clientBeliefMass(scenario, Q_AGREED, CIFType.TRANS_22));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						IS_DECEPTIVE,
						CLIENT,
						CLIENT,
						clientBeliefMass(scenario, IS_DECEPTIVE,
								CIFType.DECEPTIVE_22));
				break;
			case "A23":

				break;
			case "A24":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				break;
			default:
				TextUi.errorPrint("Invalid ActionType!");

			}
		}
		// ======================================================================================

		else if (actorName.equalsIgnoreCase(SELLER)) {
			switch (actionType) {
			case "A1":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, 5);

				break;
			case "A2":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A3":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, SELLER, SELLER, SELLER, 5);
				break;
			case "A4":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A5":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, 15);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						SELLER,
						sellerBeliefMass(scenario, Q_GIFT, CIFType.GIFT_5));
				break;
			case "A6":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, 5);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						SELLER,
						sellerBeliefMass(scenario, Q_GIFT, CIFType.GIFT_6));
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						SELLER,
						sellerBeliefMass(scenario, Q_AGREED, CIFType.TRANS_6));
				break;
			case "A7":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, -1);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_7, parameters));
				break;
			case "A8":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A9":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_9, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A10":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A11":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER, -10);
				break;
			case "A12":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_12, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A13":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.createCBImpact(retval, scenario, Q_AGREED, CROWD,
						SELLER,
						sellerBeliefMass(scenario, Q_AGREED, CIFType.TRANS_13));
				break;
			case "A14":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER,
						parameters[0]);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_14, parameters));
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, SELLER, SELLER,
								SELLER, -1);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, -1);
				ActionImpact.createCBImpact(
						retval,
						scenario,
						Q_GIFT,
						CLIENT,
						SELLER,
						sellerBeliefMass(scenario, Q_GIFT, CIFType.GIFT_14,
								parameters));
				ActionImpact.createCBImpact(
						retval,
						scenario,
						Q_AGREED,
						CROWD,
						SELLER,
						sellerBeliefMass(scenario, Q_AGREED, CIFType.TRANS_14,
								parameters));
				break;
			case "A15":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, -1);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, SELLER, SELLER,
								SELLER, -1);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, -1);
				ActionImpact.createCBImpact(retval, scenario, Q_GIFT, CLIENT,
						SELLER,
						sellerBeliefMass(scenario, Q_GIFT, CIFType.GIFT_15));
				break;
			case "A16":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER,
						sellerFunction(scenario, CIFType.D_16, parameters));
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_16, parameters));
				break;
			case "A17":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER,
						sellerFunction(scenario, CIFType.DP_17, parameters));
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, SELLER, SELLER,
								SELLER, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, -5);
				break;
			case "A18":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER, -10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, 5);
				break;
			case "A19":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A20":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, SELLER, SELLER, SELLER, 5);
				break;
			case "A21":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				break;
			case "A22":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, SELLER, SELLER, SELLER, 5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, WORTH, SELLER, SELLER, SELLER, -0.1);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, SELLER, SELLER, -5);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, DIGNITY, SELLER, CROWD, SELLER, -10);
				ActionImpact
						.cssmDelta(retval, scenario,
								CULTURE_WESTERN, POLITENESS, SELLER, SELLER,
								SELLER, 10);
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, POLITENESS, SELLER, CROWD, SELLER, 10);
				break;

			case "A23":
				break;
			case "A24":
				ActionImpact.cssmDelta(retval, scenario,
						CULTURE_WESTERN, TIME, CLIENT, CLIENT, CLIENT, 5);
				break;
			default:
				TextUi.errorPrint("Invalid ActionType!");
			}

		} else if (actorName.equalsIgnoreCase(CROWD)) {

		} else if (actorName.equalsIgnoreCase(SPOUSE)) {

		} else {
			TextUi.errorPrint("No Such Actor exist in the scenario");
		}
		return retval;

	}

	private static ConcreteBeliefValue sellerBeliefMass(Scenario scenario,
			String beliefName, CIFType type, double... parameters) {
		CbDempsterShafer evidence = null;

		if (beliefName == Q_GIFT) {
			switch (type) {
			case GIFT_5: {
				// offered as a gift
				evidence = new CbDempsterShafer(0.5, 0.0);
				break;
			}

			case GIFT_6: {
				// pushed as a gift
				evidence = new CbDempsterShafer(0.8, 0);
				break;
			}
			case GIFT_14: {
				// waiting without being asked for money
				double time = parameters[0];
				evidence = new CbDempsterShafer(0.02 * time, 0);
				break;
			}

			case GIFT_15: {
				// being asked for money
				evidence = new CbDempsterShafer(0.0, 1.0);
				break;
			}

			default:
				TextUi.println("Invalid Belief!!");

			}
			ConcreteBeliefValue cbvOld = scenario.getCB(Q_GIFT, CLIENT, SELLER);
			ConcreteBeliefValue cbvNew = cbvOld.applyEvidence(evidence);
			return cbvNew;
		} else if (beliefName == Q_AGREED) {

			switch (type) {
			case TRANS_6: {
				// forces a gift
				evidence = new CbDempsterShafer(0.5, 0.0);
				break;
			}

			case TRANS_13: {
				// accepts the gift
				evidence = new CbDempsterShafer(0.8, 0);
				break;
			}

			case TRANS_14: {
				// waiting without being asked for money
				double time = parameters[0];
				evidence = new CbDempsterShafer(0.05 * time, 0);
				break;
			}

			default:
				TextUi.println("Invalid Belief!!");

			}
			ConcreteBeliefValue cbvOld = scenario
					.getCB(Q_AGREED, CROWD, SELLER);
			ConcreteBeliefValue cbvNew = cbvOld.applyEvidence(evidence);
			return cbvNew;
		} else {
			TextUi.println("Invalid Belief!!");
			return null;
		}

	}

	private static ConcreteBeliefValue clientBeliefMass(Scenario scenario,
			String beliefName, CIFType type, double... parameters) {
		CbDempsterShafer evidence = null;

		if (beliefName == Q_GIFT) {
			int reduction = 1;
			int increment = 1;

			switch (type) {
			case GIFT_5: {
				// offered as a gift
				evidence = new CbDempsterShafer(
						ImpactFunctionHelper.doubleFloor(0.7, increment), 0.0);
				break;
			}
			case GIFT_6: {
				// pushed as a gift
				evidence = new CbDempsterShafer(0,
						ImpactFunctionHelper.doubleFloor(0.3, reduction));
				break;
			}
			case GIFT_12: {
				// being asked for money
				evidence = new CbDempsterShafer(
						ImpactFunctionHelper.doubleFloor(0.3, increment), 0);
				break;
			}
			case GIFT_14: {
				// waiting without being asked for money
				double time = parameters[0];
				evidence = new CbDempsterShafer(
						ImpactFunctionHelper
								.doubleFloor(0.02 * time, increment),
						0);
				break;
			}
			case GIFT_15: {
				evidence = new CbDempsterShafer(0,
						ImpactFunctionHelper.doubleFloor(1, reduction));
				break;
			}

			default:
				TextUi.println("Invalid Belief!!");

			}

			ConcreteBeliefValue cbvOld = scenario.getCB(Q_GIFT, CLIENT, CLIENT);
			ConcreteBeliefValue cbvNew = cbvOld.applyEvidence(evidence);
			return cbvNew;
		} else if (beliefName == Q_AGREED) {

			switch (type) {

			case TRANS_3:
				// transaction complete
				evidence = new CbDempsterShafer(0.7, 0.0);
				break;
			case TRANS_6: {
				// forces a gift
				evidence = new CbDempsterShafer(0.5, 0);
				break;
			}

			case TRANS_13: {
				// accepts the gift
				evidence = new CbDempsterShafer(0.8, 0);
				break;
			}

			case TRANS_14: {
				// waiting without being asked for money
				double time = parameters[0];
				evidence = new CbDempsterShafer(0.05 * time, 0);
				break;
			}
			case TRANS_15: {
				evidence = new CbDempsterShafer(0, 0.7);
				break;
			}
			case TRANS_20: {
				evidence = new CbDempsterShafer(0, 0.7);
				break;
			}
			case TRANS_22: {
				evidence = new CbDempsterShafer(0.7, 0);
				break;
			}

			default:
				TextUi.println("Invalid Belief!!");
			}
			ConcreteBeliefValue cbvOld = scenario
					.getCB(Q_AGREED, CROWD, CLIENT);
			ConcreteBeliefValue cbvNew = cbvOld.applyEvidence(evidence);
			return cbvNew;
		} else if (beliefName == IS_DECEPTIVE) { // deceptive = true
			switch (type) {
			case DECEPTIVE_3: {
				evidence = new CbDempsterShafer(0, 0.5);
				break;
			}
			// case DECEPTIVE_4:
			case DECEPTIVE_5: {
				evidence = new CbDempsterShafer(0.2, 0);
				break;
			}
			case DECEPTIVE_6: {
				evidence = new CbDempsterShafer(0.3, 0);
				break;
			}
			// case DECEPTIVE_7:
			case DECEPTIVE_8: {
				evidence = new CbDempsterShafer(0, 0.3);
				break;
			}
			// case DECEPTIVE_9:

			case DECEPTIVE_10: {
				evidence = new CbDempsterShafer(0.2, 0);
				break;
			}
			case DECEPTIVE_11: {
				evidence = new CbDempsterShafer(0, 0.2);
				break;
			}
			case DECEPTIVE_12: {
				evidence = new CbDempsterShafer(0.2, 0);
				break;
			}
			// case DECEPTIVE_13:
			case DECEPTIVE_14: {
				// waiting without being asked for money
				double time = parameters[0];
				evidence = new CbDempsterShafer(0, 0.05 * time);
				break;
			}
			case DECEPTIVE_15: {
				evidence = new CbDempsterShafer(0.5, 0);
				break;
			}
			// case DECEPTIVE_16:
			case DECEPTIVE_17: {
				evidence = new CbDempsterShafer(0.5, 0);
				break;
			}
			case DECEPTIVE_18: {
				evidence = new CbDempsterShafer(0.2, 0);
				break;
			}
			// case DECEPTIVE_19:
			case DECEPTIVE_20: {
				evidence = new CbDempsterShafer(0.8, 0);
				break;
			}
			case DECEPTIVE_21: {
				evidence = new CbDempsterShafer(0.2, 0);
				break;
			}
			case DECEPTIVE_22: {
				evidence = new CbDempsterShafer(0, 0.5);
				break;
			}
			case DECEPTIVE_23: {
				evidence = new CbDempsterShafer(0, 0.6);
				break;
			}
			// case DECEPTIVE_24:
			case WORLD_PERCEPTION_7: {
				break;
			}
			case WORLD_PERCEPTION_9: {
				break;
			}
			case WORLD_PERCEPTION_16: {
				break;
			}

			default:
				TextUi.println("Invalid Belief!!");

			}
			ConcreteBeliefValue cbvOld = scenario.getCB(IS_DECEPTIVE, CROWD,
					CLIENT);
			ConcreteBeliefValue cbvNew = cbvOld.applyEvidence(evidence);
			return cbvNew;
		} else {
			TextUi.println("Invalid Belief!!");
			return null;
		}

	}

	private static double sellerFunction(Scenario scenario, CIFType type,
			double... parameters) {
		switch (type) {

		case DP_7:
			loudness = parameters[0];
			return -ImpactFunctionHelper.epistemicMultiplicativePublic(
					loudness, 50);
		case DP_9:
			loudness = parameters[0];
			offensiveness = parameters[1];
			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
					offensiveness, 50);
		case DP_12: {
			double oldValue = scenario.getCSSMValue(CULTURE_WESTERN, DIGNITY,
					SELLER, CROWD, SELLER).getValue();
			double beliefTransaction = scenario.getCB(Q_AGREED, CROWD, SELLER)
					.getValue();
			return beliefTransaction * oldValue;
		}
		case DP_14: {
			double time = parameters[0];
			double oldValue = scenario.getCSSMValue(CULTURE_WESTERN, DIGNITY,
					SELLER, CROWD, SELLER).getValue();
			return ImpactFunctionHelper.dignityRetainInTime(50, oldValue, time);
		}

		case D_16:
			offensiveness = parameters[1];
			return ImpactFunctionHelper
					.impactDignityOfOffensiveness(offensiveness);

		case DP_16:
			loudness = parameters[0];
			offensiveness = parameters[1];
			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
					offensiveness, 50);

		case DP_17: {
			double oldValue = scenario.getCSSMValue(CULTURE_WESTERN, DIGNITY,
					SELLER, CROWD, SELLER).getValue();
			double beliefTransaction = scenario.getCB(Q_AGREED, CROWD, SELLER)
					.getValue();
			return beliefTransaction * oldValue;
		}
		default:
			TextUi.errorPrint("Functional impact on Client not implemented:"
					+ type);
			break;
		}
		return 0;
	}

	private static double clientFunction(Scenario scenario, CIFType type,
			double... parameters) {
		switch (type) {
		case D_15:
			double beliefIsAGift = scenario.getCB(Q_GIFT, CLIENT, CLIENT)
					.getValue();
			return -30 * beliefIsAGift;

		case D_16:
			// return effort to its self dignity
			double loudness = parameters[0];
			double offensiveness = parameters[1];
			return loudness
					* (-1 + ImpactFunctionHelper
							.impactDignityOfOffensiveness(offensiveness)) + 1;
		case DP_15:
			beliefIsAGift = scenario.getCB(Q_GIFT, CLIENT, CLIENT).getValue();
			return -30 * beliefIsAGift;

		case DP_16:
			// return effort to its public dignity
			loudness = parameters[0];
			offensiveness = parameters[1];
			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
					offensiveness, 50);

		case DR_15:
			beliefIsAGift = scenario.getCB(Q_GIFT, CLIENT, CLIENT).getValue();
			return -30 * beliefIsAGift;

		case DR_16:
			loudness = parameters[0];
			offensiveness = parameters[1];
			return loudness
					* (-1 + ImpactFunctionHelper
							.epistemicMultiplicativePeer(loudness)
							* ImpactFunctionHelper
									.impactDignityOfOffensiveness(offensiveness))
					+ 1;
		case P_16:
			loudness = parameters[0];
			offensiveness = parameters[1];
			return loudness
					* (-1 + ImpactFunctionHelper
							.impactPolitenessOfOffensiveness(offensiveness))
					+ 1;
		case PP_16:
			loudness = parameters[0];
			offensiveness = parameters[1];
			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
					offensiveness, 50);

		case PR_16:
			loudness = parameters[0];
			offensiveness = parameters[1];
			double emp = ImpactFunctionHelper
					.epistemicMultiplicativePeer(loudness);
			double ipo = ImpactFunctionHelper
					.impactPolitenessOfOffensiveness(offensiveness);
			return loudness * (-1 + emp * ipo) + 1;

		default:
			TextUi.errorPrint("Functional impact on ACTOR_CLIENT not implemented:"
					+ type);
			break;

		}
		return 0;
	}
}

/**
 * 
 * cssm = new CSSM(CULTURE_WESTERN, POLITENESS, scenario.getActorPlayedBy(
 * ACTOR_CLIENT).getName(), scenario.getActorPlayedBy(ACTOR_CROWD) .getName(),
 * scenario.getActorPlayedBy(ACTOR_CLIENT).getName(), 0, 100, 50);
 */
