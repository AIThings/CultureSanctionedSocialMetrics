package yaes.cssm.scenarios.spanishsteps;

import static yaes.cssm.cssm.ActionImpact.cbEvidence;
import static yaes.cssm.cssm.ActionImpact.cssmDelta;
import static yaes.cssm.scenarios.spanishsteps.Actors.CLIENT;
import static yaes.cssm.scenarios.spanishsteps.Actors.CROWD;
import static yaes.cssm.scenarios.spanishsteps.Actors.SELLER;
import static yaes.cssm.scenarios.spanishsteps.Actors.SPOUSE;

import java.util.ArrayList;
import java.util.List;

import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.CbDempsterShafer;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.iAIF;
import yaes.ui.text.TextUi;

/**
 * Action Impact Function implementations for Spanish Steps. Impact of Actions
 * on CSSM and CB is defined in this class.
 * 
 * @author Taranjeet
 * 
 */
public class DefineAif implements iAIF, Constants {

	private static final long serialVersionUID = -1444567934126993730L;

	/**
	 * Seller offers flower for sell
	 * 
	 * <ul>
	 * <li>minor increases on dignity for the client minor
	 * <li>increases in politeness for the seller
	 * </ul>
	 */
	private static void A01(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, 0.005);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.05);
	}

	/**
	 * Client accepts buying the flowers - minor increases in dignity
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A02(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, 0.10);
		// proof of agreement
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(1.0, 0.0));
	}

	/**
	 * Client pays for flowers - decrease in wealth for client, increase for the
	 * seller
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A03(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, WORTH, CLIENT, CLIENT,
				CLIENT, -5);
		cssmDelta(retval, scenario, CULTURE_WESTERN, WORTH, SELLER, SELLER,
				SELLER, 5);
	}

	/**
	 * Client declines buying flowers - minor decreases in politeness
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A04(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT, -0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
				CLIENT, -0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT, -0.05);
	}

	/**
	 * 
	 * Seller offers flowers as a gift
	 * 
	 * <ul>
	 * <li>increases in dignity (being honored)
	 * <li>impact on the belief that the flower is a gift
	 * </ul>
	 */
	private static void A05(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, 0.25);
		cbEvidence(retval, scenario, Q_GIFT, CLIENT, CLIENT,
				new CbDempsterShafer(0.3, 0.0));
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, CROWD,
				SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.15);
	}

	/**
	 * Seller forces the gift on the client
	 * 
	 * <ul>
	 * <li>impact on the public and peer perceptions of dignity (but not on the
	 * self perception, which perceived the forcing)
	 * <li>CB(Q-Gift, Client, Seller) impact on the belief that the flower is a
	 * gift
	 * <li>CB(Q-Agreed, Crowd, Seller) impact of the crowd that a transaction
	 * had been accepted (negative: maybe the crowd had seen the it was forcing)
	 * <li>minor increase on politeness for the seller
	 * </ul>
	 */
	private static void A06(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, 0.25);
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.0, 0.2));
		cbEvidence(retval, scenario, Q_GIFT, CLIENT, CLIENT,
				new CbDempsterShafer(0.3, 0.0));
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.05);
	}

	/**
	 * the client accepts the flower as a gift
	 * 
	 * <ul>
	 * <li>minor increases in politeness and dignity
	 * <li>impacts CB(Q-Agreed, Crowd, Seller)
	 * </ul>
	 */
	private static void A07(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT, 0.10);
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.2, 0.0));
	}

	/**
	 * the client declines the gift
	 * 
	 * <ul>
	 * <li>minor decreases in politeness and dignity FIXME: why minor????
	 * <li>minor impact on the dignity of the seller
	 * </ul>
	 */
	private static void A08(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, -0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
				CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT, -0.10);
	}

	/**
	 * alpha-9: throws gift
	 * 
	 * <ul>
	 * <li>The impact is a very big hit on politeness and dignity in all
	 * perspectives
	 * <li>High impact on dignity and financial damage to the seller
	 * </ul>
	 */
	private static void A09(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, -0.15);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, -0.30);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, -0.30);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT, -0.30);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
				CLIENT, -0.30);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT, -0.30);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, -0.03);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, CROWD,
				SELLER, -0.15);
		cssmDelta(retval, scenario, CULTURE_WESTERN, WORTH, SELLER, SELLER,
				SELLER, -2);
	}

	/**
	 * The client attempts to return the flower with loudness x and politeness y
	 * 
	 * <ul>
	 * <li>Complex impacts on various aspects of politeness and dignity of
	 * client
	 * <li>Complex impact on the crowd perspective dignity of the seller
	 * </ul>
	 */
	private static void A10(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		double delta;
		double loudness = parameters[0];
		double offensiveness = parameters[1];
		double cbAgreedCrowd = scenario.getCB(Q_AGREED, CROWD, CLIENT)
				.getValue();
		double cbAgreedClient = 0; // belief is 0 (he knows he did not agree)
		// we assume the belief of the spouse to be between that of the crowd
		// and the client
		double cbAgreedSpouse = cbAgreedCrowd / 2.0;
		//
		// Dignity of the client
		//
		// for the client,
		double dignityClient = BuildAifPL.A10_Dignity(loudness, offensiveness,
				cbAgreedClient);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, dignityClient);
		double dignityCrowd = BuildAifPL.A10_Dignity(loudness, offensiveness,
				cbAgreedCrowd);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, dignityCrowd);
		// for the spouse, the dignity evolves the same as for the crowd
		double dignitySpouse = BuildAifPL.A10_Dignity(loudness, offensiveness,
				cbAgreedSpouse);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, dignitySpouse);
		//
		// Politeness of the client
		//
		// for the client, the loudness is 0.5 (he hears what he says)
		double politenessClient = BuildAifPL.A10_Politeness(0.5, offensiveness,
				cbAgreedClient);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				CLIENT, CLIENT, politenessClient);
		// for the crowd
		double politenessCrowd = BuildAifPL.A10_Politeness(loudness,
				offensiveness, cbAgreedCrowd);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT, CROWD,
				CLIENT, politenessCrowd);
		// for the spouse, loudness is 1.0 (she hears what he says) belief is
		// the same as crowd (for the time being)
		double politenessSpouse = BuildAifPL.A10_Politeness(0.5, offensiveness,
				cbAgreedSpouse);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, CLIENT,
				SPOUSE, CLIENT, politenessSpouse);

		delta = 0.01 * BuildAifPL.aifplDignityOfOffensiveness(offensiveness);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, delta);
		delta = 0.01 * BuildAifPL.aifplMultiplicativePublic(loudness)
				* BuildAifPL.aifplDignityOfOffensiveness(offensiveness);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, CROWD,
				SELLER, delta);
		
		// time passes, evidence to agreement
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.05, 0.0));

	}

	/**
	 * Seller declines the return of the flower
	 * 
	 * <ul>
	 * <li>small negative impact on dignity of client
	 * <li>for the seller: impact on the dignity and politeness, minor one for
	 * self, complex one for crowd perspective
	 * </ul>
	 */
	private static void A11(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, -0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, -0.10);
		// Seller stuff
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, -0.05);
		// FIXME: needs to be emulated with logistic functions
		double beliefAgreed = scenario.getCB(Q_AGREED, CROWD, CLIENT)
				.getValue();
		double delta = -0.60 * Math.pow(1.0 - beliefAgreed, 2);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, CROWD,
				SELLER, delta);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, -0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, -0.05);
		// time passes, evidence to agreement
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.05, 0.0));

	}

	/**
	 * Seller accepts the return of the flower:
	 * 
	 * <ul>
	 * <li>Small positive impact on dignity of the client
	 * <li>Positive impact on the dignity and politeness of the seller
	 * </ul>
	 */
	private static void A12(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT,
				CLIENT, CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT,
				SPOUSE, CLIENT, 0.10);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.05);
		// big evidence against previous agreement
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.0, 0.5));
	}

	/**
	 * Seller waits time t:
	 * 
	 * <ul>
	 * <li>the client's belief that the flower is a gift increases
	 * <li>the crowd's belief that a transaction had been agreed upon increases
	 * <li>very minor impact on self and crowd dignity
	 * </ul>
	 */
	private static void A13(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		double time = parameters[0];
		CbDempsterShafer evidenceGift = new CbDempsterShafer(0.05 * time, 0.0);
		cbEvidence(retval, scenario, Q_GIFT, CLIENT, CLIENT, evidenceGift);
		CbDempsterShafer evidenceAgreed = new CbDempsterShafer(0.05 * time, 0.0);
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT, evidenceAgreed);
	}

	/**
	 * Seller requests payment:
	 * 
	 * <ul>
	 * <li>complex impact on the dignity of the client from self and crowd
	 * perspective
	 * <li>the belief that there the flower is a gift goes down to zero
	 * </ul>
	 */
	private static void A14(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		double beliefIsAGift = scenario.getCB(Q_GIFT, CLIENT, CLIENT)
				.getValue();
		double delta = -0.30 * beliefIsAGift;
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CLIENT,
				CLIENT, delta);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, CROWD,
				CLIENT, delta);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, CLIENT, SPOUSE,
				CLIENT, delta);
		cbEvidence(retval, scenario, Q_GIFT, CLIENT, CLIENT,
				new CbDempsterShafer(0.0, 1.0));
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, -0.01);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, CROWD,
				SELLER, -0.01);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, -0.01);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, -0.01);
		// time passes, evidence to agreement
		cbEvidence(retval, scenario, Q_AGREED, CROWD, CLIENT,
				new CbDempsterShafer(0.05, 0.0));
	}

	/**
	 * Seller gives up interaction
	 * 
	 * <ul>
	 * <li>minor increase in the sellers dignity and politeness
	 * </ul>
	 */
	private static void A15(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.05);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.05);
	}

	/**
	 * Seller concedes gift
	 * 
	 * <ul>
	 * <li>small gain worth for the client
	 * </ul>
	 */
	private static void A16(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
		cssmDelta(retval, scenario, CULTURE_WESTERN, WORTH, CLIENT, CLIENT,
				CLIENT, 1);
		cssmDelta(retval, scenario, CULTURE_WESTERN, WORTH, SELLER, SELLER,
				SELLER, -5);
		cssmDelta(retval, scenario, CULTURE_WESTERN, DIGNITY, SELLER, SELLER,
				SELLER, 0.1);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER,
				SELLER, SELLER, 0.1);
		cssmDelta(retval, scenario, CULTURE_WESTERN, POLITENESS, SELLER, CROWD,
				SELLER, 0.1);
	}

	/**
	 * ...
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A17(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
	}

	/**
	 * ...
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A18(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
	}

	/**
	 * ...
	 * 
	 * <ul>
	 * <li>...
	 * <li>...
	 * </ul>
	 */
	private static void A19(List<ActionImpact> retval, Scenario scenario,
			double... parameters) {
	}

	/**
	 * Returns the list of actions for a specific impact - it mixes together the
	 * impacts on the client and the seller.
	 * 
	 */
	@Override
	public List<ActionImpact> getImpacts(Scenario scenario, String actionType,
			String actorName, double... parameters) {
		List<ActionImpact> retval = new ArrayList<>();
		//
		// All the actions take 5 seconds, except 13 which is the first
		// parameter. Deal with
		// this here.
		//
		if (!actionType.equals("alpha-13")) {
			cssmDelta(retval, scenario, CULTURE_WESTERN, TIME, SELLER, SELLER,
					SELLER, 5);
			cssmDelta(retval, scenario, CULTURE_WESTERN, TIME, CLIENT, CLIENT,
					CLIENT, 5);
		} else {
			cssmDelta(retval, scenario, CULTURE_WESTERN, TIME, CLIENT, CLIENT,
					CLIENT, parameters[0]);
			cssmDelta(retval, scenario, CULTURE_WESTERN, TIME, SELLER, SELLER,
					SELLER, parameters[0]);
		}

		switch (actionType) {
		case "alpha-1":
			A01(retval, scenario, parameters);
			break;
		case "alpha-2":
			A02(retval, scenario, parameters);
			break;
		case "alpha-3":
			A03(retval, scenario, parameters);
			break;
		case "alpha-4":
			A04(retval, scenario, parameters);
			break;
		case "alpha-5":
			A05(retval, scenario, parameters);
			break;
		case "alpha-6":
			A06(retval, scenario, parameters);
			break;
		case "alpha-7":
			A07(retval, scenario, parameters);
			break;
		case "alpha-8":
			A08(retval, scenario, parameters);
			break;
		case "alpha-9":
			A09(retval, scenario, parameters);
			break;
		case "alpha-10":
			A10(retval, scenario, parameters);
			break;
		case "alpha-11":
			A11(retval, scenario, parameters);
			break;
		case "alpha-12":
			A12(retval, scenario, parameters);
			break;
		case "alpha-13":
			A13(retval, scenario, parameters);
			break;
		case "alpha-14":
			A14(retval, scenario, parameters);
			break;
		case "alpha-15":
			A15(retval, scenario, parameters);
			break;
		case "alpha-16":
			A16(retval, scenario, parameters);
			break;
		case "alpha-17":
			A17(retval, scenario, parameters);
			break;
		case "alpha-18":
			A18(retval, scenario, parameters);
			break;
		case "alpha-19":
			A19(retval, scenario, parameters);
			break;
		default:
			TextUi.errorPrint("Invalid ActionType!");
		}

		return retval;
	}

}
