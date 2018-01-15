package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.util.List;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;

public class ClientImpactFunction implements IImpactFunction, Constants {

	public enum CIFType {
		D_15, D_16, DP_15, DP_16, DR_15, DR_16, P_16, PP_16, PR_16, T_14

	};

	private CIFType type;

	public ClientImpactFunction(CIFType type) {
		this.type = type;
	}

	@Override
	public double getImpact(Scenario scenario, List<Double> values,
			IValueSource valueSource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getImpact(Scenario scenario, List<Double> values,
			IValueSource valueSource, String agentName) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource) {
//		switch (type) {
//		case T_14: {
//			double time = values.get(0);
//			return time;
//		}
//		case D_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(ACTOR_CROWD,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case DP_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(ACTOR_CROWD,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case DR_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(ACTOR_CROWD,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case D_16: {
//			// return effort to its self dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.impactDignityOfOffensiveness(offensiveness)) + 1;
//		}
//		case DR_16: {
//			// return effort to its peer dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.epistemicMultiplicativePeer(loudness)
//							* ImpactFunctionHelper
//									.impactDignityOfOffensiveness(offensiveness))
//					+ 1;
//		}
//		case DP_16: {
//			// return effort to its public dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			// double belief = valueSource.getBeliefValue(ACTOR_CROWD,new
//			// CultureBelief(SPANISH_STEPS, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
//			// ACTOR_CLIENT,0, 1, 0.1));
//			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
//					offensiveness, 50);
//		}
//		case P_16: {
//			// return effort on self politeness
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.impactPolitenessOfOffensiveness(offensiveness))
//					+ 1;
//		}
//		case PR_16: {
//			// return effort to its peer politeness
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			double emp = ImpactFunctionHelper
//					.epistemicMultiplicativePeer(loudness);
//			double ipo = ImpactFunctionHelper
//					.impactPolitenessOfOffensiveness(offensiveness);
//			return loudness * (-1 + emp * ipo) + 1;
//		}
//		case PP_16: {
//			// return effort to its public politeness
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
//					offensiveness, 50);
//		}
//		default: {
//			throw new Error("Functional impact on ACTOR_CLIENT not implemented:"
//					+ type);
//		}
//		}
//	}
//
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		switch (type) {
//		case T_14: {
//			double time = values.get(0);
//			return time;
//		}
//		case D_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(agentName,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case DP_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(agentName,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case DR_15: {
//			double beliefIsAGift = valueSource.getBeliefValue(agentName,
//					new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT,
//							0, 1, 0.1));
//			return -30 * beliefIsAGift;
//		}
//		case D_16: {
//			// return effort to its self dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.impactDignityOfOffensiveness(offensiveness)) + 1;
//		}
//		case DR_16: {
//			// return effort to its peer dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.epistemicMultiplicativePeer(loudness)
//							* ImpactFunctionHelper
//									.impactDignityOfOffensiveness(offensiveness))
//					+ 1;
//		}
//		case DP_16: {
//			// return effort to its public dignity
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
//					offensiveness, 50);
//		}
//		case P_16: {
//			// return effort on self politeness
//			double loudness = values.get(0) * 10;
//			double offensiveness = values.get(1) * 10;
//			return loudness
//					* (-1 + ImpactFunctionHelper
//							.impactPolitenessOfOffensiveness(offensiveness))
//					+ 1;
//		}
//		case PR_16: {
//			// return effort to its peer politeness
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			double emp = ImpactFunctionHelper
//					.epistemicMultiplicativePeer(loudness);
//			double ipo = ImpactFunctionHelper
//					.impactPolitenessOfOffensiveness(offensiveness);
//			return loudness * (-1 + emp * ipo) + 1;
//		}
//		case PP_16: {
//			// return effort to its public politeness
//			double loudness = values.get(0);
//			double offensiveness = values.get(1);
//			return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
//					offensiveness, 50);
//		}
//
//		default: {
//			throw new Error("Functional impact on ACTOR_CLIENT not implemented:"
//					+ type);
//		}
//		}
//	}
//
}
