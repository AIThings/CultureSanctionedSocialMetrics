package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.util.List;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;

public class SellerImpactFunction implements IImpactFunction, Constants {

	public enum SIFType {
		DP_14, D_16, DP_16, DP_17, T_14, DP_7, DP_12, DP_9
	};

	private SIFType type;

	public SellerImpactFunction(SIFType type) {
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

	// @Override
	// public double getImpact(List<Double> values, IValueSource valueSource,
	// String agentName) {
	// switch(type) {
	// case T_14: {
	// double time = values.get(0);
	// return time;
	// }
	// case DP_14: {
	// double time = values.get(0);
	// double oldValue = valueSource.getCSSMValue(agentName, new
	// CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD, ACTOR_SELLER, 0,
	// 100, 50));
	// return ImpactFunctionHelper.dignityRetainInTime(50, oldValue, time);
	// }
	// case D_16: {
	// // return effort to its self dignity
	// // double loudness = values.get(0);
	// double offensiveness = values.get(1);
	// return ImpactFunctionHelper
	// .impactDignityOfOffensiveness(offensiveness);
	// }
	// // case DP_7: {
	// // // return effort to its peer dignity
	// // double loudness = values.get(0);
	// // return
	// -ImpactFunctionHelper.epistemicMultiplicativePublic(loudness,50);
	// //
	// // }
	// case DP_9: {
	// // return effort to its peer dignity
	// double loudness = values.get(0);
	// double offensiveness = values.get(1);
	// return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
	// offensiveness,50);
	// }
	// case DP_16: {
	// // return effort to its peer dignity
	// double loudness = values.get(0);
	// double offensiveness = values.get(1);
	// return -ImpactFunctionHelper.epistemicLoudOffence(loudness,
	// offensiveness,50);
	// }
	// case DP_12: {
	// double oldValue = valueSource.getCSSMValue(agentName, new
	// CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD, ACTOR_SELLER, 0,
	// 100, 50));
	// double beliefTransaction = valueSource.getBeliefValue(agentName,new
	// ConcreteBelief(SPANISH_STEPS, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 1, 0.1));
	// return beliefTransaction * oldValue;
	// }
	// case DP_17: {
	// double oldValue = valueSource.getCSSMValue(agentName, new
	// CSSM(SPANISH_STEPS, DIGNITY, ACTOR_SELLER, ACTOR_CROWD, ACTOR_SELLER, 0,
	// 100, 50));
	// double beliefTransaction = valueSource.getBeliefValue(agentName,new
	// ConcreteBelief(SPANISH_STEPS, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
	// ACTOR_SELLER, 0, 1, 0.1));
	// return beliefTransaction * oldValue;
	// }
	//
	// }
	// return 0;
	// }
	//
	// @Override
	// public double getImpact(List<Double> values, IValueSource valueSource) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
}
