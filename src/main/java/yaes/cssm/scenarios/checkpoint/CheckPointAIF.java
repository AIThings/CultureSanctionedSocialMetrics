package yaes.cssm.scenarios.checkpoint;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.CSSMValue;
import yaes.cssm.cssm.CbDempsterShafer;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.iAIF;
import yaes.cssm.scenarios.spanishsteps.Actors;
import yaes.ui.text.TextUi;

/**
 * Action Impact Function implementations for Check Point Impact of Actions on
 * CSSM and CB is defined in this class.
 * 
 * @author Taranjeet
 * 
 */
public class CheckPointAIF implements iAIF, Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4819972384336713156L;

	public enum VIFType {
		Financial_A1, DIGNITY_A6, DIGNITY_A9, DIGNITY_A11, POLITENESS_PUBLIC_A2, POLITENESS_PEER_A6, POLITENESS_PEER_A9, POLITENESS_PEER_A11, S4_A9, S5_A4, S6_A5, S8_A5, S8_A6, FRIEND_11, SECURITY_A1, SECURITY_A2, DIGNITY_A2, POLITENESS_PUBLIC_A6, POLITENESS_PUBLIC_A9, POLITENESS_PEER_A2, S6_A1, S8_A9, THREAT_2, S4_A3, S5_A1, S5_A2
	}

	@Override
	public List<ActionImpact> getImpacts(Scenario scenario, String actionType,
			String actorName, double... parameters) {
		List<ActionImpact> list = new ArrayList<>();
		if (actorName.equalsIgnoreCase(ACTOR_VENDOR)) {
			switch (actionType) {
			case "A1":

				break;
			case "A2":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.S6_A5, parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.S8_A5, parameters));
				break;
			case "A3":
				break;
			case "A4":
				break;
			case "A5":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, POLITENESS, ACTOR_VENDOR, Actors.CROWD,
						ACTOR_VENDOR, 2);
				break;
			case "A6":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, POLITENESS, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR, 2);
				break;
			case "A7":
				ActionImpact
						.cssmDelta(list,
								scenario,
								CULTURE_EASTERN,
								DIGNITY,
								ACTOR_VENDOR,
								ACTOR_VENDOR,
								ACTOR_VENDOR,
								vendorFunction(scenario, VIFType.DIGNITY_A9,
										parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_VENDOR,
						ACTOR_SERGEANT,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.POLITENESS_PEER_A9,
								parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR, -5);
				break;
			case "A8":
				ActionImpact
						.cssmDelta(list,
								scenario,
								CULTURE_EASTERN,
								DIGNITY,
								ACTOR_VENDOR,
								ACTOR_VENDOR,
								ACTOR_VENDOR,
								vendorFunction(scenario, VIFType.DIGNITY_A6,
										parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_VENDOR,
						ACTOR_SERGEANT,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.POLITENESS_PEER_A6,
								parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.S8_A6, parameters));

				break;
			case "A9":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_VENDOR,
						Actors.CROWD,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.POLITENESS_PUBLIC_A2,
								parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR, -10);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, TRUST, ACTOR_VENDOR, ACTOR_SERGEANT,
						ACTOR_VENDOR, -20);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR, -10);
				break;
			case "A10":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						FINANCIAL,
						ACTOR_VENDOR,
						ACTOR_VENDOR,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.Financial_A1,
								parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, TRUST, ACTOR_VENDOR, ACTOR_SERGEANT,
						ACTOR_VENDOR, 5);
				break;
			case "A11":
				break;
			case "A12":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, DIGNITY, ACTOR_VENDOR, ACTOR_VENDOR,
						ACTOR_VENDOR, -1);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR, -15);
				break;
			case "A13":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						DIGNITY,
						ACTOR_VENDOR,
						ACTOR_VENDOR,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.DIGNITY_A11,
								parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_VENDOR,
						ACTOR_SERGEANT,
						ACTOR_VENDOR,
						vendorFunction(scenario, VIFType.POLITENESS_PEER_A11,
								parameters));
				ActionImpact.createCBImpact(list,
						scenario,
						IS_A_FRIEND,
						ACTOR_VENDOR,
						ACTOR_VENDOR,
						vendorBeliefMass(scenario, IS_A_FRIEND,
								VIFType.FRIEND_11));
				break;
			case "A14":
				break;
			case "A15":
				break;
			case "A16":
				break;
			case "A17":
				break;
			case "A18":
				break;
			default:
				TextUi.errorPrint("Invalid ActionType!");

			}
		} else if (actorName.equalsIgnoreCase(ACTOR_SERGEANT)) {
			switch (actionType) {
			case "A1":
				break;
			case "A2":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, POLITENESS, ACTOR_SERGEANT,
						Actors.CROWD, ACTOR_SERGEANT, 0.2);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.S6_A5, parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, TRUST, ACTOR_SERGEANT, ACTOR_VENDOR,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.S8_A5, parameters));

				break;
			case "A3":
				break;
			case "A4":
				break;
			case "A5":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, POLITENESS, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, 2);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, 5);
				break;
			case "A6":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, POLITENESS, ACTOR_SERGEANT,
						Actors.CROWD, ACTOR_SERGEANT, 2);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, 10);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, 10);
				break;
			case "A7":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_SERGEANT,
						Actors.CROWD,
						ACTOR_SERGEANT,
						sergeantFunction(scenario,
								VIFType.POLITENESS_PUBLIC_A9, parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.S8_A9, parameters));
				break;
			case "A8":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_SERGEANT,
						Actors.CROWD,
						ACTOR_SERGEANT,
						sergeantFunction(scenario,
								VIFType.POLITENESS_PUBLIC_A6, parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, -10);
				break;
			case "A9":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						SECURITY,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.SECURITY_A2,
								parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						DIGNITY,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.DIGNITY_A2,
								parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_SERGEANT,
						ACTOR_VENDOR,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.POLITENESS_PEER_A2,
								parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, -10);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, TRUST, ACTOR_SERGEANT, ACTOR_VENDOR,
						ACTOR_SERGEANT, -10);
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, -10);
				ActionImpact.createCBImpact(list,
						scenario,
						POSES_A_THREAT,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						sergeantBeliefMass(scenario, POSES_A_THREAT,
								VIFType.THREAT_2));

				break;
			case "A10":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, COOPERATION, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.S6_A1, parameters));
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, TRUST, ACTOR_SERGEANT, ACTOR_VENDOR,
						ACTOR_SERGEANT, 10);
				break;
			case "A11":
				break;
			case "A12":
				ActionImpact.cssmDelta(list, scenario,
						CULTURE_EASTERN, FRIENDSHIP, ACTOR_SERGEANT,
						ACTOR_VENDOR, ACTOR_SERGEANT, -20);
				break;
			case "A13":
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						DIGNITY,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.DIGNITY_A11,
								parameters));
				ActionImpact.cssmDelta(list,
						scenario,
						CULTURE_EASTERN,
						POLITENESS,
						ACTOR_SERGEANT,
						ACTOR_VENDOR,
						ACTOR_SERGEANT,
						sergeantFunction(scenario, VIFType.POLITENESS_PEER_A11,
								parameters));
				break;
			case "A14":
				break;
			case "A15":
				break;
			case "A16":
				break;
			case "A17":
				break;
			case "A18":
				break;
			default:
				TextUi.errorPrint("Invalid ActionType!");

			}

		} else if (actorName.equalsIgnoreCase(Actors.CROWD)) {

		} else if (actorName.equalsIgnoreCase(ACTOR_PRIVATE)) {

		} else if (actorName.equalsIgnoreCase(ACTOR_ROBOT)) {

		} else {
			TextUi.errorPrint("No Such Actor exist in the scenario");
		}
		return list;
	}

	private static ConcreteBeliefValue vendorBeliefMass(Scenario scenario,
			String beliefName, VIFType type, double... parameters) {
		CbDempsterShafer evidence = null;
		if (beliefName == IS_A_FRIEND) {
			switch (type) {
			case FRIEND_11:
				// the overnight delay
				CSSMValue oldDignityCM = scenario.getCSSMValue(CULTURE_EASTERN,
						DIGNITY, ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR);
				double oldDignityValue = oldDignityCM.getValue();

				CSSMValue oldPolitenessCM = scenario.getCSSMValue(
						CULTURE_EASTERN, POLITENESS, ACTOR_VENDOR,
						ACTOR_SERGEANT, ACTOR_VENDOR);
				double oldPolitenessValue = oldPolitenessCM.getValue();
				System.out.println(oldDignityValue + "<--DV-PV-->"
						+ oldPolitenessValue);
				if (oldDignityValue < 50 && oldPolitenessValue < 50) {
					evidence = new CbDempsterShafer(0.1, 0.4);
				} else if (oldDignityValue < 40 && oldPolitenessValue < 60) {
					evidence = new CbDempsterShafer(0.1, 0.6);
				} else if (oldDignityValue >= 50 && oldPolitenessValue >= 50) {
					evidence = new CbDempsterShafer(0.2, 0.0);
				} else
					evidence = new CbDempsterShafer(0, 0);
				break;
			default:
				TextUi.println("Invalid Belief!!");
			}
			ConcreteBeliefValue cbvold = scenario.getCB(IS_A_FRIEND,
					ACTOR_VENDOR, ACTOR_VENDOR);
			ConcreteBeliefValue cbvnew = cbvold.applyEvidence(evidence);
			return cbvnew;

		}

		return null;
	}

	private double vendorFunction(Scenario scenario, VIFType type,
			double[] parameters) {

		double MitigatedSpeech = 0;
		double loudness = 0;
		double offensiveness = 0;

		if (parameters.length == 3) {
			MitigatedSpeech = parameters[0];
			loudness = parameters[1];
			offensiveness = parameters[2];
		}

		if (parameters.length == 2) {
			MitigatedSpeech = parameters[0];
			loudness = parameters[1];
		}

		if (parameters.length == 1) {
			MitigatedSpeech = parameters[0];
		}
		double old_value = 0;
		switch (type) {

		// Location * Economic Activity * Business hours left
		// S1_A1
		case Financial_A1:
			return -Math
					.exp((MitigatedSpeech + loudness + offensiveness) * 0.1);

			// Mitigated Speech + Loudness + Offensiveness
			// S3_A6
		case DIGNITY_A6: // return -(MitigatedSpeech + loudness +
							// offensiveness);
			return -1
					* ImpactFunctionHelper.Heaviside(MitigatedSpeech - 4)
					* Math.exp((MitigatedSpeech + loudness + offensiveness) * 0.1);

			// Mitigated Speech + Loudness + Offensiveness
			// S3_A9
		case DIGNITY_A9:
			return -1
					* ImpactFunctionHelper.Heaviside(MitigatedSpeech - 4)
					* Math.exp((MitigatedSpeech + loudness + offensiveness) * 0.1);
		case DIGNITY_A11:
			old_value = scenario.getCSSMValue(CULTURE_EASTERN, DIGNITY,
					ACTOR_VENDOR, ACTOR_VENDOR, ACTOR_VENDOR).getValue();
			if (parameters[0] > 24)
				return 50 - old_value;
			return 0;

		case POLITENESS_PUBLIC_A2:
			// return ImpactFunctionHelper.Signum(5-MitigatedSpeech) *
			// (Math.abs(5-MitigatedSpeech) +
			// ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(loudness));
			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech - 6)
					* Math.exp((MitigatedSpeech + loudness) * 0.1);

			// 5 * t
		case S4_A3:
			return 5 * MitigatedSpeech;

			// 10/T
		case S5_A1:
			return 10 / MitigatedSpeech;

			// -5 * y * z
		case S5_A2:
			return -5 * MitigatedSpeech * loudness;

			// 5* ML
		case S5_A4:
			return 5 * MitigatedSpeech;

			// Mitigated Speech + Loudness + Offensiveness
			// S5_A6
		case POLITENESS_PEER_A6:
			// return ImpactFunctionHelper.Signum(5 - MitigatedSpeech) *
			// (Math.abs(5-MitigatedSpeech) + ImpactFunctionHelper.Signum(5 -
			// MitigatedSpeech)/(loudness+offensiveness));
			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech - 5)
					* Math.exp((MitigatedSpeech + loudness) * 0.1);

			// Case when sergeant declines the gift
		case POLITENESS_PEER_A9:
			// return ImpactFunctionHelper.Signum(5-MitigatedSpeech) *
			// (Math.abs(5-MitigatedSpeech) +
			// ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(loudness));
			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech - 3)
					* Math.exp((MitigatedSpeech) * 0.1);

		case POLITENESS_PEER_A11:
			old_value = scenario.getCSSMValue(CULTURE_EASTERN, POLITENESS,
					ACTOR_VENDOR, ACTOR_SERGEANT, ACTOR_VENDOR).getValue();
			if (parameters[0] > 24)
				return 50 - old_value;
			return 0;
		case S6_A5:
			return 0;

			// 5 * t
		case S8_A5:
			return 5 * MitigatedSpeech;

		default:
			TextUi.errorPrint("Functional impact on Vendor not implemented:"
					+ type);
			break;

		}

		return 0;
	}

	private static ConcreteBeliefValue sergeantBeliefMass(Scenario scenario,
			String beliefName, VIFType type, double... parameters) {
		CbDempsterShafer evidence = null;
		if (beliefName == POSES_A_THREAT) {

			switch (type) {
			case THREAT_2:
				evidence = new CbDempsterShafer(0.5, 0.1);
				break;
			default:
				TextUi.println("Invalid Belief!!");
			}
			ConcreteBeliefValue cbvold = scenario.getCB(POSES_A_THREAT,
					ACTOR_SERGEANT, ACTOR_SERGEANT);
			ConcreteBeliefValue cbvnew = cbvold.applyEvidence(evidence);
			return cbvnew;

		}

		return null;
	}

	private double sergeantFunction(Scenario scenario, VIFType type,
			double[] parameters) {
		double MitigatedSpeech = 0;
		double loudness = 0;
		double offensiveness = 0;

		if (parameters.length == 3) {
			MitigatedSpeech = parameters[0];
			loudness = parameters[1];
			offensiveness = parameters[2];
		}

		if (parameters.length == 2) {
			MitigatedSpeech = parameters[0];
			loudness = parameters[1];
		}

		if (parameters.length == 1) {
			MitigatedSpeech = parameters[0];
		}
		switch (type) {
		case SECURITY_A1:
			return -1 * MitigatedSpeech * Math.pow(loudness, 2);
		case SECURITY_A2:
			return -(MitigatedSpeech + loudness + offensiveness) * 0.3;
		case DIGNITY_A2:
			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech - 2)
					* Math.exp(MitigatedSpeech / 3);
		case POLITENESS_PUBLIC_A6:
			return ImpactFunctionHelper.Signum(5 - MitigatedSpeech)
					* (Math.abs(5 - MitigatedSpeech) + ImpactFunctionHelper
							.Signum(5 - MitigatedSpeech)
							/ (Math.pow(offensiveness, 2)));

		case POLITENESS_PUBLIC_A9:
			return ImpactFunctionHelper.Signum(5 - MitigatedSpeech)
					* (Math.abs(5 - MitigatedSpeech) + ImpactFunctionHelper
							.Signum(5 - MitigatedSpeech)
							/ (Math.pow(loudness, 2)));

		case POLITENESS_PEER_A9:
			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech - 2)
					* Math.exp((MitigatedSpeech + loudness + offensiveness));
		case POLITENESS_PEER_A2:
			return -1
					* ImpactFunctionHelper.Heaviside(MitigatedSpeech - 2)
					* Math.exp((MitigatedSpeech + loudness + offensiveness) * 0.3);

		case S4_A9:
			return -10 * MitigatedSpeech;
		case S5_A4:
			return 10 * MitigatedSpeech;

		case POLITENESS_PEER_A6:
			return ImpactFunctionHelper.Signum(5 - MitigatedSpeech)
					* (Math.abs(5 - MitigatedSpeech) + ImpactFunctionHelper
							.Signum(5 - MitigatedSpeech)
							/ (loudness + offensiveness));

		case S6_A1:
			return -5 / MitigatedSpeech;
		case S6_A5:
			return 5 * MitigatedSpeech;
		case S8_A9:
			return 5 * MitigatedSpeech;
		case DIGNITY_A11:
			double old_value = scenario.getCSSMValue(CULTURE_EASTERN, DIGNITY,
					ACTOR_SERGEANT, ACTOR_SERGEANT, ACTOR_SERGEANT).getValue();
			if (parameters[0] > 24)
				return 50 - old_value;
			return 0;
		case POLITENESS_PEER_A11:
			old_value = scenario.getCSSMValue(CULTURE_EASTERN, POLITENESS,
					ACTOR_SERGEANT, ACTOR_VENDOR, ACTOR_SERGEANT).getValue();
			if (parameters[0] > 24)
				return 50 - old_value;
			return 0;
		default:
			TextUi.errorPrint("Functional impact on Sergeant not implemented:"
					+ type);
			break;

		}
		return 0;
	}
}
